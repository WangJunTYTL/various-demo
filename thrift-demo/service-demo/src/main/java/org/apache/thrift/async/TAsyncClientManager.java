/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.thrift.async;

import java.io.IOException;
import java.nio.channels.ClosedSelectorException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.SelectorProvider;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeoutException;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 利用队列+（Nio方式）+异步线程的(单线程)
 * Contains selector thread which transitions method call objects
 */
public class TAsyncClientManager {
  private static final Logger LOGGER = LoggerFactory.getLogger(TAsyncClientManager.class.getName());

  // select线程
  private final SelectThread selectThread;
  // 并发集合，会先将请求放入到队列
  private final ConcurrentLinkedQueue<TAsyncMethodCall> pendingCalls = new ConcurrentLinkedQueue<TAsyncMethodCall>();

  public TAsyncClientManager() throws IOException {
    // 启动Select线程
    this.selectThread = new SelectThread();
    selectThread.start();
  }

  public void call(TAsyncMethodCall method) throws TException {
    // SelectThread is Alive
    if (!isRunning()) {
      throw new TException("SelectThread is not running");
    }
    // 请求数据序列化
    method.prepareMethodCall();
    // 请求放入到队列
    pendingCalls.add(method);
    // 唤醒selector，告诉有新的事件
    selectThread.getSelector().wakeup();
  }

  public void stop() {
    selectThread.finish();
  }

  public boolean isRunning() {
    return selectThread.isAlive();
  }

  private class SelectThread extends Thread {
    // 网络通道处理
    private final Selector selector;
    // Selector线程运行状态
    private volatile boolean running;
    // 设置超时的请求会放入到该集合，按照超时时间进行排序
    private final TreeSet<TAsyncMethodCall> timeoutWatchSet = new TreeSet<TAsyncMethodCall>(new TAsyncMethodCallTimeoutComparator());

    public SelectThread() throws IOException {
      // 创建selector实例
      this.selector = SelectorProvider.provider().openSelector();
      // 标记SeletorThread处在运行状态
      this.running = true;
      this.setName("TAsyncClientManager#SelectorThread " + this.getId());

      // We don't want to hold up the JVM when shutting down
      setDaemon(true);
    }

    public Selector getSelector() {
      return selector;
    }

    public void finish() {
      running = false;
      selector.wakeup();
    }

    public void run() {
      while (running) {
        try {
          try {
            if (timeoutWatchSet.size() == 0) {
              // No timeouts, so select indefinitely
              // 等待可以I/O处理的channel
              selector.select();
            } else {
              // We have a timeout pending, so calculate the time until then and select appropriately
              long nextTimeout = timeoutWatchSet.first().getTimeoutTimestamp();
              long selectTime = nextTimeout - System.currentTimeMillis();
              if (selectTime > 0) {
                // Next timeout is in the future, select and wake up then
                // 最多阻塞时间selectTime，保证超时时间精确
                selector.select(selectTime);
              } else {
                // Next timeout is now or in past, select immediately so we can time out
                // 立刻返回
                selector.selectNow();
              }
            }
          } catch (IOException e) {
            LOGGER.error("Caught IOException in TAsyncClientManager!", e);
          }
          // 如果selector有可用的事件，开始处理对应事件
          transitionMethods();
          // 方法调用超时处理，并回调通知方法调用超时
          timeoutMethods();
          // 开始处理队列中的请求
          startPendingMethods();
        } catch (Exception exception) {
          LOGGER.error("Ignoring uncaught exception in SelectThread", exception);
        }
      }
    }

    // Transition methods for ready keys
    private void transitionMethods() {
      try {
        // 得到可用的channel
        Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
        while (keys.hasNext()) {
          SelectionKey key = keys.next();
          keys.remove();
          if (!key.isValid()) {
            // this can happen if the method call experienced an error and the
            // key was cancelled. can also happen if we timeout a method, which
            // results in a channel close.
            // just skip
            continue;
          }
          // 在调用时会设置key关联请求对象
          TAsyncMethodCall methodCall = (TAsyncMethodCall)key.attachment();
          // 方法调用状态转换：连接
          methodCall.transition(key);

          // If done or error occurred, remove from timeout watch set
          if (methodCall.isFinished() || methodCall.getClient().hasError()) {
            timeoutWatchSet.remove(methodCall);
          }
        }
      } catch (ClosedSelectorException e) {
        LOGGER.error("Caught ClosedSelectorException in TAsyncClientManager!", e);
      }
    }

    // Timeout any existing method calls
    private void timeoutMethods() {
      Iterator<TAsyncMethodCall> iterator = timeoutWatchSet.iterator();
      long currentTime = System.currentTimeMillis();
      while (iterator.hasNext()) {
        TAsyncMethodCall methodCall = iterator.next();
        if (currentTime >= methodCall.getTimeoutTimestamp()) {
          iterator.remove();
          // 通知方法超时
          methodCall.onError(new TimeoutException("Operation " + methodCall.getClass() + " timed out after " + (currentTime - methodCall.getStartTime()) + " ms."));
        } else {
          break;
        }
      }
    }

    // Start any new calls
    private void startPendingMethods() {
      TAsyncMethodCall methodCall;
      while ((methodCall = pendingCalls.poll()) != null) {
        // Catch registration errors. method will catch transition errors and cleanup.
        try {
          // 将SocketChannel 和 selector建立绑定关系
          methodCall.start(selector);

          // If timeout specified and first transition went smoothly, add to timeout watch set
          TAsyncClient client = methodCall.getClient();
          if (client.hasTimeout() && !client.hasError()) {
            // 如果设置了超时，需要放入到watch集合里面
            timeoutWatchSet.add(methodCall);
          }
        } catch (Exception exception) {
          LOGGER.warn("Caught exception in TAsyncClientManager!", exception);
          methodCall.onError(exception);
        }
      }
    }
  }

  /** Comparator used in TreeSet */
  private static class TAsyncMethodCallTimeoutComparator implements Comparator<TAsyncMethodCall> {
    public int compare(TAsyncMethodCall left, TAsyncMethodCall right) {
      if (left.getTimeoutTimestamp() == right.getTimeoutTimestamp()) {
        return (int)(left.getSequenceId() - right.getSequenceId());
      } else {
        return (int)(left.getTimeoutTimestamp() - right.getTimeoutTimestamp());
      }
    }
  }
}

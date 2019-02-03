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
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TMemoryBuffer;
import org.apache.thrift.transport.TNonblockingTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * Encapsulates an async method call
 * Need to generate:
 *   - private void write_args(TProtocol protocol)
 *   - public T getResult() throws <Exception_1>, <Exception_2>, ...
 * @param <T>
 */
public abstract class TAsyncMethodCall<T> {

  private static final int INITIAL_MEMORY_BUFFER_SIZE = 128;
  private static AtomicLong sequenceIdCounter = new AtomicLong(0);

  public static enum State {
    CONNECTING,
    WRITING_REQUEST_SIZE,
    WRITING_REQUEST_BODY,
    READING_RESPONSE_SIZE,
    READING_RESPONSE_BODY,
    RESPONSE_READ,
    ERROR;
  }

  /**
   * Next step in the call, initialized by start()
   */
  private State state = null;

  protected final TNonblockingTransport transport;
  private final TProtocolFactory protocolFactory;
  protected final TAsyncClient client;
  private final AsyncMethodCallback<T> callback;
  private final boolean isOneway;
  private long sequenceId;
  
  private ByteBuffer sizeBuffer;
  private final byte[] sizeBufferArray = new byte[4];
  private ByteBuffer frameBuffer;

  private long startTime = System.currentTimeMillis();

  protected TAsyncMethodCall(TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport, AsyncMethodCallback<T> callback, boolean isOneway) {
    this.transport = transport;
    this.callback = callback;
    this.protocolFactory = protocolFactory;
    this.client = client;
    this.isOneway = isOneway;
    this.sequenceId = TAsyncMethodCall.sequenceIdCounter.getAndIncrement();
  }

  protected State getState() {
    return state;
  }

  protected boolean isFinished() {
    return state == State.RESPONSE_READ;
  }

  protected long getStartTime() {
    return startTime;
  }
  
  protected long getSequenceId() {
    return sequenceId;
  }

  public TAsyncClient getClient() {
    return client;
  }
  
  public boolean hasTimeout() {
    return client.hasTimeout();
  }
  
  public long getTimeoutTimestamp() {
    return client.getTimeout() + startTime;
  }

  protected abstract void write_args(TProtocol protocol) throws TException;

  /**
   * Initialize buffers.
   * @throws TException if buffer initialization fails
   */
  protected void prepareMethodCall() throws TException {
    // 实现transport，数据的读取是在本地内存中操作
    TMemoryBuffer memoryBuffer = new TMemoryBuffer(INITIAL_MEMORY_BUFFER_SIZE);
    TProtocol protocol = protocolFactory.getProtocol(memoryBuffer);
    // 写入入参数据
    write_args(protocol);

    int length = memoryBuffer.length();
    // 请求数据
    frameBuffer = ByteBuffer.wrap(memoryBuffer.getArray(), 0, length);
    // 请求数据字节长度
    TFramedTransport.encodeFrameSize(length, sizeBufferArray);
    sizeBuffer = ByteBuffer.wrap(sizeBufferArray);
  }

  /**
   * Register with selector and start first state, which could be either connecting or writing.
   * @throws IOException if register or starting fails
   */
  void start(Selector sel) throws IOException {
    SelectionKey key;
    if (transport.isOpen()) {
      // 更新当前方法调用的状态
      state = State.WRITING_REQUEST_SIZE;
      // SocketChannel 关联 selector，并告诉selector，当前可写了
      key = transport.registerSelector(sel, SelectionKey.OP_WRITE);
    } else {
      // 更新当前方法调用的状态
      state = State.CONNECTING;
      // SocketChannel 关联 selector，并告诉selector，当前开始连接诶了
      key = transport.registerSelector(sel, SelectionKey.OP_CONNECT);

      // non-blocking connect can complete immediately,
      // in which case we should not expect the OP_CONNECT
      if (transport.startConnect()) {
        //通过key通知selector可以写数据了,这里key是建立SocketChannel和Selector的纽带或中间人
        registerForFirstWrite(key);
      }
    }
    // 关联对象到key上，需要时还可以取出来
    key.attach(this);
  }

  void registerForFirstWrite(SelectionKey key) throws IOException {
    state = State.WRITING_REQUEST_SIZE;
    key.interestOps(SelectionKey.OP_WRITE);
  }

  protected ByteBuffer getFrameBuffer() {
    return frameBuffer;
  }

  /**
   * Transition to next state, doing whatever work is required. Since this
   * method is only called by the selector thread, we can make changes to our
   * select interests without worrying about concurrency.
   * @param key
   */
  protected void transition(SelectionKey key) {
    // Ensure key is valid
    if (!key.isValid()) {
      key.cancel();
      Exception e = new TTransportException("Selection key not valid!");
      onError(e);
      return;
    }

    // Transition function
    try {
      switch (state) {
        case CONNECTING:
          doConnecting(key);
          break;
        case WRITING_REQUEST_SIZE:
          doWritingRequestSize();
          break;
        case WRITING_REQUEST_BODY:
          doWritingRequestBody(key);
          break;
        case READING_RESPONSE_SIZE:
          doReadingResponseSize();
          break;
        case READING_RESPONSE_BODY:
          doReadingResponseBody(key);
          break;
        default: // RESPONSE_READ, ERROR, or bug
          throw new IllegalStateException("Method call in state " + state
              + " but selector called transition method. Seems like a bug...");
      }
    } catch (Exception e) {
      key.cancel();
      key.attach(null);
      onError(e);
    }
  }

  protected void onError(Exception e) {
    client.onError(e);
    callback.onError(e);
    state = State.ERROR;
  }

  private void doReadingResponseBody(SelectionKey key) throws IOException {
    if (transport.read(frameBuffer) < 0) {
      throw new IOException("Read call frame failed");
    }
    if (frameBuffer.remaining() == 0) {
      cleanUpAndFireCallback(key);
    }
  }

  private void cleanUpAndFireCallback(SelectionKey key) {
    state = State.RESPONSE_READ;
    key.interestOps(0);
    // this ensures that the TAsyncMethod instance doesn't hang around
    key.attach(null);
    client.onComplete();
    callback.onComplete((T)this);
  }

  private void doReadingResponseSize() throws IOException {
    if (transport.read(sizeBuffer) < 0) {
      throw new IOException("Read call frame size failed");
    }
    if (sizeBuffer.remaining() == 0) {
      state = State.READING_RESPONSE_BODY;
      frameBuffer = ByteBuffer.allocate(TFramedTransport.decodeFrameSize(sizeBufferArray));
    }
  }

  private void doWritingRequestBody(SelectionKey key) throws IOException {
    if (transport.write(frameBuffer) < 0) {
      throw new IOException("Write call frame failed");
    }
    if (frameBuffer.remaining() == 0) {
      if (isOneway) {
        cleanUpAndFireCallback(key);
      } else {
        state = State.READING_RESPONSE_SIZE;
        sizeBuffer.rewind();  // Prepare to read incoming frame size
        key.interestOps(SelectionKey.OP_READ);
      }
    }
  }

  private void doWritingRequestSize() throws IOException {
    if (transport.write(sizeBuffer) < 0) {
      throw new IOException("Write call frame size failed");
    }
    if (sizeBuffer.remaining() == 0) {
      state = State.WRITING_REQUEST_BODY;
    }
  }

  private void doConnecting(SelectionKey key) throws IOException {
    if (!key.isConnectable() || !transport.finishConnect()) {
      throw new IOException("not connectable or finishConnect returned false after we got an OP_CONNECT");
    }
    registerForFirstWrite(key);
  }
}

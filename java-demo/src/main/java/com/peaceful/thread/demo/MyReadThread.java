package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.locks.LockSupport;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/20
 * @since 1.6
 */

public class MyReadThread extends Thread {

    @Override
    public void run() {
        Util.report("start " + currentThread().getName());

        for (; ; ) {

            if (MyQueue.queue.size() > 6 || MyQueue.queue.size() <= 0) {
                // wait(); 这地方不可以用wait
                LockSupport.park(currentThread());
            } else
                Util.report("read:" + MyQueue.queue.poll());
        }
    }
}

package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/20
 * @since 1.6
 */

public class MyWriteThread extends Thread {

    @Override
    public void run() {
        Util.report("start "+currentThread().getName());
        for (; ; ) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Util.report(currentThread().getName()+" write");
            MyQueue.queue.offer(String.valueOf(Math.random()));
        }
    }
}

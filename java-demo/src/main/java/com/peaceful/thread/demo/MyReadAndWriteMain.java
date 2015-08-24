package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.locks.LockSupport;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/20
 * @since 1.6
 */

public class MyReadAndWriteMain {

    public static void main(String[] args) {
        final MyReadThread readThread = new MyReadThread();
        MyWriteThread writeThread = new MyWriteThread();
        readThread.setName("read");
        writeThread.setName("write");
        readThread.start();
        writeThread.start();

        new Thread("loop"){
            @Override
            public void run() {
                Util.report("start " + currentThread().getName());
                for (;;) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    LockSupport.unpark(readThread);
                }
            }
        }.start();
    }
}

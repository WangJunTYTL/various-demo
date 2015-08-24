package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/19
 * @since 1.6
 */

public class T6 {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = Thread.currentThread();
        Util.report("id:" + thread.getId() + "\n" +
                "name:" + thread.getName() + "\n" +
                "priority:" + thread.getPriority() + "\n" +
                "state:" + thread.getState() + "\n" +
                "isAlive:" + thread.isAlive() + "\n" +
                "isDaemon" + thread.isDaemon() + "\n" +
                "isInterrupted:" + thread.isInterrupted());

        Thread bThread = new Thread(new BThread());
        bThread.start();

        // 2s后运行
        Thread.sleep(2000);

        //b线程终止了
        Util.report("bThread is alive:" + bThread.isAlive());
//      让出cpu资源
        Thread.yield();
//        每个Thread都有一个中断状状态，默认为false。可以通过Thread对象的isInterrupted()方法来判断该线程的中断状态。可以通过Thread对象的interrupt()方法将中断状态设置为true。
        thread.interrupt();
//        当一个线程处于sleep、wait、join这三种状态之一的时候，如果此时他的中断状态为true，那么它就会抛出一个InterruptedException的异常，并将中断状态重新设置为false
        Util.report("current thread 暂停"+" "+thread.getState());
        Thread.sleep(1000);

//        Thread.stop()、Thread.suspend、Thread.resume、Runtime.runFinalizersOnExit这些终止线程运行的方法已经被废弃了，
// 使用它们是极端不安全的！想要安全有效的结束一个线程，可以使用下面的方法。

    }

    static class BThread extends Thread {
        @Override
        public void run() {
            Util.report("Bthread exe..");
        }
    }
}

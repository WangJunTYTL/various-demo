package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.LockSupport;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/20
 * @since 1.6
 */

public class LockSupportDemo {

    static Queue<Thread> queue = new ConcurrentLinkedDeque<Thread>();
    static Object lock= new Object();

    // LockSupport.park(); 阻塞当前线程
    // LockSupport.unpark(Thread thread); 许可线程继续执行
    public static void main(String[] args) {
        // 获取主线程
        Thread thread = Thread.currentThread();
        Util.report("main thread:" + thread.getName());
        // LockSupport.park();
        Thread aTh = new Thread(new DemoThread(), "aTh");
        Thread bTh = new Thread(new DemoThread(), "bTh");
        Thread cTh = new Thread(new DemoThread2(), "cTh");
        aTh.start();
        bTh.start();
        cTh.start();

    }

    static class DemoThread extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            for (; ; ) {
                if (name.equals("aTh")) {
                    Util.report("exe lock aTh");
                    queue.add(currentThread());
                    LockSupport.park(currentThread());
                    Util.report("exe lock aTh end");
                }
                Util.report("exe " + name + " ...");
                try {
                    sleep(1000);
                    LockSupport.unpark(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class DemoThread2 extends Thread {
        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            for (; ; ) {
                Util.report(LockSupport.getBlocker(queue.peek()));
//                Util.report("exe " + name + " ...");
            }
        }
    }


}

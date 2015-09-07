package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/7
 * @since 1.6
 */

public class YieldDemo {

    //使用yield()的目的是让相同优先级的线程之间能适当的轮转执行。但是，实际中无法保证yield()达到让步目的，因为让步的线程还有可能被线程调度程序再次选中
    public static void main(String[] args) {
        Object lock = new Object();
        Worker workerA = new Worker(lock);
        Worker workerB = new Worker(lock);
        Worker workerC = new Worker(lock);
        workerA.start();
        workerB.start();
        workerC.start();

    }

    static class Worker extends Thread {

        private Object lock;

        public Worker(Object lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (; ; ) {
                synchronized (lock) {
                    yield();
                    Util.report(getName() + " exe");
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    yield();
                }
            }
        }
    }
}

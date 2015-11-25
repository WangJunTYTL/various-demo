package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/29
 * @since 1.6
 */

public class MutexDemo {

    public static Mutex lock = new Mutex();
    static int x = 0;

    public static void main(String[] args) {
        Worker workerA = new Worker();
        Worker workerB = new Worker();
        workerA.setName("A");
        workerB.setName("B");
        workerA.start();
        workerB.start();
    }

    public static void out() throws InterruptedException {
        try {
            if (lock.tryLock()) {
                Thread.sleep(1000);
                Util.report(Thread.currentThread().getName() + (x++));
            }
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }

    }

    static class Worker extends Thread {

        @Override
        public void run() {
            for (; ; ) {
                try {
                    out();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

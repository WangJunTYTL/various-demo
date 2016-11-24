package com.peaceful.lock.demo;

import java.util.concurrent.locks.Lock;

/**
 * Created by wangjun on 16/8/30.
 */
public class MyLockDemo {


    private static MySync sync = new MySync();

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker(sync);
            worker.start();
        }
    }

    private static class Worker extends Thread {

        private Lock lock;

        public Worker(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            System.out.println("Hello->" + Thread.currentThread().getName());
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }
}

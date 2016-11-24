package com.peaceful.lock.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangjun on 16/8/26.
 */
public class ReentrantLockDemo3 {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        for (int i = 0; i< 5 ;i++){
            new Thread(new Worker()).start();
        }
    }

    public static class Worker implements Runnable {

        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "->" + "start");
            try {
                TimeUnit.MINUTES.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "->" + "end");
            lock.unlock();
        }
    }
}

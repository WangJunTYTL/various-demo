package com.peaceful.lock.demo;

import com.peaceful.Util;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/16
 * @since 1.6
 */

public class ConditionDemo {

    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();
    public static int i = 0;

    public static void main(String[] args) {
        Thread threadA = new Thread(new ThreadA());
        Thread threadB = new Thread(new ThreadB());
        threadA.start();
        threadB.start();
    }


    public static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                while (true) {
                    if (i % 2 == 0) {
                        try {
                            condition.signalAll();//唤醒等待的所有线程
                            // condition.signal();//唤醒等待的其中一个线程
                            condition.await(); //
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Util.report("A thread exe ..." + i);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                while (true) {
                    if (i % 2 != 0) {
                        try {
                            condition.signalAll();
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Util.report("B thread exe ..." + i);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
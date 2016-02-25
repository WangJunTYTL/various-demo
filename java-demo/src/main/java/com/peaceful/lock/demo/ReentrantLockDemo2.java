package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by wangjun on 16/2/23.
 */
public class ReentrantLockDemo2 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock(true);
        Util.report("wait...");
        for (int i = 0; i < 10; i++) {
            new Customer(lock, i).start();
        }
        Util.report("wait...");
    }


}

class Customer extends Thread {

    private Lock lock;
    private int id;

    public Customer(Lock lock, int id) {
        this.lock = lock;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                lock.lock();// 当前线程还可以再次获得锁，获取多次要释放多次
                try {
                    Thread.sleep(1000);
                    Util.report(id + " get ticket");
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            } else {
                Util.report(id + " fail");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }


    }
}

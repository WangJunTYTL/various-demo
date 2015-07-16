package com.peaceful.lock.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/15
 * @since 1.6
 */

public class ReadWriteLockDemo {

    // 锁
    ReadWriteLock lock = new ReentrantReadWriteLock();
    // 值
    double value = 0d;
    int addtimes = 0;

    /**
     * 增加value的值，不允许多个线程同时进入该方法
     */
    public void addValue(double v) {
        // 得到writeLock并锁定
        Lock writeLock = lock.writeLock();
        writeLock.lock();
        System.out.println("ReadWriteLockTest to addValue: " + v + "   "
                + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        try {
            // 做写的工作
            this.value += v;
            this.addtimes++;
        } finally {
            // 释放writeLock锁
            writeLock.unlock();
        }
    }
    /**
     * 获得信息。当有线程在调用addValue方法时，getInfo得到的信息可能是不正确的。
     * 所以，也必须保证该方法在被调用时，没有方法在调用addValue方法。
     */
    public String getInfo() {
        // 得到readLock并锁定
        Lock readLock = lock.readLock();
        readLock.lock();
        System.out.println("ReadWriteLockTest to getInfo   "
                + System.currentTimeMillis());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        try {
            // 做读的工作
            return this.value + " : " + this.addtimes;
        } finally {
            // 释放readLock
            readLock.unlock();
        }
    }

}

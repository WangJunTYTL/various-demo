package com.peaceful.lock.demo;

import com.peaceful.Util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wangjun  [wangjuntytl@163.com]
 * @version 1.0
 * @since 15/4/16.
 */

public class ReentrantLockDemo {

    static Integer[] buffer = new Integer[9];
    static int size;
    static Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Write write = new Write();
        Read read = new Read();
        write.start();
        read.start();
    }


    public static class Write extends Thread {

        @Override
        public void run() {
            for (; ; ) {
                lock.lock();
                try {
                    for (; size < buffer.length; size++) {
                        if (size == -1) size++;
                        buffer[size] = size;
                        if (size == 5) {
                            lock.unlock();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class Read extends Thread {

        @Override
        public void run() {
            for (; ; ) {
                lock.lock();
                try {
                    if (size == 9) size--;
                    for (; size >= 0; size--) {
                        Util.report(buffer[size]);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}

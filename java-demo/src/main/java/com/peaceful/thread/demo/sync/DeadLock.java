package com.peaceful.thread.demo.sync;

import com.peaceful.Util;

import java.util.concurrent.TimeUnit;

/**
 * 检测死锁 ：jps | grep App | awk '{print $1}' | xargs jstack
 *
 * Created by wangjun on 16/3/4.
 */
public class DeadLock {

    static Object lockA = new Object();
    static Object lockB = new Object();
    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
    }

    static class T1 extends Thread{
        @Override
        public void run() {
            synchronized (lockA){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB){
                    Util.report("a");
                }
            }
        }
    }

    static class T2 extends Thread{
        @Override
        public void run() {
            synchronized (lockB){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockA){
                    Util.report("b");
                }
            }
        }
    }
}

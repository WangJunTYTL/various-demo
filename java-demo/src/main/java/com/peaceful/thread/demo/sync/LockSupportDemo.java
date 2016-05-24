package com.peaceful.thread.demo.sync;

import com.peaceful.common.util.Util;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangjun on 16/3/3.
 */
public class LockSupportDemo {

    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        t1.setName("hello");
        T2 t2 = new T2();
        T2 t23 = new T2();
        t2.setName("world01");
        t23.setName("world02");
        t1.start();
        Thread.sleep(1000);

        t2.start();
        t23.start();


    }

    static Object object = new Object();

    static class T1 extends Thread{

        @Override
        public void run() {
            while (true){
//                LockSupport.unpark(this);
                LockSupport.park(object);
//                LockSupport.parkUntil(System.currentTimeMillis()+30000);
                Util.report("hello world");
            }
        }

    }

    static class T2 extends Thread{

        @Override
        public void run() {
            while (true){
                synchronized (object) {
                    // pass
                    Util.report("aaa");
                }
            }
        }

    }

}

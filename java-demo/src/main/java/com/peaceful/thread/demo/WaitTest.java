package com.peaceful.thread.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/20
 * @since 1.6
 */


// WaitTest.java的源码
class AAAAAAThread extends Thread{

    public AAAAAAThread(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+" call notify()");
            // 唤醒当前的wait线程
            notify();
        }
    }
}

public class WaitTest {

    public static void main(String[] args) {

        AAAAAAThread t1 = new AAAAAAThread("t1");

        synchronized(t1) {
            try {
                // 启动“线程t1”
                System.out.println(Thread.currentThread().getName()+" start t1");
                t1.start();

                // 主线程等待t1通过notify()唤醒。
                System.out.println(Thread.currentThread().getName()+" wait()");
                t1.wait();

                System.out.println(Thread.currentThread().getName()+" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

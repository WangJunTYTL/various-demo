package com.peaceful.thread.demo.state;

/**
 * Created by wangjun on 16/3/4.
 */
public class BlockStateDemo {


    public static void main(String[] args) {

        // BLOCK
        T1 t1 = new T1();
        T1 t2 = new T1();
        t1.setName("ABC");
        t2.setName("T2");
        t1.start();
        t2.start();

        T2 t23 = new T2();
        t23.setName("T23");
        t23.start();
    }

    static Object lock = new Object();

    static class T1 extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    // pass
                }
            }
        }
    }

    static Object lock2 = new Object();

    static class T2 extends Thread {
        @Override
        public void run() {
            while (true) {
                synchronized (lock2) {
                    try {
                        lock2.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

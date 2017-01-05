package com.peaceful.thread.demo.thread.pool;

/**
 * Created by JunWang on 2016/12/9.
 */
public class Print1_100 {



    public static int i = 1;
    public static Object lock = new Object();

    public static void main(String[] args) {
        new TA().start();
        new TB().start();
    }

    public static class TA extends Thread{
        @Override
        public void run() {
            while (true && i <100){
                synchronized (lock) {
                    if (i % 2 == 1){
                        System.out.println(i++);
                        lock.notifyAll();
                    }else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static class TB extends Thread{
        @Override
        public void run() {
            while (true && i <=100){
                synchronized (lock) {
                    if (i % 2 == 0){
                        System.out.println(i++);
                        lock.notifyAll();
                    }else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

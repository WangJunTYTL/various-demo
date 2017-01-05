package com.peaceful.thread.demo.thread.pool;

/**
 * Created by JunWang on 2016/12/9.
 */
public class TestPoolSetup {

    public static void main(String[] args) {
        TestThreadPool pool = new TestThreadPool(2);
        pool.start();
        for (int i = 0;i<100;i++){
            pool.submit(new Task());
        }
    }

    public static class Task implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"->hello world");
        }
    }
}

package com.peaceful.lock.demo;

import com.peaceful.Util;

import java.util.concurrent.TimeUnit;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/5
 * @since 1.6
 */

public class AQSDemoDemo {


    public static void main(String[] args) throws InterruptedException {
        final AQSDemo aqsDemo = new AQSDemo();

        Worker worker01 = new Worker(aqsDemo);
        Worker worker02 = new Worker(aqsDemo);

        worker01.setName("workd01");
        worker02.setName("workd02");
        worker01.start();
        worker02.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                aqsDemo.signal();
                Worker worker = new Worker(aqsDemo);
                worker.start();
            }
        }).start();


    }

    static class Worker extends Thread {

        AQSDemo aqsDemo;

        public Worker(AQSDemo aqsDemo) {
            this.aqsDemo = aqsDemo;
        }
        @Override
        public void run() {
            for (int i = 0; i < 6; i++) {
                if (i == 5) {
                    try {
                          aqsDemo.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Util.report(i);
            }
        }
    }
}

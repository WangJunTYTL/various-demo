package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/5
 * @since 1.6
 */

public class AQSDemoDemo {


    public static void main(String[] args) {
        AQSDemo aqsDemo = new AQSDemo();

        Worker worker = new Worker(aqsDemo);

        worker.start();


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

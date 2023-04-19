package com.peaceful.thread.demo.sync;

import com.peaceful.Util;

/**
 * Created by wangjun on 16/3/4.
 */
public class ABC {


    public static void main(String[] args) throws InterruptedException {

        Worker workerA = new Worker(null);
        Worker workerB = new Worker(workerA);
        Worker workerC = new Worker(workerB);
        workerA.setName("A");
        workerB.setName("B");
        workerC.setName("C");
        workerA.start();
        workerB.start();
        workerC.start();


    }

    static class Worker extends Thread {

        Thread before;

        public Worker(Thread before) {
            this.before = before;
        }

        @Override
        public void run() {
            try {
                if (before != null)
                    before.join();
                Util.report(getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

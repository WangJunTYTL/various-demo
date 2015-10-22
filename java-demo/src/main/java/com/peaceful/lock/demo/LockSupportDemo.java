package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.locks.LockSupport;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/14
 * @since 1.6
 */

public class LockSupportDemo {

    public static void main(String[] args) {
        int state = 0;
        Worker worker = new Worker(state);
        worker.start();
        Manager manager = new Manager(worker);
        manager.start();
    }

    static class Worker extends Thread {

        private int state;

        public Worker(int state) {
            this.state = state;
        }

        @Override
        public void run() {
            for (; ; ) {
                if (state % 2 == 0) {
                    LockSupport.park(this);
                }
                state++;
                Util.report(state);
            }
        }
    }

    static class Manager extends Thread {

        private  Thread worker;
        public Manager(Thread thread){
            this.worker = thread;
        }

        @Override
        public void run() {
            for (; ; ) {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LockSupport.unpark(worker);
                Thread.yield();
            }
        }
    }
}

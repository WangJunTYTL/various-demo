package com.peaceful.thread.demo;

import com.peaceful.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/7
 * @since 1.6
 */

public class InterruptDemo {

    public static void main(String[] args) {
        Worker worker = new Worker(5000);
        Manage manage = new Manage(worker);
        worker.start();
        manage.start();
    }

    static class Worker extends Thread {

        private long sleep;

        public Worker(long sleep) {
            this.sleep = sleep;
        }

        @Override
        public void run() {

            try {
                sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isInterrupted()) {
                Util.report("isInterrupted");
            }

            Util.report("Worker complete");
        }
    }

    static class Manage extends Thread {

        private Thread worker;
        public Manage(Thread worker){
            this.worker = worker;
        }

        @Override
        public void run() {

            for (; ; ) {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                worker.interrupt();// 打断worker的休眠
                Util.report("manage interrupt worker");
            }

        }
    }
}

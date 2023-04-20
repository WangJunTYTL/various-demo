package com.peaceful.thread.demo;

import com.peaceful.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/6
 * @since 1.6
 */

public class JoinDemo {

    public static void main(String[] args) throws InterruptedException {

        Worker worker = new Worker(5000);
        worker.start();
        worker.join(); // 等待worker线程执行完毕
        worker.join(1000); //等待worker线程1s钟的时间，如果1s没有执行完毕，则继续执行
        Util.report("main complete");

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
            Util.report("worker complete");
        }
    }
}

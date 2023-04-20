package com.peaceful.thread.demo;

import com.peaceful.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/7
 * @since 1.6
 */

public class FIFOMutexDemo {

    public static void main(String[] args) {
        FIFOMutex fifoMutex = new FIFOMutex();
        Worker workerA = new Worker(fifoMutex);
        Worker workerB = new Worker(fifoMutex);
        Worker workerC = new Worker(fifoMutex);
        Manage manage =  new Manage(fifoMutex);
        workerA.setName("worker-a");
        workerB.setName("worker-b");
        workerC.setName("worker-c");
        workerA.start();
        workerB.start();
        workerC.start();
        manage.start();

    }

    static class Worker extends Thread {

        FIFOMutex fifoMutex;

        public Worker(FIFOMutex fifoMutex) {
            this.fifoMutex = fifoMutex;
        }

        @Override
        public void run() {
            fifoMutex.lock();
            Util.report(currentThread().getName()+" worker complete");
        }

    }

    static class Manage extends Thread {

        FIFOMutex fifoMutex;

        public Manage(FIFOMutex fifoMutex) {
            this.fifoMutex = fifoMutex;
        }

        @Override
        public void run() {
            for (;;){
                try {
                    sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Util.report("manage exe...");
                fifoMutex.unlock();
            }
        }

    }


}

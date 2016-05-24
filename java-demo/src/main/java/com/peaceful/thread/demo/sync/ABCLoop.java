package com.peaceful.thread.demo.sync;

import java.util.concurrent.Semaphore;

/**
 * Created by wangjun on 16/3/4.
 */
public class ABCLoop {


    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphoreA = new Semaphore(1);
        Semaphore semaphoreB = new Semaphore(0);
        Semaphore semaphoreC = new Semaphore(0);

        Worker workerA = new Worker(semaphoreA, semaphoreB);
        Worker workerB = new Worker(semaphoreB, semaphoreC);
        Worker workerC = new Worker(semaphoreC, semaphoreA);
        workerA.setName("A");
        workerB.setName("B");
        workerC.setName("C");
        workerA.start();
        workerB.start();
        workerC.start();


    }

    static class Worker extends Thread {

        Semaphore semaphore01;
        Semaphore semaphore02;

        public Worker(Semaphore semaphore01, Semaphore semaphore02) {

            this.semaphore01 = semaphore01;
            this.semaphore02 = semaphore02;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    semaphore01.acquire();
                    System.out.print(getName());
                    semaphore02.release(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

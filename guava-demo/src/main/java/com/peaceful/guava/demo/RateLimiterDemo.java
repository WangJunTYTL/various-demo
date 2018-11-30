package com.peaceful.guava.demo;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * Created by Jun on 2018/7/19.
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1.0);
        WorkerA workerA = new WorkerA(rateLimiter);
        WorkerB workerB = new WorkerB(rateLimiter);
        workerA.start();
        workerB.start();

    }

    static class WorkerA extends Thread {

        private RateLimiter rateLimiter;

        public WorkerA(RateLimiter rateLimiter){
            this.rateLimiter = rateLimiter;
        }

        @Override
        public void run() {
            int i = 0;
            for (; ; ) {
                rateLimiter.acquire();
                System.out.println("workerA->:"+i);
                i++;
            }
        }
    }

    static class WorkerB extends Thread {

        private RateLimiter rateLimiter;

        public WorkerB(RateLimiter rateLimiter){
            this.rateLimiter = rateLimiter;
        }

        @Override
        public void run() {
            int i = 0;
            for (; ; ) {
                if (rateLimiter.tryAcquire(1,TimeUnit.SECONDS)){
                    System.out.println("workerB->:"+i);
                    i++;
                }else{
                    // 被限流
                }


            }
        }
    }
}

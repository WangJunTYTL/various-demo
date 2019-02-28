package com.peaceful.lock.demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * Created by wangjun38 on 2018-12-14.
 */
public class LockSupportDemo2 {


    public static void main(String[] args) {
        Worker worker = new Worker();

        worker.start();
    }


    public static class Worker extends Thread{

        @Override
        public void run() {
            for (;;){
                System.out.println("......");
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
            }
        }
    }
}


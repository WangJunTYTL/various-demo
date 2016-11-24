package com.peaceful.thread.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun on 16/8/29.
 */
public class ExecutorDemo {


    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new T1(),1,1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new T2(),1,1, TimeUnit.SECONDS);
    }

    public static class T1 implements Runnable{

        @Override
        public void run() {
            // 没有任何异常
            int x = 1/0;
            System.out.println("hello T1");
        }
    }

    public static class T2 implements Runnable{

        @Override
        public void run() {
            System.out.println("hello T2");
        }
    }
}

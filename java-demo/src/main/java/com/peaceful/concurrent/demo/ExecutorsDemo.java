package com.peaceful.concurrent.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * jdk Executor 并发框架，主要结构是 队列  线程池
 * <p/>
 * Created by wangjun on 15/2/6.
 */
public class ExecutorsDemo {


    public static void main(String[] args) {
        //自动优化线程池大小
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.submit(new Client(1));
        cachedThreadPool.submit(new Client(1));

        //线程池固定大小
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);
        fixedThreadPool.submit(new Client(100));
        fixedThreadPool.submit(new Client(100));

        //单个线程
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.submit(new Client(1000));

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        scheduledThreadPool.schedule(new Client(10000),5, TimeUnit.SECONDS);

//        executorService.execute(new Runnable() {
//            @Override
//            public void run() {
//                int i = 0;
//                while (i < 97) {
//                    Util.report("hello");
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                i++;
//            }
//        });
    }
}

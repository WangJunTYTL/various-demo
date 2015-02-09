package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangjun on 15/2/6.
 */
public class ExecutorsDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(new Client());
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

package com.peaceful.thread.demo.thread.pool;

import com.peaceful.sync.demo.test.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun38 on 2020/10/27.
 */
public class ExecutorsDemo {


    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(1, 2,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());


        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        BlockingQueue<Runnable> bolckQueue = new ArrayBlockingQueue<>(5);
                        for (; ; ) {
                            try {
                                bolckQueue.put(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                System.out.println(System.currentTimeMillis());
                                                try {
                                                    TimeUnit.SECONDS.sleep(1);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                );
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            executorService.submit(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        bolckQueue.take().run();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });

                        }
                    }
                }
        ).start();
    }


}

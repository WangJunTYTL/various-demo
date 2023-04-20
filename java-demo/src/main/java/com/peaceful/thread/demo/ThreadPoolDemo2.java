package com.peaceful.thread.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjun38 on 2020/10/20.
 */
public class ThreadPoolDemo2 {

    private List<Runnable> taskList = new ArrayList<>();
    private List<Thread> threadList = new ArrayList<>();
    private List<Worker> workerList = new ArrayList<>();
    private int threadCount = 5;

    public static void main(String[] args) {
        ThreadPoolDemo2 threadPoolDemo2 = new ThreadPoolDemo2();
        try {
            threadPoolDemo2.initThreadPool();
            for (int i = 0; i < 100; i++) {
                threadPoolDemo2.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("running..........");
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void submit(Runnable runnable) {
        System.out.println("add task...");
        taskList.add(runnable);
        for (int i = 0; i < workerList.size(); i++) {
            if (workerList.get(i).isRunning) {
                // nothing
            } else {
                workerList.get(i).notifyWorker();
            }
        }
    }

    class Worker implements Runnable {
        private volatile boolean isRunning = true;
        private Object lock = new Object();

        public void notifyWorker() {

            try {
                synchronized (lock) {
                    lock.notify();
                    System.out.println("notify......");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void run() {
            for (; ; ) {
                synchronized (lock) {
                    System.out.println("execute......");
                    if (taskList.isEmpty()) {
                        try {
                            isRunning = false;
                            System.out.println("wait......");
                            lock.wait();
                            isRunning = true;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Runnable runnable = taskList.get(0);
                        taskList.remove(runnable);
                        runnable.run();
                    }
                }

            }
        }
    }

    private void initThreadPool() throws InterruptedException {
        for (int i = 0; i < threadCount; i++) {
            Worker worker = new Worker();
            Thread thread = new Thread(worker);
            thread.start();
            threadList.add(thread);
            workerList.add(worker);
        }
    }

}

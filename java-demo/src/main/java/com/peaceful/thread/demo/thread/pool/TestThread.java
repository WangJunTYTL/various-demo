package com.peaceful.thread.demo.thread.pool;

import java.util.Queue;

/**
 * Created by JunWang on 2016/12/9.
 */
public class TestThread extends Thread{

    private String name;
    private volatile boolean isStop;
    private Queue<Runnable> workQueue;
    private Object lock = new Object();
    private volatile boolean isBusy = false;


    public TestThread(String name,Queue<Runnable> workQueue){
        this.name = name;
        this.workQueue = workQueue;
    }

    @Override
    public void run() {
       while (!isStop){
           synchronized (lock) {
               Runnable task = workQueue.poll();
               if (task == null) {
                   try {
                       isBusy = false;
                       // 一定要搞清楚wait和notify应该是谁的方法，这里不可以直接写wait
                       lock.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }else {
                   isBusy = true;
                   task.run();
                   System.out.println("task queue size->"+workQueue.size());
               }
           }
       }
    }

    public void doWork(){
        synchronized (lock){
            lock.notifyAll();
        }
    }

    public void kill(){
        this.isStop = true;
    }

    public boolean isBusy(){
        return isBusy;
    }
}

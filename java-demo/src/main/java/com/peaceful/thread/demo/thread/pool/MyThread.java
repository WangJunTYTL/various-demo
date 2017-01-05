package com.peaceful.thread.demo.thread.pool;

import com.peaceful.common.util.Util;

/**
 * Created by wangjun on 16/2/22.
 */
public class MyThread extends Thread {


    private Runnable task = null;
    private boolean isShutDown = false;
    private boolean isIdea = true;
    private int id;

    public MyThread(int id) {
        this.id = id;
    }

    public int getTId() {
        return id;
    }


    public synchronized void doTask(Runnable task) {
        this.task = task;
        isIdea = false;
        // 幻想在这个object monitor lock处于等待的线程
        notifyAll();
    }

    public synchronized boolean isIdea() {
        return isIdea;
    }

    @Override
    public void run() {
        synchronized (this) {
            while (!isShutDown) {
                if (task != null) {
                    Util.report(id + " do worker");
                    isIdea = false;
                    task.run();
                    isIdea = true;
                }
                try {
                    Util.report("thread " + id + " wait...");
                    // wait 方法会释放掉 object monitor lock，使线程处于wait状态
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isIdea = true;
            }
        }
    }
}

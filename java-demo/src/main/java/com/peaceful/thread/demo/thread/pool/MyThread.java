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
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isIdea = true;
            }
        }
    }
}

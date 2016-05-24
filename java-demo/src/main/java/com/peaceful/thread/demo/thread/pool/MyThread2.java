package com.peaceful.thread.demo.thread.pool;

/**
 * Created by wangjun on 16/3/3.
 */
public class MyThread2 extends Thread {

    private Runnable task;
    private volatile boolean isIdle = true;
    private Object lock = new Object();


    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                isIdle = false;
                if (task != null) {
                    task.run();
                }
                isIdle = true;
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setTask(Runnable task) {
        this.task = task;
        synchronized (lock) {
            lock.notify();
        }
    }

    public boolean isIdle() {
        return isIdle;
    }
}

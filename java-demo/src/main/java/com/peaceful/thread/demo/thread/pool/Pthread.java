package com.peaceful.thread.demo.thread.pool;

/**
 * 线程池内线程对象需要一直处于start的状态,当无任务执行时需要处于wait状态
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 15/12/9
 */
public class Pthread extends Thread {

    private ThreadPool threadPool;
    private Runnable task;
    private boolean isIdle = false;
    private boolean isShutDown = false;

    public Pthread(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    public void run() {

        while (!isShutDown) {
            isIdle = false;
            if (task != null) {
                task.run();
            }
            isIdle = true;
            task = null;

            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutDown() {
        isShutDown = true;
        notifyAll();
    }


}

package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/1
 * @since 1.6
 */

public class ThreadLocalDemo {

    public static void main(String[] args) {
        WorkerManage workerManage = new WorkerManage();
        workerManage.setName("worker-manage");
        workerManage.start();
    }

    /**
     * 每个线程都附带一个map结构的集合 {@link Thread#threadLocals}
     * 调用threadLocal的set或get方法会操作这个map
     */
    static ThreadLocal<String> threadLocal = new ThreadLocal<String>();


    static class WorkerManage extends Thread {

        @Override
        public void run() {
            for (; ; ) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                threadLocal.set(getName() + "-" + System.currentTimeMillis());
                new Worker().exe();
            }
        }
    }

    static class Worker {

        public void exe() {
            Util.report(threadLocal.get() + " worker ...");
        }
    }
}

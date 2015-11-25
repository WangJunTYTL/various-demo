package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

/**
 * 在实际使用线程时,为了方便管理 监控线程会用到threadGroup的概念
 *
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/11/14
 * @since 1.6
 */

public class ThreadGroupDemo {

    public static void main(String[] args) {

        Threads.start(new Worker());
    }

    static class Threads {
        private static DefaultThreadFactory defaultThreadFactory = new DefaultThreadFactory(new ThreadGroup("TestThreadGroup"), true);
        private static ThreadListener listener = new DefaultThreadListener();

        public static void start(Worker worker) {
            Thread thread = defaultThreadFactory.newThread(worker);
            listener.onCreate(thread);
            thread.start();
            listener.onStart(thread);
        }


    }

    interface ThreadListener {

        void onCreate(Thread thread);

        void onStart(Thread thread);

        void onStop();
    }

    static class DefaultThreadListener implements ThreadListener{

        @Override
        public void onCreate(Thread thread) {
            Util.report("create thread "+ thread.getThreadGroup().getName()+"-"+thread.getName());
        }

        @Override
        public void onStart(Thread thread) {
            Util.report("start thread "+ thread.getThreadGroup().getName()+"-"+thread.getName());
        }

        @Override
        public void onStop() {

        }
    }

    static class DefaultThreadFactory {

        private ThreadGroup threadGroup;

        public DefaultThreadFactory(ThreadGroup threadGroup, boolean daemon) {
            this.threadGroup = threadGroup;
            threadGroup.setDaemon(daemon);
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(threadGroup, runnable);
            thread.setPriority(Thread.NORM_PRIORITY);
            if (thread.isDaemon()) thread.setDaemon(false);
            return thread;
        }

    }


    static class Worker implements Runnable {

        @Override
        public void run() {
            for (; ; ) {
                Util.report("worker exe ...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

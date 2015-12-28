package com.peaceful.thread.demo.thread.pool;

import java.util.List;
import java.util.Vector;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 15/12/9
 */
public class ThreadPool {

    private List<Pthread> pool = new Vector<Pthread>(6);

    public boolean isShutDown = false;


    private ThreadPool() {

    }

    public ThreadPool getInstance() {
        return Single.pool;
    }


    private static class Single {
        static ThreadPool pool = new ThreadPool();
    }

    public synchronized void start(Runnable runnable) {

        if (pool.size() != 0) {
            Pthread pthread = pool.remove(pool.size() - 1);
        }
    }

    public synchronized void returnThread(Pthread pthread) {
        if (!isShutDown) {
            pool.add(pthread);
        }else {
            pthread.shutDown();
        }
    }


}

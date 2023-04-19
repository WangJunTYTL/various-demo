package com.peaceful.thread.demo.thread.pool;

import com.peaceful.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 16/2/22.
 */
public class MyThreadPool {

    private List<MyThread> pool = new ArrayList<MyThread>();

    private MyThreadPool() {

    }

    public static MyThreadPool getMyThreadPool(int size) {
        MyThreadPool myThreadPool = new MyThreadPool();
        if (size <= 0) throw new RuntimeException("size must gt 0");
        for (int i = 0; i < size; i++) {
            MyThread myThread = new MyThread(i);
            myThreadPool.pool.add(myThread);
            myThread.setDaemon(false);
            myThread.start();
            Util.report("create new thread for pool");

        }
        return myThreadPool;
    }

    public void submit(Runnable task) {
        boolean flag = false;
        for (MyThread myThread : pool) {
            if (myThread.isIdea()) {
                myThread.doTask(task);
                Util.report("receive task for "+myThread.getTId());
                flag = true;
                break;
            }
        }
        if (flag) {
            // pass
        } else {
            throw new RuntimeException("not idea thread in the pool");
        }
    }
}

package com.peaceful.thread.demo.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by JunWang on 2016/12/9.
 */
public class TestThreadPool {

    private int size;
    private List<TestThread> threadList;
    private Queue<Runnable> taskQueue = new ArrayBlockingQueue<Runnable>(1000);

    public  TestThreadPool(int size){
        this.size = size;
        this.threadList = new ArrayList<TestThread>();
    }

    public void start(){
        if (size <= 0 ){
            throw new IllegalArgumentException("size must > 0");
        }
        for (int i =0;i<size;i++) {
            TestThread testThread = new TestThread("test-" + i, taskQueue);
            System.out.println("start thread->"+testThread.getName());
            threadList.add(testThread);
            testThread.start();
        }
    }

    public void submit(Runnable task){
        taskQueue.add(task);
        System.out.println("submit task");
        for (TestThread thread:threadList){
            if (!thread.isBusy()){
                thread.doWork();
                return;
            }
        }
    }

    public void stop(){

    }

}

package com.peaceful.thread.demo.thread.pool;

import com.peaceful.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun on 16/3/3.
 */
public class MyThreadPool2 {


    public boolean submit(Runnable task) {
        boolean flag = false;
        for (MyThread2 thread2 : threadList) {
            if (thread2.isIdle()) {
                thread2.setTask(task);
                flag = true;
            }
        }
        if (flag) {
            return true;
        }
        return flag;

    }


    private final static List<MyThread2> threadList = new ArrayList<MyThread2>();

    private static MyThreadPool2 newInstance(int size) {
        if (size <= 0) throw new RuntimeException("size must gt 0");
        MyThreadPool2 pool2 = new MyThreadPool2();
        for (int i = 0; i < size; i++) {
            MyThread2 thread2 = new MyThread2();
            thread2.start();
            threadList.add(thread2);
        }
        return pool2;
    }

    public static void main(String[] args) {
        MyThreadPool2 pool2 = newInstance(6);
        for (int i = 8; i < 100; i++) {
            pool2.submit(new Worker());
        }
    }

    static class Worker implements Runnable{

        @Override
        public void run() {
            Util.report("hello world!");
        }
    }


}

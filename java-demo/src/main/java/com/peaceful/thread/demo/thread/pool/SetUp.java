package com.peaceful.thread.demo.thread.pool;


import com.peaceful.Util;

/**
 * Created by wangjun on 16/2/22.
 */
public class SetUp {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = MyThreadPool.getMyThreadPool(5);
        for (int i = 0; i < 1000; i++) {
            try {
                myThreadPool.submit(new Task());
            }catch (Exception e){
                Util.report(e.getMessage());
            }
        }

    }

    static class Task implements Runnable {

        @Override
        public void run() {
            Util.report("Hi");
        }
    }
}

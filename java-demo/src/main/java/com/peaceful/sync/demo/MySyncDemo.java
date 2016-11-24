package com.peaceful.sync.demo;

import java.util.concurrent.locks.Lock;

/**
 * Created by wangjun on 16/9/2.
 */
public class MySyncDemo {

    private static MySync sync = new MySync();

    public static void main(String[] args) {
        for (int i = 0; i< 2000;i++){
            Worker worker = new Worker(sync);
            worker.start();
        }
    }

    private static class Worker extends Thread{

        Lock lock;
        public Worker(Lock sync){
            this.lock = sync;
        }
        @Override
        public void run() {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"->Hello");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }
}

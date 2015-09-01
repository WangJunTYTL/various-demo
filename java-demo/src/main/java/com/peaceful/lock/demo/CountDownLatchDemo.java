package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.CountDownLatch;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/1
 * @since 1.6
 */

public class CountDownLatchDemo {

    // 与信号量的作用有点类似，用于
    // A synchronization aid that allows one or more threads to wait until
    // a set of operations being performed in other threads completes.
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch =  new CountDownLatch(2);//需要释放两次
        Worker workerA = new Worker(countDownLatch);
        Worker workerB = new Worker(countDownLatch);
        workerA.setName("worker-A");
        workerB.setName("worker-B");
        workerA.start();
        workerB.start();
        countDownLatch.await();//等待workerA、B完成任务
        Util.report("worker complete will exit !!!");

    }

    static class Worker extends Thread{

        CountDownLatch countDownLatch;

        public Worker(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }
        @Override
        public void run() {
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Util.report(getName()+" worker");
            countDownLatch.countDown();
        }
    }
}

package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/2
 * @since 1.6
 */

public class CyclicBarrierDemo {

    //Waits until all parties have invoked await on this barrier.
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        Worker workerA= new Worker(cyclicBarrier,5);
        Worker workerB= new Worker(cyclicBarrier,5);
        Worker workerC= new Worker(cyclicBarrier,5000);
        workerA.start();
        workerB.start();
        workerC.start();
    }

    static class Worker extends Thread {

        CyclicBarrier cyclicBarrier;
        long time;

        public Worker(CyclicBarrier cyclicBarrier,long time) {
            this.cyclicBarrier = cyclicBarrier;
            this.time = time;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(time);
                Util.report(getName() + " start ...");
                cyclicBarrier.await(); // 等待所有的任务都调用了await后开始执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            Util.report(getName() + " complete ...");
        }
    }
}

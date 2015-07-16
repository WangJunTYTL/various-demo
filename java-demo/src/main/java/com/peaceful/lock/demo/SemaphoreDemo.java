package com.peaceful.lock.demo;


import com.peaceful.common.util.Util;

import java.util.concurrent.Semaphore;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/15
 * @since 1.6
 */

public class SemaphoreDemo {

    Semaphore semaphore = new Semaphore(6, true);

    // 信号量，限定对某一个资源的最大访问量
    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 100; i++) {
            demo.getNext();
        }
    }

    public void getNext() throws InterruptedException {
         Util.report(semaphore.tryAcquire());
//         semaphore.acquire(); //会被阻塞
        // semaphore.acquireUninterruptibly(); 不响应中断
//        Util.report(semaphore.drainPermits());

    }

    public void release() {
        semaphore.release();
    }
}

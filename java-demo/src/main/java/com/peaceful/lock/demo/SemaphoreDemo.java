package com.peaceful.lock.demo;


import com.peaceful.common.util.Util;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/15
 * @since 1.6
 */

public class SemaphoreDemo {

    // 信号量，通常被用来限制某个资源的最大访问线程数

    // 信号量，限定对某一个资源的最大访问量
    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo demo = new SemaphoreDemo();
        for (int i = 0; i < 100; i++) {
            demo.getNext();
            Util.report("空闲的信号量->"+demo.semaphore.availablePermits());
            if (i == 10) {
                demo.release();
            }
        }
    }

    Semaphore semaphore = new Semaphore(6, true);

    public void getNext() throws InterruptedException {
//         Util.report(semaphore.tryAcquire()); 如果等不到，立即返回false
        Util.report(semaphore.tryAcquire(500, TimeUnit.MILLISECONDS));
//         semaphore.acquire(); //会被阻塞
        // semaphore.acquireUninterruptibly(); 不响应中断
//        Util.report(semaphore.drainPermits());
    }

    public void release() {
//      semaphore.release();
        semaphore.release(Integer.MAX_VALUE);// 要释放锁的数量,注意如果给的数值大于初始化时信号量的个数，会主动增加信号量的个数
    }
}

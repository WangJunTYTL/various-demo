package com.peaceful.concurrent.demo;

import java.util.concurrent.ForkJoinPool;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/6
 * @since 1.6
 */

public class ForkJoinTaskDemoDemo {

    public static void main(String[] args) {
        ForkJoinTaskDemo forkJoinTaskDemo = new ForkJoinTaskDemo(5);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(forkJoinTaskDemo);
    }
}

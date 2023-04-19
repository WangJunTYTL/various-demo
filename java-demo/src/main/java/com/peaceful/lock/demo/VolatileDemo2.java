package com.peaceful.lock.demo;

import com.peaceful.Util;

/**
 * Created by wangjun on 16/2/26.
 */
public class VolatileDemo2 {

    volatile public Integer x = 0;


    public static void main(String[] args) throws InterruptedException {

        VolatileDemo2 demo2 = new VolatileDemo2();
        for (int i = 0; i < 1000; i++) {
            new Worker(demo2).start();
        }

        Thread.sleep(1000);
        // 最后发现x的值并不是100
        Util.report(demo2.x);
    }


}

class Worker extends Thread {
    VolatileDemo2 demo2;

    public Worker(VolatileDemo2 demo2) {
        this.demo2 = demo2;
    }

    @Override
    public void run() {
        demo2.x++;
        Util.report("hello " + demo2.x);
    }
}

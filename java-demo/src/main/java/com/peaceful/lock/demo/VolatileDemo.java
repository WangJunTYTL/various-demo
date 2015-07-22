package com.peaceful.lock.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/22
 * @since 1.6
 */

public class VolatileDemo {

    volatile boolean isExist;

    public void tryExist() {
        if (isExist == !isExist) {
            System.exit(0);
        }
    }

    public void swapValue() {
        isExist = !isExist;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileDemo test = new VolatileDemo();
        Thread a = new Thread() {
            @Override
            public void run() {
                while (true) {
                    test.tryExist();
                }
            }
        };
        a.start();
        Thread b = new Thread() {
            @Override
            public void run() {
                while (true) {
                    test.swapValue();
                }
            }
        };
        b.start();
        Thread.sleep(1000);
    }


}

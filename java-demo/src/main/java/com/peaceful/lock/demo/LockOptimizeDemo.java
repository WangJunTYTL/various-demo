package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/22
 * @since 1.6
 */

public class LockOptimizeDemo {

    static Object lock = new Object();

    static int x = 0;
    static int t = 100000000;

    /**
     * 锁粗化： 频繁的请求锁资源也会消耗性能
     */
    static public void d1() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < t; i++) {
            synchronized (lock) { // 频繁的请求锁，会消耗很多时间
                x += i;
            }
        }
        Util.report(x);
        Util.report(System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        synchronized (lock) {
            for (int i = 0; i < t; i++) {
                x++;
            }
        }
        Util.report(x);
        Util.report(System.currentTimeMillis() - start);
    }

    public static void main(String[] args) {
        d1();
    }
}

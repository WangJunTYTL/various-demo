package com.peaceful.thread.demo;

import sun.misc.Unsafe;

import java.util.concurrent.locks.LockSupport;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/19
 * @since 1.6
 */

public class T2 {

    static Object lock = new Object();

    public static void main(String[] args) {
        for (;;){
            if (5<6){
                LockSupport.park(lock);
            }
        }
    }
}

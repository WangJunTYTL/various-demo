package com.peaceful.thread.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.locks.LockSupport;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/19
 * @since 1.6
 */

public class T3 {

    public static void main(String[] args) {
        T3 t3 = new T3();
        LockSupport.parkNanos(Thread.currentThread(),5000);
        Util.report("hello");
    }
}

package com.peaceful.lock.demo;

import com.peaceful.common.util.Util;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/7/22
 * @since 1.6
 */

public class NotLockDemo {


    // 无锁 cas 算法 效率高
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Util.report(atomicInteger.getAndIncrement());

        AtomicReference<Long> longAtomicReference = new AtomicReference<Long>();
        longAtomicReference.set(new Long(0));
        Util.report(longAtomicReference.get());
    }
}

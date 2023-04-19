package com.peaceful.lock.demo;

import com.peaceful.Util;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/9/7
 * @since 1.6
 */

public class AtomicBooleanDemo {

    public static void main(String[] args) {
        AtomicBoolean locked = new AtomicBoolean(false);
        Util.report(locked.compareAndSet(false, false));
        Util.report(locked.compareAndSet(false, true));
        //如果当前是true，就把它设置为false
        Util.report(locked.compareAndSet(true, false));

    }
}

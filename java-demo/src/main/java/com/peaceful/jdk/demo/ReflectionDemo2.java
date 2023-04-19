package com.peaceful.jdk.demo;

import com.peaceful.Util;
//import jdk.internal.reflect.Reflection;

/**
 * Created by wangjun on 16/2/28.
 */
public class ReflectionDemo2 {

    public static void main(String[] args) {
//        Util.report(Reflection.getCallerClass());
        Util.report(Thread.currentThread().getContextClassLoader());
    }
}

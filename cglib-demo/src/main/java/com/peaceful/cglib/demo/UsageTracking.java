package com.peaceful.cglib.demo;

import java.lang.reflect.Method;

/**
 * Date 14/11/3.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public interface UsageTracking {

    void costTime(Class clzss,Method method,long time);

}

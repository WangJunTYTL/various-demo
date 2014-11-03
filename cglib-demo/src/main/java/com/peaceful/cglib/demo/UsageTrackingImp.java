package com.peaceful.cglib.demo;

import java.lang.reflect.Method;

/**
 * Date 14/11/3.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class UsageTrackingImp implements UsageTracking{
    @Override
    public void costTime(Class clzss,Method method, long time) {
        System.out.print(clzss.getName()+"."+method.getName()+" cost time "+time+" mis");
    }
}

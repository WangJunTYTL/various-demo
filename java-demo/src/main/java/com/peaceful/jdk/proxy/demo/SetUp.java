package com.peaceful.jdk.proxy.demo;

import com.peaceful.common.util.Util;

import java.lang.reflect.Proxy;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/15
 * @since 1.6
 */

public class SetUp {

    // jdk 提供的代理方式
    public static void main(String[] args) {
        // 代理对象处理类
        SayInvocationHandler sayInvocationHandler = new SayInvocationHandler();
        // 生成被代理对象的实例，实例会是代理对象的实例
        Say say = (Say) Proxy.newProxyInstance(getDefaultClassLoader(), new Class[]{Say.class}, sayInvocationHandler);
        Util.report(say.say());
        //下面这个会报错invoke method is say interface is com.peaceful.jdk.proxy.demo.Say
        // jdk提供的代理方式的缺点是代理类只能是揭开，cglib可以代理任何对象
        // jdk的proxy是通过java的反射机制
        // cglib是通过字节码操作完成，功能更强大，更高级
        // Say2 say2 = (Say2) Proxy.newProxyInstance(getDefaultClassLoader(), new Class[]{Say2.class}, sayInvocationHandler);
        // say2.say();


    }

    private static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {

        }
        if (cl == null) {
            cl = SetUp.class.getClassLoader();
        }
        return cl;
    }
}

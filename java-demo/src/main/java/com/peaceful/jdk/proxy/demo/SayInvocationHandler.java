package com.peaceful.jdk.proxy.demo;

import com.peaceful.common.util.Util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/15
 * @since 1.6
 */

public class SayInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 在运行时期动态的获取代理对象调用的方法和对象
        // 这就是java的反射机制

        if (Object.class == method.getDeclaringClass()) {
            return method.invoke(this, args);
        }

        // 实际对象
        Say say = new Say() {
            @Override
            public String say() {
                return "hello world";
            }
        };
        Util.report("invoke method is "+method.getName()+" interface is "+say.getClass().getInterfaces()[0].getName());
        return method.invoke(say,null);
    }
}

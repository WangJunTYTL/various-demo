package com.peaceful.cglib.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/16
 * @since 1.6
 */

public abstract class TaskSchedule2 {

    static Map<Class, Object> scheduleProcessMap = new IdentityHashMap<Class, Object>();

    static <T> T addScheduleClass(Class<T> tClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class) {

                    return "method:" + method.getName() + " args:" + args;
                } else {
                    method.invoke(this, args);
                }
                return null;
            }
        });
        T t = (T) enhancer.create();
        scheduleProcessMap.put(tClass, t);
        return t;
    }



}

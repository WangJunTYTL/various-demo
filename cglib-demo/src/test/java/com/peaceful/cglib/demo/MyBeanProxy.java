package com.peaceful.cglib.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public class MyBeanProxy {


    public static <T> T testMethodInterceptor(final Class<T> requiredType) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(requiredType);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                if (obj instanceof MyBeanHandle) {
                    if (method.getName().equals("post") || method.getName().equals("after")) {
                        return proxy.invokeSuper(obj, args);
                    } else {
                        obj.getClass().getDeclaredMethod("post", new Class[]{Object.class, Method.class, Object[].class}).invoke(obj, obj, method, args);
                        Object res = proxy.invokeSuper(obj, args);
                        obj.getClass().getDeclaredMethod("after", new Class[]{Object.class, Method.class, Object[].class}).invoke(obj, obj, method, args);
                        return res;
                    }
                }
                return null;
            }
        });
        return (T) enhancer.create();
    }

}

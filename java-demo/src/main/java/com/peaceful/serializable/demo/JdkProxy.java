package com.peaceful.serializable.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class JdkProxy implements BaseProxy {

    private InvocationHandler handler = new ProxyInvokeHandler();

    @Override
    public <T> T getInstance(Class<T> zClass) {
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{zClass}, handler);
    }
}

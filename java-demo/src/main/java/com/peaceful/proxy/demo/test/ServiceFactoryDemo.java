package com.peaceful.proxy.demo.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author <a href="mailto:wangjuntytl@163.com">wangjun</a>
 * @version 1.0 15/12/5
 */
public class ServiceFactoryDemo {

    public static  <T> T lookup(Class clazz) {
        T t = (T) Proxy.newProxyInstance(getDefaultClassLoader(), new Class[]{ clazz}, new InvokeMetaHandler());
        return t;
    }

    private static class InvokeMetaHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            InvokeMeta invokeMeta = new InvokeMeta();
            invokeMeta.claszz = method.getDeclaringClass();
            invokeMeta.method = method.getName();
            invokeMeta.parameterizedTypes = method.getParameterTypes();
            invokeMeta.args = args;

            System.out.println("request remote server");
            Object result = RemoteServerDemo.request(invokeMeta);

            return result;
        }
    }

    private static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        } catch (Throwable ex) {

        }
        if (cl == null) {
            cl = ServiceFactoryDemo.class.getClassLoader();
        }
        return cl;
    }
}

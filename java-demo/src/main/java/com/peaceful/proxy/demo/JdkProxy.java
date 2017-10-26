package com.peaceful.proxy.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by wangjun38 on 2017/7/16.
 */
public class JdkProxy {

    interface Hello {
        void say(String msg);
    }

    static class HelloImpl {
        void say(String msg){
            System.out.println(msg);
        }
    }

    public static void main(String[] args) {
        Hello hello = (Hello) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Hello.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long startTime = System.currentTimeMillis();

                method.invoke(new HelloImpl(),args);

                long costTime = System.currentTimeMillis() - startTime;
                System.out.println("costTime:"+costTime);
                return null;
            }
        });

        hello.say("hi,June");
    }
}

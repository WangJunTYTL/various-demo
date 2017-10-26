package com.peaceful.cglib.demo;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by wangjun38 on 2017/8/1.
 */
public class CglibDemo {

    public static void main(String[] args) throws IOException, InterruptedException {

        // 对接口进行增强
        Hello hello = (Hello) Enhancer.create(Hello.class, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("execute->"+method.getName());
                return null;
            }
        });

        hello.say("hello");

        // 对具体类进行曾强
        HelloImpl hello2 = (HelloImpl) Enhancer.create(HelloImpl.class, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                System.out.println("execute->"+method.getName());
                return null;
            }
        });

        hello2.say("hello");


        boolean flag = true;
        while (flag){
            Thread.sleep(1000);
        }

    }

    interface Hello{

        void say(String msg);
    }



}

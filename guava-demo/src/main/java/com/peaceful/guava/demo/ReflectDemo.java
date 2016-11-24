package com.peaceful.guava.demo;

import com.google.common.reflect.Reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by wangjun on 16/8/5.
 */
public class ReflectDemo {

    public static void main(String[] args) {
        // 和jdk的区别不大
        UserI userI = Reflection.newProxy(UserI.class, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return "hello world!";
            }
        });
        System.out.println(userI.getName());
    }

    public interface UserI {

        public String getName();
    }
}

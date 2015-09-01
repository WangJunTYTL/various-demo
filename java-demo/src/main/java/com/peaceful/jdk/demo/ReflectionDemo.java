package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；
 * 对于任意一个对象，都能够调用它的任意一个方法和属性；
 * 这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
 * <p/>
 * 另外可以看下 Java 内省机制 {@link java.beans.Introspector}
 * <p/>
 * Created by wangjun on 15/2/28.
 */
public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class clazz = Class.forName("com.peaceful.jdk.demo.impl.UserImpl");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Util.report(method.getName());
            if (method.getName().equals("getCurrentUser")) {
                Object result = method.invoke(clazz.newInstance(), null);
                Util.report(result);
            }
        }
    }
}

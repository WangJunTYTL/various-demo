package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wangjun on 15/2/28.
 */
public class ReflectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {

        Class clazz = Class.forName("com.peaceful.jdk.demo.impl.UserImpl");

        Method[] methods = clazz.getDeclaredMethods();
        for(Method method:methods){
            Util.report(method.getName());
            if (method.getName().equals("getCurrentUser")){
                Object result =  method.invoke(clazz.newInstance(), null);
                Util.report(result);
            }
        }

    }
}

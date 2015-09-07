package com.peaceful.jdk.demo;

import com.alibaba.fastjson.JSON;
import com.peaceful.common.util.Util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/16
 * @since 1.6
 */

public class ReflectionDemo {

    interface HelloWorld {

        String say(String str);
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {



        HelloWorld helloWorld = (HelloWorld) Proxy.newProxyInstance(HelloWorld.class.getClassLoader(), new Class[]{HelloWorld.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                Class[] parameterTypes = method.getParameterTypes();
                ReflectionDesc serviceUnit = new ReflectionDesc();
                serviceUnit.setArgs(args);
                serviceUnit.setMethodName(methodName);
                serviceUnit.setParameterTypes(parameterTypes);
                return JSON.toJSONString(serviceUnit);
            }
        });

        String result = helloWorld.say("hello world");
        Util.report(result);
        ReflectionDesc serviceUnit = JSON.parseObject(result, ReflectionDesc.class);
        Class[] parameterTypes = serviceUnit.getParameterTypes();
        Object[] argses = serviceUnit.getArgs();
        String methodName = serviceUnit.getMethodName();
        Util.report(methodName + "\t" + parameterTypes + "\t" + argses);
        Method method = HelloWorld.class.getMethod(methodName, parameterTypes);
        Util.report(method.invoke(new HelloWorld() {
            @Override
            public String say(String str) {
                return str;
            }
        }, argses));

    }

}

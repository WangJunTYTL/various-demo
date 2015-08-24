package com.peaceful.cglib.demo;

import com.peaceful.cglib.demo.test.SampleClass;
import com.peaceful.common.util.Util;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/16
 * @since 1.6
 */

public abstract class TaskSchedule<T> {

    protected T getMethodInfo() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getClass());
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class) {
                    Class returnType = method.getReturnType();
                    if (returnType.equals(String.class)){

                    }else{
                        Util.report("returnType:"+returnType);
                    }
                    return "method:" + method.getName() + "args:" + args;
                } else {
                    method.invoke(this, args);
                }
                return null;
            }
        });
        return (T) enhancer.create();
    }

    protected T addSchduleClass(Class<T> tClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.getClass());
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class) {
                    Class returnType = method.getReturnType();
                    if (returnType.equals(String.class)){

                    }else{
                        Util.report("returnType:"+returnType);
                    }
                    return "method:" + method.getName() + "args:" + args;
                } else {
                    method.invoke(this, args);
                }
                return null;
            }
        });
        return (T) enhancer.create();
    }



    protected String commit(String queueName, String methodInfo) {
        Task task = new Task();
        task.queueName = queueName;
        task.aClass = this.getClass();
        task.ext=methodInfo;
        Util.report(task);
        return "123456";
    }

}

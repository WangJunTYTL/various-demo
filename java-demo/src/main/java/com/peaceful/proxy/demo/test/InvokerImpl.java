package com.peaceful.proxy.demo.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author <a href="mailto:wangjuntytl@163.com">wangjun</a>
 * @version 1.0 15/12/5
 */
public class InvokerImpl implements Invoker {

    @Override
    public Object doInvoke(InvokeMeta meta) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Object instance = BeanContainerDemo.getBean(meta.claszz);
        Method method = meta.claszz.getMethod(meta.method, meta.parameterizedTypes);
        Object result = method.invoke(instance, meta.args);
        return result;
    }

}

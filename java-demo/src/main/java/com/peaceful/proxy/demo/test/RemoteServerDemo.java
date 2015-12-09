package com.peaceful.proxy.demo.test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <a href="mailto:wangjuntytl@163.com">wangjun</a>
 * @version 1.0 15/12/5
 */
public class RemoteServerDemo {

    public static Object request(InvokeMeta meta) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Invoker invoker = new InvokerImpl();
        Object result = invoker.doInvoke(meta);
        return result;
    }

}

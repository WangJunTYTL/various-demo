package com.peaceful.proxy.demo.test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author <a href="mailto:wangjuntytl@163.com">wangjun</a>
 * @version 1.0 15/12/5
 */
public interface Invoker {

    Object doInvoke(InvokeMeta meta) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;

}

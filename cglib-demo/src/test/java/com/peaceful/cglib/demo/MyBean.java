package com.peaceful.cglib.demo;

import com.peaceful.common.util.Util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public class MyBean implements MyBeanHandle {

    public void test() {
        Util.report("test--> exe");
    }

    @Override
    public boolean post(Object obj, Method method, Object[] args) {
        Util.report("post->exe method " + method.getName() + " start");
        return true;
    }

    @Override
    public boolean after(Object obj, Method method, Object[] args) {
        Util.report("after--> ext method " + method.getName() + " end");
        return true;
    }
}

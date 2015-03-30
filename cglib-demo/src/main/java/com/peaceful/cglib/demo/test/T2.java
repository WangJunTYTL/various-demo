package com.peaceful.cglib.demo.test;

import com.peaceful.common.util.Util;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public class T2 {

    public static void main(String[] args) {
        t1();
//        t2();
//        t3();
    }

    static void t1() { // 替代原先的返回值
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new FixedValue() { // 返回固定的值
            @Override
            public Object loadObject() throws Exception {
                return "hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create(); // 返回SampleClass的之类
//        enhancer.createClass(); If you only want to create a class, but no instance, Enhancer#createClass
        Util.report(" proxy class's super class is -->" + proxy.getClass().getSuperclass());
        Util.report("SampleClass lost function ->" + proxy.test(" go go ")); // SampleClass的say方法已被替换
        Util.report("SampleClass lost function ->" + proxy.say2(" go go ")); // SampleClass的say方法已被替换
//        Util.report("SampleClass lost function ->" + proxy.hashCode());
// proxy.hashCode() result in a ClassCastException since the FixedValue interceptor always returns a String even though
// the Object#hashCode signature requires a primitive integer.
    }

    static void t2() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {
            //            This callback allows us to answer with regards to the invoked method.
// However, you should be careful when calling a method on the proxy object
// that comes with the InvocationHandler#invoke method. All calls on this method will
// be dispatched with the same InvocationHandler and might therefore result in an endless loop.
// In order to avoid this, we can use yet another callback dispatcher
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "Hello cglib!";
                } else {
                    throw new RuntimeException("Do not know what to do.");
                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        Util.report(proxy.test("123"));
    }

    static void t3() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
                    throws Throwable {
                if (method.getDeclaringClass() == Object.class && method.getReturnType() == String.class) {
                    Util.report("method.getDeclaringClass-->" + method.getDeclaringClass());
                    return "Hello cglib!";
                } else {
                    Util.report("在调用之前我可以做些事情");
                    Object res = proxy.invokeSuper(obj, args);
                    Util.report(res);
                    Util.report("在调用之后我可以做些事情");
                    return res;

                }
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        Util.report(proxy instanceof SampleClass);
        Util.report(proxy.getClass());
        Util.report(proxy.test("123"));
    }

}

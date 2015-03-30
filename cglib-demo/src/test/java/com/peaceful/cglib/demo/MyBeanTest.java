package com.peaceful.cglib.demo;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public class MyBeanTest {

    @org.junit.Test
    public void test() throws Exception {
        MyBean myBean = MyBeanProxy.testMethodInterceptor(MyBean.class);
        myBean.test();
    }
}

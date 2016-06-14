package com.peaceful.demo.spring.boot.common;

import org.springframework.context.ApplicationContext;

/**
 * 程序全局上下文信息：比如你可以通过{@link AppContext#spring}获取ApplicationContext对象
 *
 * @author WangJun
 * @version 1.0 16/6/4
 */
public class AppContext {

    public static ApplicationContext spring;
    protected static void setSpring(ApplicationContext spring) {
        AppContext.spring = spring;
    }

}

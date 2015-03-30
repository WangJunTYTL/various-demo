package com.peaceful.cglib.demo;

import java.lang.reflect.Method;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public interface MyBeanHandle {

    boolean post(Object obj,Method method,Object[] args);
    boolean after(Object obj,Method method,Object[] args);
}

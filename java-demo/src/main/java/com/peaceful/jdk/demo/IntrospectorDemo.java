package com.peaceful.jdk.demo;

import com.peaceful.Util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

/**
 * introspection
 *
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/6
 * @since 1.6
 */

public class IntrospectorDemo {

    //内省(Introspector) 是Java 语言对 JavaBean 类属性、事件的一种缺省处理方法。
    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo
                (EleA.class);
        Util.report(info.getPropertyDescriptors()[0]);
        Util.report(info);
    }
}

package com.peaceful.jdk.demo;

import com.peaceful.common.util.Util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/6
 * @since 1.6
 */

public class IntrospectorTest {


    public static void main(String[] args) throws IntrospectionException {
        BeanInfo info = Introspector.getBeanInfo
                (EleA.class);
        Util.report(info);
    }
}

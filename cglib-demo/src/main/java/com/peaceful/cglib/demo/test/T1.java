package com.peaceful.cglib.demo.test;

import com.peaceful.common.util.Util;
import net.sf.cglib.core.DefaultGeneratorStrategy;
import net.sf.cglib.proxy.Enhancer;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/3/29.
 */

public class T1 {

    public static void main(String[] args) {
        Enhancer e = new Enhancer();
        e.setSuperclass(T1Inte.class);
        // etc.
        e.setStrategy(new DefaultGeneratorStrategy() {
            protected byte[] transform(byte[] b) {
                // do something with bytes here
                Util.report(new String(b));
                return b;
            }
        });


//        Object obj = e.create();
//        Util.report(obj.getClass());
    }
}

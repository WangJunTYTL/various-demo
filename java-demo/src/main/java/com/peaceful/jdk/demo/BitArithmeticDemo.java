package com.peaceful.jdk.demo;

import com.peaceful.Util;

/**
 * Java 位运算,以二进制的方式运算
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 15/12/8
 */
public class BitArithmeticDemo {

    public static void main(String[] args) {

        Util.report("01左移1位" + (1 << 1));
        Util.report("01右移1位" + (1 >> 1));
        // 只有同时为1 才为1
        Util.report("01和01进行与运算" + (1 & 1));
        // 只要有一个为1 就为 1
        Util.report("01和01进行或运算" + (1 | 1));
    }

}

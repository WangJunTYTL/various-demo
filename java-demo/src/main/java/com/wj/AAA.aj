package com.wj;

/**
 * Date 14-9-7.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public aspect AAA {
    pointcut greeting(): execution(*BBB.say(..));
    after() returning(): greeting() {
        System.out.println(" World!");
    }
}

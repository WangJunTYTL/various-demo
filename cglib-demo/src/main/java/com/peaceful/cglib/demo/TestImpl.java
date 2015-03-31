package com.peaceful.cglib.demo;

/**
 * Date 14/11/3.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
@Deprecated
public class TestImpl implements Test {
    @Override
    public String say() {
        return ("hello world");
    }
}

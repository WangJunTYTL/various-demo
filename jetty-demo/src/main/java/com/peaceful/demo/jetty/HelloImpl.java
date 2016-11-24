package com.peaceful.demo.jetty;

/**
 * Created by wangjun on 2016/11/12.
 */
public class HelloImpl implements Hello{

    public String say(String msg) {
        return "You Say:" + msg;
    }
}

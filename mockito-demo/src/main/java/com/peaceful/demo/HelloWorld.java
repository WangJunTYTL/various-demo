package com.peaceful.demo;

/**
 * Created by wangjun38 on 2019-11-20.
 */
public class HelloWorld {

    public String say(String msg) {
        if (msg == null || msg.equalsIgnoreCase("")){
            System.out.println("msg is empty!");
        }else {
            System.out.println(msg);
        }
        return msg;
    }
}

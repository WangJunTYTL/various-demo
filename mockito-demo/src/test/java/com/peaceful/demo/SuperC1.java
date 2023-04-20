package com.peaceful.demo;

/**
 * Created by wangjun38 on 2020/7/25.
 */
public abstract class SuperC1 {

    public String say(String msg){
        System.out.println(this.getClass().getSimpleName()+"-->"+msg);
        return msg;
    }
}

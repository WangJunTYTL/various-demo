package com.peaceful.demo;

/**
 * Created by wangjun38 on 2020/7/25.
 */
public class ChildC1 extends SuperC1 {

    public String sayHi(String msg) {
        System.out.println(this.getClass().getSimpleName() + "-->" + msg);
        return "Hi-->"+msg;
    }
}

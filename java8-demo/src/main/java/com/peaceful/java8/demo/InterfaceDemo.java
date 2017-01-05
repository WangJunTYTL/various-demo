package com.peaceful.java8.demo;

/**
 * Created by JunWang on 2017/1/5.
 */
public interface InterfaceDemo {

    void  out(String msg);

    // Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法
    default void out(){
        System.out.println("hello world");
    }
}

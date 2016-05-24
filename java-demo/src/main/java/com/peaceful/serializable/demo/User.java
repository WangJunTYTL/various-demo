package com.peaceful.serializable.demo;


import java.io.Serializable;

/**
 * Created by wangjun on 16/3/2.
 */
public class User implements Serializable{

    public User(){

    }

    public User(String name, int age) {
        this.age = age;
        this.name = name;
    }
    public String name;
    private int age;
    protected int sex;
    public static String like = "pingpang";
    public final static String like2 = "foot";


    @Override
    public String toString() {
        return name+" "+age+" "+sex+" "+like+" "+like2;
    }
}

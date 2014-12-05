package com.peaceful.jdk.demo.impl;

/**
 * Created by wangjun on 14/11/29.
 */
public class Student implements Cloneable {

    public int id;
    private String name;


    public Student clone() throws CloneNotSupportedException {
     return (Student) super.clone();
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

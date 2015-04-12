package com.peaceful.jdk.demo.impl;

import com.peaceful.jdk.demo.EleA;

/**
 * Created by wangjun on 14/11/29.
 */
public class Student implements Cloneable {

    public int id;
    private String name;
    public EleA eleA; //clone时只会把该类的引用地址复制

    public Student(){
        eleA = new EleA();
    }


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

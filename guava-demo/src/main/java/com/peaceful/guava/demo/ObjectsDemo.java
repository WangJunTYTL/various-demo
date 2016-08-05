package com.peaceful.guava.demo;

import com.google.common.base.Objects;

/**
 * Created by wangjun on 16/8/5.
 */
public class ObjectsDemo {
    public static void main(String[] args) {
        System.out.println(Objects.equal("a","a"));
        System.out.println(Objects.equal("a","b"));
        System.out.println(Objects.equal("a",null));
        System.out.println(Objects.equal(null,null));
        System.out.println(Objects.equal("1",1));
    }
}

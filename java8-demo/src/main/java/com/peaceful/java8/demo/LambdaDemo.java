package com.peaceful.java8.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * http://www.jb51.net/article/48304.htm
 * Created by JunWang on 2017/1/5.
 */
public class LambdaDemo {

    // Lambda表达式基于数学中的λ演算得名，直接对应于其中的lambda抽象(lambda abstraction)，是一个匿名函数，即没有函数名的函数
    public static void main(String[] args) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        // 在1.8之前是通过匿名函数的方式
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        names = Arrays.asList("peter", "anna", "mike", "xenia");
        // 在Java 8 中你就没必要使用这种传统的匿名对象的方式了，Java 8提供了更简洁的语法，lambda表达式
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        // lambda可以自动推导类型
        Collections.sort(names, (a,b)->b.compareTo(a));
        System.out.println(names);

    }

}

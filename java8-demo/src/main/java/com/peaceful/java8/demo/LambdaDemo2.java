package com.peaceful.java8.demo;

/**
 * Created by JunWang on 2017/1/5.
 */
public class LambdaDemo2 {

    interface  Converter<F,T>{
        T convert(F from);
    }

    private static Converter<String ,Integer> converter = (from -> Integer.parseInt(from));
    //Lambda表达式是如何在java的类型系统中表示的呢？每一个lambda表达式都对应一个类型，通常是接口类型。而“函数式接口”是指仅仅只包含一个抽象方法的接口，
    // 每一个该类型的lambda表达式都会被匹配到这个抽象方法。因为 默认方法 不算抽象方法，所以你也可以给你的函数式接口添加默认方法
    public static void main(String[] args) {
        System.out.println(converter.convert("1234"));
    }
}

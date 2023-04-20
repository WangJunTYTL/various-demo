package com.peaceful.java8;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by wangjun38 on 2020/7/21.
 */
public class LambdaTest {

    public static void main(String[] args) {
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;

        List<Integer> integerList = Lists.newArrayList(1, 2, 3);

        integerList.forEach(x->System.out.println(x));

    }

    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
    }

    private static int sum(int x, int y) {
        return x + y;
    }


}

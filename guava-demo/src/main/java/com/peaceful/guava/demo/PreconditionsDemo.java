package com.peaceful.guava.demo;


import com.google.common.base.Preconditions;

/**
 * @author WangJun
 * @version 1.0 16/6/17
 */
public class PreconditionsDemo {

    public static void main(String[] args) {
        Preconditions.checkArgument(args == null);
    }
}

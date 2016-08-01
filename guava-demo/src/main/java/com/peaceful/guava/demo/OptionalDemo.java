package com.peaceful.guava.demo;


import com.google.common.base.Optional;

/**
 * @author WangJun
 * @version 1.0 16/6/17
 */
public class OptionalDemo {

    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        System.out.println(possible.isPresent());
        possible = Optional.absent();
        System.out.println(possible.orNull());
    }
}

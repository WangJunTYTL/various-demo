package com.peaceful.guava.demo;


import com.google.common.base.Optional;
import com.google.common.base.Throwables;

/**
 * @author WangJun
 * @version 1.0 16/6/17
 */
public class ThrowablesDemo {

    public static void main(String[] args) {

        try{
            int x = 1/0;
        }catch (Exception e){
            Throwables.propagate(e);
        }




    }
}

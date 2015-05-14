package com.peaceful.design.mode.observable;

import com.peaceful.design.mode.Teacher;

import java.util.Observable;

/**
 * @author wangjun
 * @version 1.0
 * @since 15/5/8.
 */

public class SetUp {

    public static void main(String[] args) {
        StudentA a = new StudentA();
        StudentB b = new StudentB();

        Teacher teacher = new Teacher();
        teacher.addObserver(a);
        teacher.addObserver(b);
        teacher.set("hello everyone");
//        Event event= new Event();
        teacher.notifyObservers("hello everyone");

    }
}

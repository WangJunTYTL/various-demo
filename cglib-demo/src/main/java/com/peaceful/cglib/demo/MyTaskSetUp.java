package com.peaceful.cglib.demo;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/8/16
 * @since 1.6
 */

public class MyTaskSetUp {

    public static void main(String[] args) {

        MyTask myTask = new MyTask();

        myTask.commit("queue", myTask.getMethodInfo().say("123"));

        HelloWorld helloWorld = TaskSchedule2.addScheduleClass(HelloWorld.class);
        helloWorld.say("hello world","test");


    }
}

package com.peaceful.serializable.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WangJun
 * @version 1.0 16/4/15
 */
public class HelloProxy {


    public static void main(String[] args) {
        BaseProxy baseProxy = new JdkProxy();
        Hello hello = baseProxy.getInstance(Hello.class);


        List<User> users = new ArrayList<User>();
        User user01 = new User("01",16);
        User user02 = new User("02",16);
        users.add(user01);
        users.add(user02);
        hello.say(users);

    }
}

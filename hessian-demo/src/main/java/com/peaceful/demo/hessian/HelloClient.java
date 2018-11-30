package com.peaceful.demo.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangjun38 on 2018/8/23.
 */
public class HelloClient {

    public static void main(String[] args) throws MalformedURLException {
        String url = "http://127.0.0.1:8787/hessian";
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
        HelloService basic = (HelloService) factory.create(HelloService.class, url);
        System.out.println(basic.hello());
        System.out.println(basic.hello2("你好"));
        System.out.println(basic.hello3(66));
        List<String> msgList = new ArrayList<String>();
        msgList.add("a");
        msgList.add("a");
        msgList.add("c");
        System.out.println(basic.hello3(msgList));
        User user = new User();
        user.setName("JJ");
        System.out.println(basic.hello(user).getName());
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        System.out.println(basic.hello(userList));

        basic.hello4();
    }
}

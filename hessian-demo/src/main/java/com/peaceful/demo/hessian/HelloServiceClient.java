package com.peaceful.demo.hessian;

import com.caucho.hessian.client.HessianProxyFactory;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jun on 2018/8/23.
 */
public class HelloServiceClient {

    public static void main(String[] args) throws MalformedURLException {
        String url = "http://127.0.0.1:8787/hessian";
        HessianProxyFactory factory = new HessianProxyFactory();
        factory.setOverloadEnabled(true);
        HelloServiceDef basic = (HelloServiceDef) factory.create(HelloServiceDef.class, url);
        System.out.println(basic.hello());
        System.out.println(basic.hello2("你好"));
        System.out.println(basic.hello3(66));
        List<String> msgList = new ArrayList<String>();
        msgList.add("a");
        msgList.add("a");
        msgList.add("c");
        System.out.println(basic.hello3(msgList));
        UserTO user = new UserTO();
        user.setName("JJ");
        System.out.println(basic.hello(user).getName());
        List<UserTO> userList = new ArrayList<UserTO>();
        userList.add(user);
        System.out.println(basic.hello(userList));

        basic.hello4();
    }
}

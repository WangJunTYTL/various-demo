package com.peaceful.demo.hessian;

import java.util.List;

/**
 * Created by wangjun38 on 2018/8/23.
 */
public interface HelloService {

    String hello();

    String hello2(String msg);

    int hello3(int num);

    List<String> hello3(List<String> msgList);

    User hello(User user);

    List<User> hello(List<User> userList);

    void hello4();
}

package com.peaceful.demo.hessian;

import java.util.List;

/**
 * Created by Jun on 2018/8/23.
 */
public interface HelloServiceDef {

    String hello();

    String hello2(String msg);

    int hello3(int num);

    List<String> hello3(List<String> msgList);

    UserTO hello(UserTO user);

    List<UserTO> hello(List<UserTO> userList);

    void hello4();
}

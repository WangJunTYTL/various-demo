package com.peaceful.demo.hessian;

import com.caucho.hessian.server.HessianServlet;

import java.util.List;

/**
 * Created by Jun on 2018/8/23.
 */
public class HelloServiceDefImpl extends HessianServlet implements HelloServiceDef {

    public String hello() {
        return "Hi";
    }

    public String hello2(String msg) {
        return msg;
    }

    public int hello3(int num) {
        return num;
    }

    public List<String> hello3(List<String> msgList) {
        return msgList;
    }

    public UserTO hello(UserTO user) {
        return user;
    }

    public List<UserTO> hello(List<UserTO> userList) {
        return userList;
    }

    public void hello4() {
        throw new RuntimeException("Error Msg");
    }
}

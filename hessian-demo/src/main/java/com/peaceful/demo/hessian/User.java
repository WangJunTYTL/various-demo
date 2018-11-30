package com.peaceful.demo.hessian;

import java.io.Serializable;

/**
 * Created by wangjun38 on 2018/8/23.
 */
public class User implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

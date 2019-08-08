package com.peaceful.demo.hessian;

import java.io.Serializable;

/**
 * Created by Jun on 2018/8/23.
 */
public class UserTO implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

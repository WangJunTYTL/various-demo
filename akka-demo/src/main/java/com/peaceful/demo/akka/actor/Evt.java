package com.peaceful.demo.akka.actor;

import java.io.Serializable;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/6/23
 * @since 1.6
 */


class Evt implements Serializable {
    private final String data;

    public Evt(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
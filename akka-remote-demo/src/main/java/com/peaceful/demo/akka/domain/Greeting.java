package com.peaceful.demo.akka.domain;

import java.io.Serializable;

/**
 * Created by wangjun on 15/1/17.
 */
public class Greeting implements Serializable {
    public final String who;

    public Greeting(String who) {
        this.who = who;
    }
}

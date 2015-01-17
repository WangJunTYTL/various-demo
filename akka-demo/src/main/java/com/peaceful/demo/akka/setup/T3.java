package com.peaceful.demo.akka.setup;

import akka.actor.Props;
import akka.japi.Creator;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.actor.GreetActor2;

import java.util.Date;

/**
 * 创建actor的几种方式
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class T3 {
    static class GreetActor2C implements Creator<GreetActor2> {

        @Override
        public GreetActor2 create() throws Exception {
            return new GreetActor2(new Date());
        }
    }

    public static void main(String[] args) {

        //无参构造
        Props props1 = Props.create(GreetActor.class);
        //有参构造
        Props props2 = Props.create(GreetActor2.class, new Date());
        //通过静态类构造
        Props props3 = Props.create(new GreetActor2C());

    }
}



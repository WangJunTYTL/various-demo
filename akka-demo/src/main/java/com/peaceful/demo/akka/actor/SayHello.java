package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;

/**
 * @author WangJun
 * @version 1.0
 */
public class SayHello extends UntypedActor {

    @Override
    public void onReceive(Object msg) throws Exception {
        // 编写你自己的业务
        if (msg instanceof String) {
            System.out.println(msg);
        }
    }

}

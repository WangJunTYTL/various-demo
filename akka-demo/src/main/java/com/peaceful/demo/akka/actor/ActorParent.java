package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 *
 *
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/5
 */
public class ActorParent extends UntypedActor {

    // 创建子Actor的方法,Context.actorOf(...)
    ActorRef child = getContext().actorOf(Props.create(ActorChild.class), "child");

    @Override
    public void onReceive(Object message) throws Exception {
    }
}

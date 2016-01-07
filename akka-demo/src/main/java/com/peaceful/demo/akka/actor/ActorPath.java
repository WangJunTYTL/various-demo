package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/5
 */
public class ActorPath extends UntypedActor {

    LoggingAdapter log =  Logging.getLogger(getContext().system(),this);
    ActorRef child = getContext().actorOf(Props.create(ActorChild.class),"child");

    @Override
    public void onReceive(Object message) throws Exception {
        log.info("{}",message);
        // getContext().child("xx");
        // getContext().parent();
    }
}

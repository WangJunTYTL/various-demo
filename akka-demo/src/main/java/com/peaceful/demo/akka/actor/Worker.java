package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;

/**
 * Created by wangjun on 15/3/9.
 */
public class Worker extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    @Override
    public void onReceive(Object o) throws Exception {

        ActorRef sender = getSender(); //message sender
        ActorRef self = getSelf(); // message receiver

        log.debug("sender is {}", sender.path().toSerializationFormat());
        log.debug("receiver is {}", self.path().toSerializationFormat());

        log.debug("start work ...");
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        log.debug(getSelf().path().name() + " preRestart");
        super.preRestart(reason, message);
    }


    @Override
    public void postRestart(Throwable reason) throws Exception {
        log.debug(getSelf().path().name() + " postRestart");
        super.postRestart(reason);
    }


    @Override
    public void postStop() throws Exception {
        log.debug(getSelf().path().name() + " postStop");
        super.postStop();
    }

}

package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;
import scala.Option;

/**
 * Created by wangjun on 15/3/8.
 */
public class ChildOfGreetActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {

        ActorRef sender = getSender(); //message sender
        ActorRef self = getSelf(); // message receiver
        log.debug("sender is {}", sender.path().toSerializationFormat());
        log.debug("receiver is {}", self.path().toSerializationFormat());
        log.info("message content is {}", message);
        getSender().tell(new Greeting(getSelf().path().name() + " receive message"), getSelf()); // tell sender , it see message
        if (message.equals("500"))
            throw new Exception("500");
        else if (message.equals("302"))
            throw new ArithmeticException("302");
        else
            unhandled(message);
    }

    // actor lifecycle method
    @Override
    public void preStart() throws Exception {
        log.debug(getSelf().path().toSerializationFormat() + " preStart");
        super.preStart();
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        log.debug(getSelf().path().toSerializationFormat() + " preRestart");
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

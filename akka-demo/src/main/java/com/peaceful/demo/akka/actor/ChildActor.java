package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * Created by wangjun on 15/3/6.
 */
public class ChildActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    final ActorRef child = //  this actor 下创建children
            getContext().actorOf(Props.create(GreetActor.class), "myChild");

    final ActorRef child2 = //  this actor 下创建children
            getContext().actorOf(Props.create(GreetActor.class), "myChild2");

    @Override
    public void onReceive(Object o) throws Exception {


        log.info("T4Actor receive msg is " + o);
        if (o.equals("1"))
            child.tell(new Greeting("wj"), ActorRef.noSender());
        else if (o.equals("ok"))
            log.info("child2 has receive msg");
        else {
            child2.tell(new Greeting("jj"), child2);
            //getSelf reference to the ActorRef of the actor
            //getSender reference sender Actor of the last received message,
            // typically used as described in Reply to messages
            getSelf().tell("ok", getSelf()); //自己给自己发送消息，告诉自己已经给child2发送过消息
        }

    }
    // plus some behavior ...

}

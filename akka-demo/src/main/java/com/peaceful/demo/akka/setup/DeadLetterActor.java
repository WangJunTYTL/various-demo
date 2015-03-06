package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.japi.Creator;
import com.peaceful.demo.akka.actor.GreetActor;
import com.peaceful.demo.akka.actor.GreetActor2;

import java.util.Date;

/**
 * Props is a configuration class to specify options for the creation of actors,
 * think of it as an immutable and thus freely shareable recipe for creating an actor including associated deployment information (e.g. which dispatcher to use, see more below). Here are some examples of how to create a Props instance.
 * 创建actor的几种方式
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class DeadLetterActor extends UntypedActor {

    //想deadLetter mailbox发消息
    ActorRef lastSender = getContext().system().deadLetters();

    @Override
    public void onReceive(Object o) throws Exception {
        lastSender.tell("kill", getSelf());
    }

}



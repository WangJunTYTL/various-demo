package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;

/**
 * Since Akka enforces parental supervision every actor is supervised and (potentially)
 * the supervisor of its children, it is advisable that you familiarize yourself
 * with Actor Systems and Supervision and Monitoring and it may also help to read Actor References, Paths and Addresses.
 *
 * 创建actor
 *
 * Created by wangjun on 15/1/17.
 */
public class GreetActor extends UntypedActor { // 创建一个actor 要实现 UntypedActor

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    final ActorRef child = //  this actor 下创建children
            getContext().actorOf(Props.create(GreetActor.class), "myChild");


    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting)
            log.info("Hello " + ((Greeting) message).who);
    }
}

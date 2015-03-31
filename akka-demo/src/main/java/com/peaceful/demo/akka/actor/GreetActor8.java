package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;
import scala.concurrent.duration.Duration;

/**
 * setReceiveTimeout
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class GreetActor8 extends UntypedActor { // 创建一个actor 要实现 UntypedActor

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);


    ActorRef child1 = getContext().actorOf(Props.create(GreetActor.class));
    ActorRef child2 = getContext().actorOf(Props.create(GreetActor.class));
    ActorRef child3 = getContext().actorOf(Props.create(GreetActor.class));
    ActorRef child4 = getContext().actorOf(Props.create(GreetActor.class));
    ActorRef child5 = getContext().actorOf(Props.create(GreetActor.class));

    private int i = 0;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting) {
            // todo 负载平衡是怎么做的 此处只是模拟 5 个actor轮询处理任务
            if (i % 5 == 0) {
                i = 0;
                i++;
                child1.forward("进入1", getContext());
            } else if (i % 5 == 1) {
                i++;
                child2.forward("进入2", getContext());

            } else if (i % 5 == 2) {
                i++;
                child3.forward("进入3", getContext());
            } else if (i % 5 == 3) {
                i++;
                child4.forward("进入4", getContext());
            } else if (i % 5 == 4) {
                i++;
                child5.forward("进入5", getContext());
            } else {
                i++;
                child1.forward("进入1", getContext());
            }
        }

    }

    @Override
    public void postStop() throws Exception {
        log.info(getSelf().path().name() + " stop !!!");
        super.postStop();
    }
}

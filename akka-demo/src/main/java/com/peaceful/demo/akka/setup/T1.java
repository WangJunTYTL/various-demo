package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import com.peaceful.common.util.Util;
import com.peaceful.demo.akka.actor.GreetActor;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * 测试最小控制单元actor 执行顺序
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class T1 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        ActorRef target = system.actorOf(Props.create(GreetActor.class));
        final Inbox inbox = Inbox.create(system);
        inbox.send(target, "hello");
        inbox.receive(Duration.create(1, TimeUnit.MINUTES));
        //assert inbox.receive(Duration.create(1, TimeUnit.SECONDS)).equals("world");
    }
}



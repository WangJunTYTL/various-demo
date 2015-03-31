package com.peaceful.demo.akka.actor;

import akka.actor.*;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.japi.Function;
import com.peaceful.demo.akka.domain.Greeting;
import scala.Option;
import scala.concurrent.duration.Duration;

/**
 * this is a simple actor
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class GreetActor extends UntypedActor { // 创建一个actor 要实现 UntypedActor


    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    //A path in an actor system represents a "place" which might be occupied by a living actor.
    // Initially (apart from system initialized actors) a path is empty.
    // When actorOf() is called it assigns an incarnation of the actor described by the passed Props to the given path.
    // An actor incarnation is identified by the path and a UID.
    // A restart only swaps theActor instance defined by the Props but the incarnation and hence the UID remains the same.

    //final ActorRef child = //  在actor内部创建自己的child，会引起无线递归，但系统不会发出警告，需要我们自已注意
    //      getContext().actorOf(Props.create(GreetActor.class), "myChild");
    ActorRef child = getContext().actorOf(Props.create(ChildOfGreetActor.class));
    // ActorRef 代表一个actor实例

    // actor 有消息时要调用的方法
    @Override
    public void onReceive(Object message) throws Exception {

        ActorRef sender = getSender(); //message sender
        ActorRef self = getSelf(); // message receiver

        log.debug("sender is {}", sender.path().toSerializationFormat());
        log.debug("receiver is {}", self.path().toSerializationFormat());

        // 处理消息
        if (message instanceof Greeting)
            log.info("Hello " + ((Greeting) message).who);
        else if (message instanceof String) {
            child.forward(message, getContext()); // 消息被转发给child处理
        } else
            unhandled(message);

    }

    // actor lifecycle method
    @Override
    public void preStart() throws Exception {
        log.debug(getSelf().path().name() + " preStart");
        super.preStart();
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

    // manage child strategy
    @Override
    public SupervisorStrategy supervisorStrategy() {
        SupervisorStrategy strategy = new OneForOneStrategy(
                10, Duration.create("1 minute"), new Function<Throwable, SupervisorStrategy.Directive>() {
            @Override
            public SupervisorStrategy.Directive apply(Throwable t) {
                if (t instanceof ArithmeticException) return SupervisorStrategy.resume();
                else if (t instanceof NullPointerException) return SupervisorStrategy.restart();
                else if (t instanceof Exception)
                    return SupervisorStrategy.stop(); // can’t receive message when actor stop,the message will dump to dead letters
                else return SupervisorStrategy.escalate();
            }
        });
        return strategy;
    }
}

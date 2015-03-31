package com.peaceful.demo.akka.actor;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.domain.Greeting;
import scala.Option;

/**
 * Since Akka enforces parental supervision every actor is supervised and (potentially)
 * the supervisor of its children, it is advisable that you familiarize yourself
 * with Actor Systems and Supervision and Monitoring and it may also help to read Actor References, Paths and Addresses.
 * <p/>
 * <p/>
 * <p/>
 * 创建actor
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class TestLifecycleActor extends UntypedActor { // 创建一个actor 要实现 UntypedActor

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    //The method preStart() of an actor is only called once directly during the initialization of the first instance,
    // that is, at creation of its ActorRef. In the case of restarts, preStart() is called from postRestart(),
    // therefore if not overridden, preStart() is called on every incarnation.
    // However, overriding postRestart() one can disable this behavior,
    // and ensure that there is only one call to preStart().

    //One useful usage of this pattern is to disable creation of new ActorRefs for children during restarts.
    // This can be achieved by overriding preRestart():

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting)
            log.info("Hello " + ((Greeting) message).who);
        getContext().stop(getSelf()); // actor stop
        getSelf().tell("hello", ActorRef.noSender()); // 此处消息会被发送到deadLetter mailbox
    }

    @Override
    public void preStart() throws Exception {
        log.info("preStart");
        super.preStart();
        // Initialize children here
    }

    // The default implementation of preRestart() stops all the children
    // of the actor. To opt-out from stopping the children, we
// have to override preRestart()
    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        log.info("preRestart");
// Keep the call to postStop(), but no stopping of children
        postStop();
    }

    // Overriding postRestart to disable the call to preStart()
    // after restarts
    @Override
    public void postRestart(Throwable reason) throws Exception {
        super.postRestart(reason);
    }

    @Override
    public void postStop() throws Exception {
        log.info("postStop");
        super.postStop();
    }
}

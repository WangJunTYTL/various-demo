package com.peaceful.demo.akka.actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import scala.Option;
import scala.PartialFunction;
import scala.runtime.BoxedUnit;

/**
 * @author WangJun
 * @version 1.0 16/3/29
 */
public class ActorLifeCycle extends UntypedActor {

    LoggingAdapter log =  Logging.getLogger(getContext().system(),this);

    @Override
    public void onReceive(Object o) throws Exception {

    }


    @Override
    public void aroundReceive(PartialFunction<Object, BoxedUnit> receive, Object msg) {
        log.info("aroundReceive");
        super.aroundReceive(receive, msg);
    }

    @Override
    public void aroundPreStart() {
        log.info("aroundPreStart");
        super.aroundPreStart();
    }

    @Override
    public void aroundPostStop() {
        log.info("aroundPostStop");
        super.aroundPostStop();
    }

    @Override
    public void aroundPreRestart(Throwable reason, Option<Object> message) {
        log.info("aroundPreRestart");
        super.aroundPreRestart(reason, message);
    }

    @Override
    public void aroundPostRestart(Throwable reason) {
        log.info("aroundPostRestart");
        super.aroundPostRestart(reason);
    }


    @Override
    public void preStart() throws Exception {
        log.info("preStart");
        super.preStart();
    }

    @Override
    public void postStop() throws Exception {
        log.info("postStop");
        super.postStop();
    }

    @Override
    public void preRestart(Throwable reason, Option<Object> message) throws Exception {
        log.info("preRestart");
        super.preRestart(reason, message);
    }

    @Override
    public void postRestart(Throwable reason) throws Exception {
        log.info("postRestart");
        super.postRestart(reason);
    }
}

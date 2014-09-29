/*
 * Copyright (c) 2014. Create by WangJun &  Leader ZhaoWeiQiang
 */

package actor;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Created by wangjun on 14-7-8.
 * 定时更新AdOrder Actor
 */
public class MyActor extends UntypedActor {
    LoggingAdapter log = Logging.getLogger(getContext().system(), this); //log 必须这样写

    public void onReceive(Object message) throws Exception {
        if (message.equals("AdOrderScheduling")){
            log.info("计划任务开始..." );
            Scheduling.adOrderScheduling();
        }
    }
}
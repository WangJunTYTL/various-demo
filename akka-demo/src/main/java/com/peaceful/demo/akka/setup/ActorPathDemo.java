package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.peaceful.demo.akka.actor.ActorPath;

/**
 * 每个Actor都会有一个Path,我们可以Actor reference 引用到需要交互的Actor,也可以通过Path寻找需要交互的Actor
 *
 * @author <a href="mailto:wangjuntytl@163.com">WangJun</a>
 * @version 1.0 16/1/5
 */
public class ActorPathDemo {

    final static ActorSystem system = ActorSystem.create("MySystem");
    static LoggingAdapter log = Logging.getLogger(system, ActorPathDemo.class);

    public static void main(String[] args) {
        ActorRef actorRef = system.actorOf(Props.create(ActorPath.class), "path");
        log.info(actorRef.path().toSerializationFormat());
        // actor 的path很像文件系统的路径,我们可以利用正则匹配搜寻actor
        // System.actorSelection(...) 从根开始  Context.actorSelection(...)是从当前Actor向下搜寻
        ActorSelection selection = system.actorSelection("akka://MySystem/user/*/*");
        selection.tell("hello", ActorRef.noSender());
    }

}

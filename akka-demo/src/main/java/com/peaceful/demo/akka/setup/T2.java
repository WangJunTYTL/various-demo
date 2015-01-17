package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.demo.akka.actor.GreetActor2;
import com.peaceful.demo.akka.domain.Greeting;

import java.util.Date;

/**
 * 测试最小控制单元actor 执行顺序
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class T2 {
    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        //创建一个actor，GreetActor2类型的actor，创建后的名字叫greeter（这个名字在一个actorSystem中是唯一的）
        ActorRef greeter = system.actorOf(Props.create(GreetActor2.class, new Date()), "greeter");
        greeter.tell(new Greeting("wj"), ActorRef.noSender());
        ActorRef greeter2 = system.actorOf(Props.create(GreetActor2.class, new Date()), "greeter2");
        greeter2.tell(new Greeting("wj2"), ActorRef.noSender());
        ActorRef greeter3 = system.actorOf(Props.create(GreetActor2.class, new Date()), "greeter3");
        greeter3.tell(new Greeting("wj3"), ActorRef.noSender());
        ActorRef greeter4 = system.actorOf(Props.create(GreetActor2.class, new Date()), "greeter4");
        greeter4.tell(new Greeting("wj4"), ActorRef.noSender());
        ActorRef greeter5 = system.actorOf(Props.create(GreetActor2.class, new Date()), "greeter5");
        greeter5.tell(new Greeting("wj5"), ActorRef.noSender());
/**************** 运行结果分析actor的执行顺序是无序的，这就是所谓的akka是消息驱动模型，actor之间是隔离的
 * /Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/bin/java -Didea.launcher.port=7538 "-Didea.launcher.bin.path=/Applications/IntelliJ IDEA 14.app/Contents/bin" -Dfile.encoding=UTF-8 -classpath "/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/lib/ant-javafx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/lib/dt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/lib/javafx-doclet.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/lib/javafx-mx.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/lib/jconsole.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/lib/sa-jdi.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/lib/tools.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/htmlconverter.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/rt.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.7.0_65.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Users/wangjun/ideaWorkSpace/github/myapp/akka-demo/target/classes:/Users/wangjun/.m2/repository/com/typesafe/akka/akka-actor_2.10/2.3.8/akka-actor_2.10-2.3.8.jar:/Users/wangjun/.m2/repository/org/scala-lang/scala-library/2.10.4/scala-library-2.10.4.jar:/Users/wangjun/.m2/repository/com/typesafe/config/1.2.1/config-1.2.1.jar:/Users/wangjun/.m2/repository/org/slf4j/slf4j-api/1.5.10/slf4j-api-1.5.10.jar:/Users/wangjun/.m2/repository/org/slf4j/slf4j-log4j12/1.5.10/slf4j-log4j12-1.5.10.jar:/Users/wangjun/.m2/repository/log4j/log4j/1.2.16/log4j-1.2.16.jar:/Users/wangjun/.m2/repository/com/google/guava/guava/16.0/guava-16.0.jar:/Applications/IntelliJ IDEA 14.app/Contents/lib/idea_rt.jar" com.intellij.rt.execution.application.AppMain com.peaceful.demo.akka.setup.T2
 [INFO] [01/17/2015 09:50:39.552] [MySystem-akka.actor.default-dispatcher-7] [akka://MySystem/user/greeter5] com.peaceful.demo.akka.domain.Greeting@192ba2d4
 [INFO] [01/17/2015 09:50:39.552] [MySystem-akka.actor.default-dispatcher-3] [akka://MySystem/user/greeter2] com.peaceful.demo.akka.domain.Greeting@7486a276
 [INFO] [01/17/2015 09:50:39.552] [MySystem-akka.actor.default-dispatcher-2] [akka://MySystem/user/greeter] com.peaceful.demo.akka.domain.Greeting@33142cbe
 [INFO] [01/17/2015 09:50:39.552] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/greeter3] com.peaceful.demo.akka.domain.Greeting@75ad2db5
 [INFO] [01/17/2015 09:50:39.552] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/greeter4] com.peaceful.demo.akka.domain.Greeting@1ff44e33
 [INFO] [01/17/2015 09:50:39.553] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/greeter4] Hello wj4 now time is 2015-1-17 9:50:39
 [INFO] [01/17/2015 09:50:39.553] [MySystem-akka.actor.default-dispatcher-2] [akka://MySystem/user/greeter] Hello wj now time is 2015-1-17 9:50:39
 [INFO] [01/17/2015 09:50:39.553] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/greeter3] Hello wj3 now time is 2015-1-17 9:50:39
 [INFO] [01/17/2015 09:50:39.553] [MySystem-akka.actor.default-dispatcher-3] [akka://MySystem/user/greeter2] Hello wj2 now time is 2015-1-17 9:50:39
 [INFO] [01/17/2015 09:50:39.553] [MySystem-akka.actor.default-dispatcher-7] [akka://MySystem/user/greeter5] Hello wj5 now time is 2015-1-17 9:50:39

 */
    }
}



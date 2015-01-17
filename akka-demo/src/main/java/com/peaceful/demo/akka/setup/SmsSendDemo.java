package com.peaceful.demo.akka.setup;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.peaceful.common.util.Util;
import com.peaceful.demo.akka.actor.SmsActor;
import com.peaceful.demo.akka.domain.Sms;

/**
 * 模拟发送1000条短信，并发执行，一条短信发送成功的时间为2s 机器 macpro m 16g c 4
 * <p/>
 * Created by wangjun on 15/1/17.
 */
public class SmsSendDemo {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("MySystem");
        long now = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ActorRef smsA = system.actorOf(Props.create(SmsActor.class), "sms" + i);
            Sms sms = new Sms();
            sms.content = "hello wj + " + i;
            sms.sender = "wj +" + i;
            sms.receiver = "jj +" + i;
            smsA.tell(sms, ActorRef.noSender());
        }
        Util.report(System.currentTimeMillis() - now);
    }

    /****************
     *
     * P_LOG:116 前台线程只用了116ms处理完任务
     * 后台运行 01/17/2015 10:31:21.272 开始   01/17/2015 10:32:45.480 结束 并行处理 1m24s212ms 船型处理应该是2000s 并行速度是串行的23倍
     * 而且actor的数量是在一定数量，下面可以看出是在25个，也就是说可以同时并行执行最大数量为25 ，根据实际结果可以看出应该是并行23个actor
     *
     * [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-12] [akka://MySystem/user/sms9] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/sms2] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms5] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms1] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms5] sms start send content: hello wj + 5
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/sms2] sms start send content: hello wj + 2
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms1] sms start send content: hello wj + 1
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms5] sms start send receiver: jj +5
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/sms2] sms start send receiver: jj +2
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms1] sms start send receiver: jj +1
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms5] sms start send sender: wj +5
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms10] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/sms2] sms start send sender: wj +2
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms1] sms start send sender: wj +1
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms10] sms start send content: hello wj + 10
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms10] sms start send receiver: jj +10
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms3] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms10] sms start send sender: wj +10
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms7] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms3] sms start send content: hello wj + 3
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-7] [akka://MySystem/user/sms4] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms3] sms start send receiver: jj +3
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms7] sms start send content: hello wj + 7
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-11] [akka://MySystem/user/sms8] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms3] sms start send sender: wj +3
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms7] sms start send receiver: jj +7
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-7] [akka://MySystem/user/sms4] sms start send content: hello wj + 4
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-11] [akka://MySystem/user/sms8] sms start send content: hello wj + 8
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-7] [akka://MySystem/user/sms4] sms start send receiver: jj +4
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms7] sms start send sender: wj +7
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-11] [akka://MySystem/user/sms8] sms start send receiver: jj +8
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-7] [akka://MySystem/user/sms4] sms start send sender: wj +4
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-10] [akka://MySystem/user/sms6] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-11] [akka://MySystem/user/sms8] sms start send sender: wj +8
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-10] [akka://MySystem/user/sms6] sms start send content: hello wj + 6
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-10] [akka://MySystem/user/sms6] sms start send receiver: jj +6
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-2] [akka://MySystem/user/sms0] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-14] [akka://MySystem/user/sms11] sms start send...
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-10] [akka://MySystem/user/sms6] sms start send sender: wj +6
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-2] [akka://MySystem/user/sms0] sms start send content: hello wj + 0
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-14] [akka://MySystem/user/sms11] sms start send content: hello wj + 11
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-12] [akka://MySystem/user/sms9] sms start send content: hello wj + 9
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-2] [akka://MySystem/user/sms0] sms start send receiver: jj +0
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-14] [akka://MySystem/user/sms11] sms start send receiver: jj +11
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-12] [akka://MySystem/user/sms9] sms start send receiver: jj +9
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-2] [akka://MySystem/user/sms0] sms start send sender: wj +0
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-14] [akka://MySystem/user/sms11] sms start send sender: wj +11
     [INFO] [01/17/2015 10:31:21.272] [MySystem-akka.actor.default-dispatcher-12] [akka://MySystem/user/sms9] sms start send sender: wj +9
     [INFO] [01/17/2015 10:31:21.273] [MySystem-akka.actor.default-dispatcher-16] [akka://MySystem/user/sms12] sms start send...
     [INFO] [01/17/2015 10:31:21.273] [MySystem-akka.actor.default-dispatcher-16] [akka://MySystem/user/sms12] sms start send content: hello wj + 12
     [INFO] [01/17/2015 10:31:21.273] [MySystem-akka.actor.default-dispatcher-16] [akka://MySystem/user/sms12] sms start send receiver: jj +12
     [INFO] [01/17/2015 10:31:21.273] [MySystem-akka.actor.default-dispatcher-16] [akka://MySystem/user/sms12] sms start send sender: wj +12
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-17] [akka://MySystem/user/sms13] sms start send...
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-17] [akka://MySystem/user/sms13] sms start send content: hello wj + 13
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-17] [akka://MySystem/user/sms13] sms start send receiver: jj +13
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-18] [akka://MySystem/user/sms14] sms start send...
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-17] [akka://MySystem/user/sms13] sms start send sender: wj +13
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-18] [akka://MySystem/user/sms14] sms start send content: hello wj + 14
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-18] [akka://MySystem/user/sms14] sms start send receiver: jj +14
     [INFO] [01/17/2015 10:31:21.281] [MySystem-akka.actor.default-dispatcher-18] [akka://MySystem/user/sms14] sms start send sender: wj +14
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-23] [akka://MySystem/user/sms19] sms start send...
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-23] [akka://MySystem/user/sms19] sms start send content: hello wj + 19
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-23] [akka://MySystem/user/sms19] sms start send receiver: jj +19
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-25] [akka://MySystem/user/sms21] sms start send...
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-23] [akka://MySystem/user/sms19] sms start send sender: wj +19
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-25] [akka://MySystem/user/sms21] sms start send content: hello wj + 21
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-25] [akka://MySystem/user/sms21] sms start send receiver: jj +21
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-25] [akka://MySystem/user/sms21] sms start send sender: wj +21
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-3] [akka://MySystem/user/sms22] sms start send...
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-3] [akka://MySystem/user/sms22] sms start send content: hello wj + 22
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-3] [akka://MySystem/user/sms22] sms start send receiver: jj +22
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-3] [akka://MySystem/user/sms22] sms start send sender: wj +22
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-21] [akka://MySystem/user/sms16] sms start send...
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-21] [akka://MySystem/user/sms16] sms start send content: hello wj + 16
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-21] [akka://MySystem/user/sms16] sms start send receiver: jj +16
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-21] [akka://MySystem/user/sms16] sms start send sender: wj +16
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-22] [akka://MySystem/user/sms18] sms start send...
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-22] [akka://MySystem/user/sms18] sms start send content: hello wj + 18
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-22] [akka://MySystem/user/sms18] sms start send receiver: jj +18
     [INFO] [01/17/2015 10:31:21.285] [MySystem-akka.actor.default-dispatcher-22] [akka://MySystem/user/sms18] sms start send sender: wj +18
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-24] [akka://MySystem/user/sms20] sms start send...
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-19] [akka://MySystem/user/sms15] sms start send...
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-24] [akka://MySystem/user/sms20] sms start send content: hello wj + 20
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-19] [akka://MySystem/user/sms15] sms start send content: hello wj + 15
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-24] [akka://MySystem/user/sms20] sms start send receiver: jj +20
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-24] [akka://MySystem/user/sms20] sms start send sender: wj +20
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-19] [akka://MySystem/user/sms15] sms start send receiver: jj +15
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-19] [akka://MySystem/user/sms15] sms start send sender: wj +15
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-20] [akka://MySystem/user/sms17] sms start send...
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-20] [akka://MySystem/user/sms17] sms start send content: hello wj + 17
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-20] [akka://MySystem/user/sms17] sms start send receiver: jj +17
     [INFO] [01/17/2015 10:31:21.286] [MySystem-akka.actor.default-dispatcher-20] [akka://MySystem/user/sms17] sms start send sender: wj +17
     P_LOG: 116
     [INFO] [01/17/2015 10:31:21.303] [MySystem-akka.actor.default-dispatcher-15] [akka://MySystem/user/sms23] sms start send...
     [INFO] [01/17/2015 10:31:21.303] [MySystem-akka.actor.default-dispatcher-15] [akka://MySystem/user/sms23] sms start send content: hello wj + 23
     [INFO] [01/17/2015 10:31:21.303] [MySystem-akka.actor.default-dispatcher-15] [akka://MySystem/user/sms23] sms start send receiver: jj +23
     [INFO] [01/17/2015 10:31:21.303] [MySystem-akka.actor.default-dispatcher-15] [akka://MySystem/user/sms23] sms start send sender: wj +23
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/sms2] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms3] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms1] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-7] [akka://MySystem/user/sms4] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms5] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-11] [akka://MySystem/user/sms8] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-10] [akka://MySystem/user/sms6] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-14] [akka://MySystem/user/sms11] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-2] [akka://MySystem/user/sms0] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-12] [akka://MySystem/user/sms9] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-16] [akka://MySystem/user/sms12] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms24] sms start send...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms10] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms26] sms start send...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms25] sms start send...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms7] sms start send suc ...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms24] sms start send content: hello wj + 24
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms26] sms start send content: hello wj + 26
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms25] sms start send content: hello wj + 25
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms24] sms start send receiver: jj +24
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-4] [akka://MySystem/user/sms24] sms start send sender: wj +24
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms25] sms start send receiver: jj +25
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms26] sms start send receiver: jj +26
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms26] sms start send sender: wj +26
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms25] sms start send sender: wj +25
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms27] sms start send...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms27] sms start send content: hello wj + 27
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms28] sms start send...
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms27] sms start send receiver: jj +27
     [INFO] [01/17/2015 10:31:23.275] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms28] sms start send content: hello wj + 28
     [INFO] [01/17/2015 10:31:23.276] [MySystem-akka.actor.default-dispatcher-8] [akka://MySystem/user/sms27] sms start send sender: wj +27
     [INFO] [01/17/2015 10:31:23.276] [MySystem-akka.actor.default-dispatcher-16] [akka://MySystem/user/sms29] sms start send...
     [INFO] [01/17/2015 10:31:23.276] [MySystem-akka.actor.default-dispatcher-13] [akka://MySystem/user/sms28] sms start send receiver: jj +28
     [INFO] [01/17/2015 10:31:23.276] [MySystem-akka.actor.default-dispatcher-16] [akka://MySystem/user/sms29] sms start send content: hello wj + 29
     [INFO] [01/17/2.........
     ...
     ...
     ..
     INFO] [01/17/2015 10:32:45.474] [MySystem-akka.actor.default-dispatcher-21] [akka://MySystem/user/sms995] sms start send suc ...
     [INFO] [01/17/2015 10:32:45.474] [MySystem-akka.actor.default-dispatcher-11] [akka://MySystem/user/sms994] sms start send suc ...
     [INFO] [01/17/2015 10:32:45.476] [MySystem-akka.actor.default-dispatcher-23] [akka://MySystem/user/sms996] sms start send suc ...
     [INFO] [01/17/2015 10:32:45.480] [MySystem-akka.actor.default-dispatcher-9] [akka://MySystem/user/sms997] sms start send suc ...
     [INFO] [01/17/2015 10:32:45.480] [MySystem-akka.actor.default-dispatcher-6] [akka://MySystem/user/sms998] sms start send suc ...
     [INFO] [01/17/2015 10:32:45.480] [MySystem-akka.actor.default-dispatcher-5] [akka://MySystem/user/sms999] sms start send suc ...
     */
}

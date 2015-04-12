package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date 14-9-26.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class T1 {
    Logger logger = LoggerFactory.getLogger(T1.class);

    private static String groupNode = "test";
    private static String subNode = "sub";

    private static String address = "localhost";
    static  String data = "app.id=56#app.version=2.0.0";


    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 5000, new Watcher() {
            public void process(WatchedEvent event) {
                // 不做处理
                Util.report("发现节点改变...");
            }
        });
        byte[] current = zk.getData("/test",null,null);
        Util.report(new String(current, "utf-8"));
        zk.setData("/test",data.getBytes(),-1);
    }

    /**
     * server的工作逻辑写在这个方法中
     * 此处不做任何处理, 只让server sleep
     */
    public static void handle() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
}
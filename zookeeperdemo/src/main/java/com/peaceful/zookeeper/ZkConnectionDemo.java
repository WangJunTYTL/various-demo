package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Date 14-9-26.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class ZkConnectionDemo {

    static Logger logger = LoggerFactory.getLogger(ZkConnectionDemo.class);

    public static ZooKeeper getConn() {
        return Single.zk;
    }

    public static ZooKeeper getNewConn() {
        return Single.getConn();
    }


    private static class Single {

        static ZooKeeper zk = getConn();

        public static ZooKeeper getConn() {

            try {
                zk = new ZooKeeper("localhost:2181", 5000, new Watcher() {
                    public void process(WatchedEvent event) {
                        // 不做处理
                        Util.report("" + event);
                    }
                });
                logger.info("zk conn suc... enjoy it !");
                return zk;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
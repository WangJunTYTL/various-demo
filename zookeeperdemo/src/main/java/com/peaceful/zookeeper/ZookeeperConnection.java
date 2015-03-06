package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date 14/11/11.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class ZookeeperConnection {

    Logger logger = LoggerFactory.getLogger(AppServer.class);

    private String servers = "127.0.0.1:12181,127.0.0.1:22181,127.0.0.1:32181";

    public ZooKeeper getZookeeper() throws Exception {
        ZooKeeper zk = new ZooKeeper(servers, 5000, getWatcher());
        logger.debug("servers:{}", servers);
        return zk;
    }

    //操作节点时触发watcher，在操作时是否触发watcher可以指定
    public Watcher getWatcher() {
        return new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                Util.report(event.getPath());
                Util.report(event.getType().name());
            }
        };
    }
}

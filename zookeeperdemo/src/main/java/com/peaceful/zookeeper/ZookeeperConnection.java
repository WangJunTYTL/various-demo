package com.peaceful.zookeeper;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Date 14/11/11.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class ZookeeperConnection {

    Logger logger = LoggerFactory.getLogger(AppServer.class);

    private MyWatcher myWatcher;

    //    private String servers = "127.0.0.1:12181,127.0.0.1:22181,127.0.0.1:32181";
    private String servers = "127.0.0.1:2181";

    public ZooKeeper getZookeeper() throws Exception {
        myWatcher = new MyWatcher();
        ZooKeeper zk = new ZooKeeper(servers, 5000, myWatcher); // 这里指定的watch可以被覆盖通过zookeeper.register(watcher)
        logger.debug("servers:{}", servers);
        return zk;
    }

    public void registWatcher(String key, Watcher watcher) {
        myWatcher.registWatcher(key, watcher);
    }

    public void removeWatcher(String key) {
        myWatcher.removeWatcher(key);
    }


}

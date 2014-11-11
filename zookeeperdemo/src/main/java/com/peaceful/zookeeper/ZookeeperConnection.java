package com.peaceful.zookeeper;

import com.peaceful.util.Util;
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

    public ZooKeeper connectZookeeper() throws Exception {
        ZooKeeper zk = new ZooKeeper(servers, 5000,createWatcher());
        return zk;
    }

    public Watcher createWatcher(){
        return  new Watcher(){

            @Override
            public void process(WatchedEvent event) {
                Util.report(event.getPath());
                Util.report(event.getType().name());
            }
        };
    }




}

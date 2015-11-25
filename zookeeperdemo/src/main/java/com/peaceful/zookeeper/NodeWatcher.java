package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * Created by wangjun on 15/11/24.
 */
public class NodeWatcher implements Watcher {

    ZooKeeper zooKeeper;

    public NodeWatcher(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent event) {
        Util.report("NodeWatcher:" + event);

        // 再次注册watch,因为每次watch触发以后就会消失,目前在触发后在次注册watch
        if (event.getType().equals(Event.EventType.None)) {

        } else {
            try {
                zooKeeper.exists(event.getPath(), this);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

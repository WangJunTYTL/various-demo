package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * Created by wangjun on 15/11/24.
 */
public class DefaultWatcher implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        Util.report("DefaultWatcher:"+event);
    }
}

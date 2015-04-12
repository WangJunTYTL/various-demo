package com.peaceful.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.HashMap;
import java.util.Map;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/5.
 */

public class MyWatcher implements Watcher {

    private static Map<String, Watcher> watcherMap = new HashMap<String, Watcher>();

    @Override
    public void process(WatchedEvent event) {
        for (Watcher watcher : watcherMap.values())
            watcher.process(event);
    }

    public boolean registWatcher(String key, Watcher watcher) {
        if (watcherMap.containsKey(key)) {
            return false;
        } else {
            synchronized (watcherMap) {
                watcherMap.put(key, watcher);
                return true;
            }
        }
    }

    public void removeWatcher(String key) {
        if (watcherMap.containsKey(key)) {
            synchronized (watcherMap) {
                watcherMap.remove(key);
            }
        }
    }
}

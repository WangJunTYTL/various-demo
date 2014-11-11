package com.peaceful.zookeeper;

import com.peaceful.util.Util;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.*;

public class ZookeeperConnectionTest {

    ZooKeeper zooKeeper;

    String znode = "/test";

    @Before
    public void init() throws Exception {
        zooKeeper = new ZookeeperConnection().connectZookeeper();
    }

    @org.junit.Test
    public void testSet() throws Exception {
        Stat stat = zooKeeper.exists(znode, true);
        if (stat != null)
            zooKeeper.setData(znode, "hello world".getBytes(), stat.getVersion());
        else
            zooKeeper.setData(znode, "hello world".getBytes(), 0);
    }

    @org.junit.Test
    public void testGet() throws Exception {
        Stat stat = zooKeeper.exists(znode, true);
        if (stat != null) {
            byte[] data = zooKeeper.getData(znode, true, stat);
            Util.report(new String(data));
        }
    }

    //得到子节点
    @org.junit.Test
    public void testChild() throws Exception {
        Stat stat = zooKeeper.exists(znode, true);
        if (stat != null) {
            List<String> list = zooKeeper.getChildren(znode,true);
            Util.report(list);
        }
    }
}
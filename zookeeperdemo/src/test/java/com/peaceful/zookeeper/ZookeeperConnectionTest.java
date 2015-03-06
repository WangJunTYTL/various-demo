package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;

import java.util.List;


public class ZookeeperConnectionTest {

    ZooKeeper zooKeeper;

    String znode = "/test";

    @Before
    public void init() throws Exception {
        zooKeeper = new ZookeeperConnection().getZookeeper();
        Stat stat = zooKeeper.exists(znode, false);
        if (stat == null) {
            zooKeeper.create(znode, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.setData(znode, "hello world".getBytes(), 0);
        }


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
            List<String> list = zooKeeper.getChildren(znode, true);
            Util.report(list);
        }
    }

    @org.junit.Test
    public void test() throws Exception {
        Stat stat = zooKeeper.exists(znode, true);
        if (stat != null) {
            zooKeeper.delete(znode, stat.getVersion()); //如果下面还有node，将会出错
        }
    }
}
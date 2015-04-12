package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * <a mailto:wangjuntytl@163.com>Email:wangjuntytl@163.com</a>
 *
 * @author wangjun
 * @version 1.0
 * @since 15/4/5.
 */

public class ZkTest {

    ZooKeeper zk;

    /**
     * 以 CreateMode.PERSISTENT 模式递归创建node，如果创建成功返回node
     *
     * @param node
     * @param data
     * @return
     */
    public String createNode(String node, byte[] data) {
        return createNode(node, data, CreateMode.PERSISTENT);
    }

    /**
     * 递归创建node，如果创建成功返回node
     *
     * @param node
     * @param data
     * @param createMode
     * @return
     */
    public String createNode(String node, byte[] data, CreateMode createMode) {
        if (node == null) return null;
        if (node.equals("/")) return null;
        Stat stat = getStat(node);
        if (stat != null) return null;
        String parentNode = node.substring(0, node.lastIndexOf("/"));
        Stat parentStat;
        if (node.indexOf("/") == node.lastIndexOf("/")) parentStat = new Stat();
        else parentStat = getStat(parentNode);
        if (parentStat == null) {
            return createNode(parentNode, null, createMode);
        } else {
            try {
                return zk.create(node, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, createMode);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Stat getStat(String node) {
        try {
            return zk.exists(node, true); // 打开watch
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * return node data
     *
     * @param node
     * @return
     */
    public byte[] getNode(String node) {
        Stat stat = getStat(node);
        if (stat != null)
            try {
                return zk.getData(node, false, stat);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        return null;
    }

    public Stat setNode(String node, byte[] data) {
        Stat stat = getStat(node);
        if (stat != null) {
            try {
                return zk.setData(node, data, stat.getVersion());
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<String> getChildren(String node) {
        Stat stat = getStat(node);
        if (stat == null) return null;
        try {
            List<String> list = zk.getChildren(node, true);
            if (list.size() == 0) return null;
            return list;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean delete(String node) {
        if (StringUtils.isEmpty(node)) return false;
        if (node.equals("/")) return false;
        if (node.equals("/zookeeper")) return false;
        List<String> child = getChildren(node);
        if (child != null) {
            for (String childNode : child) {
                delete(node + "/" + childNode);
            }
        }
        Stat stat = getStat(node);
        if (stat != null) {
            try {
                zk.delete(node, stat.getVersion());
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public void register(Watcher watcher) {
        zk.register(watcher);// 这将覆盖zookeeper的默认watcher
    }


    public static void main(String[] args) throws Exception {
        ZkTest zkTest = new ZkTest();
        ZookeeperConnection zookeeperConnection = new ZookeeperConnection();
        zkTest.zk = zookeeperConnection.getZookeeper();
        zookeeperConnection.registWatcher("node1", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Util.report("watch event path is " + event.getPath() + " type is " + event.getType() + " state is " + event.getState());
            }
        });
       /* zkTest.register(new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                Util.report("event2 is " + event.getPath());

            }
        });*/
        String rootNode = "/test";
        String rootNode2 = "/test2";
        String node1 = "/test/t1";
        String node2 = "/test/t2";
        String ephemeralNode = "/test/t4";
        String sequentialNode = "/test/t5";
        String sequentialNode2 = "/test/t6";
        String sequentialNode3 = "/test/t7";
        String sequentialNode4 = "/test2/bb/cc";
        byte[] data = "hello world".getBytes();
        Util.report("is exist " + zkTest.getStat(node1));
        Util.report("get node  " + zkTest.getNode(node1));
        Util.report("create node " + zkTest.createNode(node1, null));
        Util.report("create node " + zkTest.createNode(node2, null));
        Util.report("create node " + zkTest.createNode(ephemeralNode, data, CreateMode.EPHEMERAL)); // session valid will delete
        Util.report("create node " + zkTest.createNode(sequentialNode, data, CreateMode.PERSISTENT_SEQUENTIAL)); // node name will 自动追加一个自增的10位数，针对每个节点下的子节点开始算起
        Util.report("create node " + zkTest.createNode(sequentialNode2, data, CreateMode.PERSISTENT_SEQUENTIAL));
        Util.report("create node " + zkTest.createNode(sequentialNode3, data, CreateMode.PERSISTENT_SEQUENTIAL));
        Util.report("create node " + zkTest.createNode(sequentialNode4, data, CreateMode.EPHEMERAL_SEQUENTIAL));
        Util.report("get Children list " + zkTest.getChildren(rootNode));
        Util.report("set data " + zkTest.setNode(node1, "hello world".getBytes()));
        Util.report("get node  " + zkTest.getNode(node1));
        Util.report("set data " + zkTest.setNode(node1, "hello world2".getBytes()));
        Util.report("get node  " + zkTest.getNode(node1));
        Util.report("get data " + zkTest.getNode(sequentialNode + "0000000062"));
        Util.report("get Children list " + zkTest.getChildren("/tes"));
        Util.report("delete node " + zkTest.delete(node1));
        Util.report("delete node " + zkTest.delete(node2));
        Util.report("delete node " + zkTest.delete(rootNode2));

    }

}

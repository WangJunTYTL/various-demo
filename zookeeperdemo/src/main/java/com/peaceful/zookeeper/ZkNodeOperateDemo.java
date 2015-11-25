package com.peaceful.zookeeper;

import com.peaceful.common.util.Util;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by wangjun on 15/11/23.
 */
public class ZkNodeOperateDemo {

    private static String groupNode = "/test";
    private static String subNode = "sub";

    static Logger logger = LoggerFactory.getLogger(ZkConnectionDemo.class);


    public static void main(String[] args) throws KeeperException, InterruptedException {

        // 获取连接
        ZooKeeper zk = ZkConnectionDemo.getConn();
        // 获取新的连接,当用zk作服务协调时,通常是靠一个TCP长连接,如果长连接失败丢失,代表服务下线
        ZooKeeper zk2 = ZkConnectionDemo.getNewConn();

        // 覆盖默认的Connection watcher
        zk.register(new DefaultWatcher());

        // 为某个节点添加watcher ,当节点create delete set时会触发watcher
        Stat stat = zk.exists(groupNode, new NodeWatcher(zk));
        // 可以为你一个节点追加多个watcher
        zk.exists(groupNode, new Node2Watcher());


        if (stat == null) {
            logger.info(groupNode + " node not exist");
            String r = zk.create(groupNode, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            logger.info("create node {} return result is {}", groupNode, r);
        } else {
            // 判断是否是数据节点
            Stat _stat = zk.exists(groupNode, null);
            logger.info("{} stat is {}", groupNode, _stat);
            if (_stat.getNumChildren() == 0) {
                logger.info("{} is a data node", groupNode);
                byte[] value = zk.getData(groupNode, true, null);
                logger.info("get data from node {} ,result is {}", groupNode, value);
                Util.report("version:"+_stat.getVersion());
                zk.setData(groupNode,"hello".getBytes(),_stat.getVersion());
                zk.setData(groupNode, "hello world".getBytes(), _stat.getVersion() + 1, new AsyncCallback.StatCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, Stat stat) {
                        Util.report("StatCallback:"+stat);
                    }
                },null);
                // 此处version需要加2
                zk.delete(groupNode, _stat.getVersion()+2);
            } else {
                List<String> childrens = zk.getChildren(groupNode, null);
                logger.info("{} is a parend node, sub node is ", groupNode, childrens);
            }
        }

        for (;;){
//            zk.exists(groupNode,new Node2Watcher());
            Thread.sleep(1000);
        }

    }

}

package com.peaceful.demo.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.TimeUnit;

/**
 * zk的client有zookeeper官网提供的，可用起来会比较麻烦，比如设置watch麻烦、断开不可以重连
 * 开源的有：curator、zkclient
 *
 * Created by wangjun on 2016/11/3.
 */
public class T1 {

    public static void main(String[] args) throws InterruptedException {
        // zk可以在连接时指定namespace，这样所有的操作都是在namespace下操作,该功能是在3.2后
        ZkClient zkClient = new ZkClient("localhost:2181/namespace");
        int count = zkClient.countChildren("/");
        System.out.println("当前根目录共有" + count + "个Node");

        zkClient.delete("/test");
        zkClient.create("/test", null, CreateMode.PERSISTENT);
        System.out.println("/test node is exist：" + zkClient.exists("/test"));

        // handle data change watch  不需要重复设置watch
        // client 设置watch后，在指定路径发生改变时，服务端会发送变更事件
        zkClient.subscribeDataChanges("/test", new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {
                System.out.println("事件Watch change " + s + "-->" + o);
            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                System.out.println("事件Watch" + s + " delete event");
            }
        });

        zkClient.writeData("/test","1");
        System.out.println("/test 值："+zkClient.readData("/test"));

        zkClient.delete("/test");

        zkClient.create("/test","2",CreateMode.EPHEMERAL);

        TimeUnit.SECONDS.sleep(5);
    }
}

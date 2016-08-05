package com.peaceful.demo.es;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by wangjun on 16/2/5.
 */
public class ConnDemo {

    private static Client client = null;

    public static Client getConn() {
        if (client != null)
            return client;
//        client 不必指出所有节点,client和配置的一个节点通信后,会嗅探到其它节点,自定增加节点和删除节点功能
        String host01 = "127.0.0.1";
        String host02 = "127.0.0.1";
        Client client = null;
        try {
            Settings settings = Settings.settingsBuilder()
                    .put("cluster.name", "elasticsearch").build();

            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host01), 9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host02), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void main(String[] args) {
        getConn();
    }
}

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
        String host01 = "127.0.0.1";
        String host02 = "127.0.0.1";
        Client client = null;
        try {
            client = TransportClient.builder().build()
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

package com.peaceful.demo.es;

import org.elasticsearch.client.Client;

/**
 * Created by wangjun on 16/2/9.
 */
public class IndexDemo {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
    }
}

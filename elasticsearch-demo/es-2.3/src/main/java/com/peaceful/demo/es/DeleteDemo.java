package com.peaceful.demo.es;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.Client;

/**
 * Created by wangjun on 16/8/4.
 */
public class DeleteDemo {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        DeleteResponse response = client.prepareDelete("first-index","user","1").get();
        long version = response.getVersion();
        boolean isFound = response.isFound();
        System.out.println("isFount->"+isFound);
        System.out.println("version->"+version);
    }
}

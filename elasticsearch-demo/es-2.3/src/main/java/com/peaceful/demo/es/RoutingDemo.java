package com.peaceful.demo.es;

import org.elasticsearch.action.explain.ExplainRequest;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by wangjun on 16/8/4.
 */
public class RoutingDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Client client = ConnDemo.getConn();
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("name", "123");
        user.put("phone", "15888886666");
        user.put("address", "北京");
        //  查询时需要指定routing
        IndexResponse response = client.prepareIndex("first-index", "user","8").setSource(user).setRouting("name").get();
        System.out.println(response);


    }
}

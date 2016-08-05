package com.peaceful.demo.es;

import org.elasticsearch.action.explain.ExplainRequest;
import org.elasticsearch.action.explain.ExplainResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by wangjun on 16/8/4.
 */
public class GetDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Client client = ConnDemo.getConn();
        // setOperationThreaded 是为了在调用这线程上执行 默认为true
        GetResponse response = client.prepareGet("first-index", "user","2").setRouting("name").setOperationThreaded(false).get();
        Map<String, Object> source = response.getSource();
        System.out.println("source->" + source);
    }
}

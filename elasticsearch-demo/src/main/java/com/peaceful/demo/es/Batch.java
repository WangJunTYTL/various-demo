package com.peaceful.demo.es;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Client;
import org.slf4j.helpers.Util;

/**
 * Created by wangjun on 16/2/13.
 */
public class Batch {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        BulkRequestBuilder requestBuilder = client.prepareBulk().add(new IndexRequest("test","users").source("name","Wang Gang","age",28)).add(new IndexRequest("test","users").source("name","Li Bai","age",28));
        Util.report(requestBuilder.toString());
        requestBuilder.execute().actionGet();
    }
}

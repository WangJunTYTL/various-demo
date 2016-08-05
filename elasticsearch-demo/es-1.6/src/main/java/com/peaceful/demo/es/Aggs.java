package com.peaceful.demo.es;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.slf4j.helpers.Util;

/**
 * 简单的分析语法
 * Created by wangjun on 16/2/16.
 */
public class Aggs {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        SearchRequestBuilder requestBuilder = client.prepareSearch().addAggregation(AggregationBuilders.terms("by_age").field("age"));
        Util.report(requestBuilder.toString());
        SearchResponse response = requestBuilder.execute().actionGet();
    }
}

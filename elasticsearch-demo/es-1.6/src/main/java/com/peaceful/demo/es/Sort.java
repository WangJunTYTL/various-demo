package com.peaceful.demo.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.helpers.Util;

import java.util.Iterator;

/**
 * 简单排序
 *
 * Created by wangjun on 16/2/8.
 */
public class Sort {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        SearchResponse response = client.prepareSearch("test").addSort("updateTime", SortOrder.ASC).execute().actionGet();
        SearchHits hits = response.getHits();
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()) {
            Util.report(iterator.next().getSourceAsString());
        }
    }
}

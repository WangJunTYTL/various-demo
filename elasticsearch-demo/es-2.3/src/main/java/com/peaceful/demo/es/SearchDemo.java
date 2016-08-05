package com.peaceful.demo.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.helpers.Util;
import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.Iterator;

/**
 * Created by wangjun on 16/2/5.
 */
public class SearchDemo {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        // prepareSearch("test") 需要搜索的index，可以填写多个
        SearchResponse response = client.prepareSearch("test").setQuery(new TermQueryBuilder("age", 28)).execute().actionGet();
        Util.report("total:" + String.valueOf(response.getHits().totalHits()));
        Iterator<SearchHit> iterator = response.getHits().iterator();
        while (iterator.hasNext()) {
            Util.report(iterator.next().getSource().toString());
        }

//        match 查询
        response = client.prepareSearch("test").setQuery(new MatchQueryBuilder("name", "wj").toString()).execute().actionGet();
        iterator = response.getHits().iterator();
        while (iterator.hasNext()) {
            Util.report(iterator.next().getSource().toString());
        }

        // exist
        existsQuery("name");
    }
}

package com.peaceful.demo.es;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.slf4j.helpers.Util;

/**
 * Created by wangjun on 16/2/8.
 */
public class Scan {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        SearchRequestBuilder requestBuilder = client.prepareSearch("test").setTypes("users").setSearchType(SearchType.SCAN).setQuery(new TermQueryBuilder("name", "wj")).setScroll(new TimeValue(60000));
        SearchResponse response = requestBuilder.setSize(100).execute().actionGet();

        // 打印请求body体
        Util.report(requestBuilder.toString());

        //Scroll until no hits are returned
        while (true) {

            for (SearchHit hit : response.getHits().getHits()) {
                //Handle the hit...
                Util.report(hit.getSource().toString());
            }
            response = client.prepareSearchScroll(response.getScrollId()).setScroll(new TimeValue(60000)).execute().actionGet();
            //Break condition: No hits are returned
            if (response.getHits().getHits().length == 0) {
                break;
            }
        }
    }
}

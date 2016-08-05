package com.peaceful.demo.es;

import org.elasticsearch.action.count.CountResponse;
import org.slf4j.helpers.Util;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by wangjun on 16/2/5.
 */
public class CountQuery {

    public static void main(String[] args) {
        CountResponse countResponse = ConnDemo.getConn().prepareCount("first-index")
                .setQuery(termQuery("_id", "2"))
                .execute()
                .actionGet();
        Util.report(String.valueOf(countResponse.getCount()));
        System.out.println(countResponse.getTotalShards());
    }
}

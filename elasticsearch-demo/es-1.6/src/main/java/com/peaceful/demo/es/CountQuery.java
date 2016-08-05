package com.peaceful.demo.es;

import org.elasticsearch.action.count.CountResponse;
import org.slf4j.helpers.Util;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * Created by wangjun on 16/2/5.
 */
public class CountQuery {

    public static void main(String[] args) {
        CountResponse countResponse = ConnDemo.getConn().prepareCount("db_order")
                .setQuery(termQuery("phone", "15652636152"))
                .execute()
                .actionGet();
        Util.report(String.valueOf(countResponse.getCount()));
    }
}

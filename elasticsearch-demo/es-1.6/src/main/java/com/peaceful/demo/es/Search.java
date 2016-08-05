package com.peaceful.demo.es;

import org.elasticsearch.action.count.CountRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.helpers.Util;
import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * dsl 查询语言语法
 * <p/>
 * Created by wangjun on 16/2/9.
 */
public class Search {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        // query dsl & filter dsl  filter 用于精确查找，大多使用filter 查找，不会计算相关性
        SearchRequestBuilder searchRequest = client.prepareSearch("test").setQuery(new TermQueryBuilder("updateTime", System.currentTimeMillis()));
        Util.report(searchRequest.toString());
        // term filter  ( sql = )
        searchRequest = client.prepareSearch("test").setPostFilter(termQuery("updateTime", System.currentTimeMillis()));
        Util.report(searchRequest.toString());
        // terms filter ( sql in )
        searchRequest = client.prepareSearch("test").setPostFilter(termsQuery("name", "wj", "wj01"));
        Util.report(searchRequest.toString());
        // range filter ( sql between and )
        searchRequest = client.prepareSearch("test").setPostFilter(rangeQuery("updateTime").gte(1455009486597l).lt(System.currentTimeMillis()));
        Util.report(searchRequest.toString());
        // exist filter ( sql != null )
        searchRequest = client.prepareSearch("test").setPostFilter(existsQuery("age"));
        Util.report(searchRequest.toString());
        // miss filter ( sql == null)
        searchRequest = client.prepareSearch("test").setPostFilter(missingQuery("age"));
        Util.report(searchRequest.toString());
        // bool filter ( sql and or )
        searchRequest = client.prepareSearch("test").setPostFilter(boolQuery().must(existsQuery("age")).mustNot(existsQuery("updateTime")));
        Util.report(searchRequest.toString());


        // 聚合
        // count ( sql count)
        CountRequestBuilder countRequestBuilder = client.prepareCount("test").setQuery(new TermQueryBuilder("name", "wj"));
        Util.report(countRequestBuilder.toString());


        // 排序
        // sort ( sql order by)
        searchRequest = client.prepareSearch("test").addSort("updateTime", SortOrder.ASC);
        Util.report(searchRequest.toString());

        // highlight
        searchRequest = client.prepareSearch("test").setQuery(new TermQueryBuilder("name", "wj")).addSort("updateTime", SortOrder.ASC).addHighlightedField("name").setFetchSource("phone",null);
        Util.report(searchRequest.toString());

    }
}

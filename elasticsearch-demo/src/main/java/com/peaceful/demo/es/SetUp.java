package com.peaceful.demo.es;

import com.peaceful.common.util.Util;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/6/17
 * @since 1.6
 */

public class SetUp {

    public static void main(String[] args) {
//        String host01 = "search.edaijia.cn";
//        String host01 = "search.edaijia.cn";
        String host01 = "127.0.0.1";
        String host02 = "127.0.0.1";
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "elasticsearch_root").build();
        Client client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(host01, 9300))
                .addTransportAddress(new InetSocketTransportAddress(host02, 9300));

        CountResponse countResponse = client.prepareCount("db_order")
                .setQuery(termQuery("phone", "15652636152"))
                .execute()
                .actionGet();
        Util.report(countResponse.getCount());
    }
}

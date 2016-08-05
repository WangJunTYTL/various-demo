package test;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHits;
import org.junit.Test;

import java.util.Random;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/6/21
 * @since 1.6
 */

public class ElasticSearch {

    /*@Test
    public void testElasticSearch1() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "promotion ").put("client.transport.sniff", true).build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("192.168.172.95", 9300));

        SearchResponse response = client.prepareSearch("promotion").setTypes("promotion")
                .addFields("Pid", "PromoId")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(new TermQueryBuilder("PromoId", 100000000 + new Random().nextInt(100000000)))
                .execute().actionGet();
        SearchHits hits = response.getHits();
        client.close();
    }

    @Test
    public void testElasticSearch2() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "promotion ").put("client.transport.sniff", true).build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("192.168.172.95", 9300));

        SearchResponse response = client.prepareSearch("promotion").setTypes("promotion")
                .addFields("Pid", "PromoId")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(new TermQueryBuilder("PromoName", "苹果（Apple）iPhone 6 Plus (A1524) 16GB 金"))
                .execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.print(hits.getHits().length);
        client.close();
    }

    @Test
    public void testElasticSearch3() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "promotion ").put("client.transport.sniff", true).build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("192.168.172.95", 9300));

        SearchResponse response = client.prepareSearch("promotion").setTypes("promotion")
                .addFields("Pid", "PromoId")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(new TermQueryBuilder("PromoType", new Random().nextInt(6) + 1))
                .execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.print(hits.getTotalHits());
        client.close();
    }

    @Test
    public void testElasticSearch4() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "promotion ").put("client.transport.sniff", true).build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("192.168.172.95", 9300));

        SearchResponse response = client.prepareSearch("promotion").setTypes("promotion")
                .addFields("Pid", "PromoId")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(new TermQueryBuilder("PromoState", new Random().nextInt(4) + 1))
                .execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.print(hits.getTotalHits());
        client.close();
    }

    @Test
    public void testElasticSearch5() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "promotion ").put("client.transport.sniff", true).build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("192.168.172.95", 9300));

        SearchResponse response = client.prepareSearch("promotion").setTypes("promotion")
                .addFields("Pid", "PromoId")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(new RangeQueryBuilder("TimeBegin").
                        from(System.currentTimeMillis()).
                        to(System.currentTimeMillis()))
                .execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.print(hits.getTotalHits());
        client.close();
    }

    @Test
    public void testElasticSearch6() {
        Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "promotion ").put("client.transport.sniff", true).build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("192.168.172.95", 9300));

        QueryBuilder queryBuilder = new BoolQueryBuilder()
                .must(new TermQueryBuilder("CreatePin", "bjadmin"))
                .must(new TermQueryBuilder("PromoState", new Random().nextInt(4) + 1))
                .must(new RangeQueryBuilder("TimeBegin").
                        from(System.currentTimeMillis()).
                        to(System.currentTimeMillis()));

        SearchResponse response = client.prepareSearch("promotion").setTypes("promotion")
                .addFields("Pid", "PromoId")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(queryBuilder)
                .execute().actionGet();
        SearchHits hits = response.getHits();
        System.out.print(hits.getTotalHits());
        client.close();
    }

*/
    @Test
    public void testSync(){

    }
}

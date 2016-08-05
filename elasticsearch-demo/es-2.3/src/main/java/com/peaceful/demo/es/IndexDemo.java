package com.peaceful.demo.es;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangjun on 16/2/9.
 */
public class IndexDemo {

    public static void main(String[] args) {
        Client client = ConnDemo.getConn();
        Map<String, Object> user = new HashMap<String, Object>();
        user.put("name", "JJ");
        user.put("pbone", "15888886666");
        user.put("address", "北京");
//        异步执行
        IndexResponse response = client.prepareIndex("first-index", "user","1").setSource(user).get();
        // Index name
        String _index = response.getIndex();
// Type name
        String _type = response.getType();
// Document ID (generated or not)
        String _id = response.getId();
// Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
// isCreated() is true if the document is a new one, false if it has been updated
        boolean created = response.isCreated();

        System.out.println("_index->"+_index);
        System.out.println("_type->"+_type);
        System.out.println("_id->"+_id);
        System.out.println("_version->"+_version);
        System.out.println("created->"+created);
    }
}

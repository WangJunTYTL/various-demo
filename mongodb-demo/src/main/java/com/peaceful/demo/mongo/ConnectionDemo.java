package com.peaceful.demo.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import java.net.UnknownHostException;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/24
 * @since 1.6
 */

public class ConnectionDemo {

    public static void main(String[] args) throws UnknownHostException {
        getConn();
    }

    public static MongoDatabase getConn(){
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("test");
        return db;
    }
}

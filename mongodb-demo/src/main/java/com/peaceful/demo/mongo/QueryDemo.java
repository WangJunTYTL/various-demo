package com.peaceful.demo.mongo;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.ParseException;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/10/24
 * @since 1.6
 */

public class QueryDemo {

    public static void main(String[] args) throws ParseException {
        MongoDatabase db = ConnectionDemo.getConn();
        FindIterable<Document> iterable = db.getCollection("user").find();
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                System.out.println(document);
            }
        });
    }
}

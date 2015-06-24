package com.peaceful.demo.kafka;


import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import java.util.Properties;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/6/7
 * @since 1.6
 */

public class ProducerDemo {

    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("zk.connect", "127.0.0.1:2181");
        props.put("metadata.broker.list", "127.0.0.1:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        KeyedMessage<String, String> data = new KeyedMessage<String, String>("test-topic", "test-message2");
        for (; ; ) {
            producer.send(data);
            Thread.sleep(100);
        }
//        producer.close();
    }
}

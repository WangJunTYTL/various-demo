package com.peaceful.demo.kafka;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.Message;
import kafka.message.MessageAndMetadata;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author WangJun <wangjuntytl@163.com>
 * @version 1.0 15/6/7
 * @since 1.6
 */

public class ConsumerDemo {
    public static void main(String[] args) {
        // specify some consumer properties
        Properties props = new Properties();
        props.put("zookeeper.connect", "localhost:2181");
        props.put("zk.connectiontimeout.ms", "1000");
        props.put("auto.commit.enable", "true");
        props.put("group.id", "test_group");

        // Create the connection to the cluster
        ConsumerConfig consumerConfig = new ConsumerConfig(props);
        ConsumerConnector consumerConnector = Consumer.createJavaConsumerConnector(consumerConfig);

        // create 4 partitions of the stream for topic “test-topic”, to allow 4 threads to consume
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("test-topic", 4);
        Map<String, List<KafkaStream<byte[], byte[]>>> topicMessageStreams =
                consumerConnector.createMessageStreams(map);
        List<KafkaStream<byte[], byte[]>> streams = topicMessageStreams.get("test-topic");


        // create list of 4 threads to consume from each of the partitions
        ExecutorService executor = Executors.newFixedThreadPool(4);


        // consume the messages in the threads
        for (final KafkaStream<byte[], byte[]> stream : streams) {
            executor.submit(new Runnable() {
                public void run() {
                    ConsumerIterator<byte[], byte[]> it = stream.iterator();
                    int i = 0;
                    while (it.hasNext()) {
                        // connector.commitOffsets();手动提交offset,当autocommit.enable=false时使用
                        MessageAndMetadata<byte[], byte[]> item = it.next();
                        try {
                            System.out.print("content->" + new String(item.message(), "utf-8") + (i++) + "\n");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }
                    /*for (MessageAndMetadata msgAndMetadata : stream) {
                        // process message (msgAndMetadata.message())
                        System.out.println("topic: " + msgAndMetadata.topic());
                        Message message = (Message) msgAndMetadata.message();
                        ByteBuffer buffer = message.payload();
                        byte[] bytes = new byte[message.payloadSize()];
                        buffer.get(bytes);
                        String tmp = null;
                        try {
                            tmp = new String(bytes,"utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        System.out.println("message content: " + tmp);
                    }
                }*/
            });
        }

    }
}
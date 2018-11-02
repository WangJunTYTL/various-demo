package com.peaceful.demo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * Created by Jun on 2018/9/13.
 */
public class KafkaConsumerDemo {

    public static void main(String[] args) {
        // consumer是非线程安全的，可以一个线程对应一个consumer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test");
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);

        // consumer订阅的topic信息
        consumer.subscribe(Arrays.asList("test-topic-01", "bar"));

        // 这里仅为了测试
        Set<TopicPartition> topicPartitions = consumer.assignment();
        consumer.seekToBeginning(topicPartitions); // 重置offset
        while (true) {

//            consumer.seekToBeginning(topicPartitions); // 重置offset
            ConsumerRecords<String, String> records = consumer.poll(100);

            if (records.isEmpty()) {
                // nothing
            } else {


                for (ConsumerRecord<String, String> record : records)
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                // 提交offset
                consumer.commitAsync(new OffsetCommitCallback() {
                    // 提交offset到server端成功后回调函数
                    public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
                        for (Map.Entry<TopicPartition, OffsetAndMetadata> entry : offsets.entrySet()) {
                            System.out.println(entry.getKey() + "->" + entry.getValue());
                        }

                    }
                });
            }
//            consumer.commitSync();
        }
    }
}

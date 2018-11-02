package com.peaceful.demo.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Jun on 2018/9/13.
 */
public class KafkaProducerDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        for (int i = 0; i < 100; i++) {
            // 异步发送
            final ProducerRecord message = new ProducerRecord<String, String>("test-topic-01", Integer.toString(i), Integer.toString(i));
//            Future<RecordMetadata> data = producer.send(message);
            // callback是在kafka消息发送的线程内执行，应保证callback快速响应，防止阻碍kafka消息发送出来
            Callback callback = new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println(metadata);
                    if (exception != null) {
                        exception.printStackTrace();
                    }
                }
            };
            Future<RecordMetadata> data = producer.send(message, callback);
            // client端消息发送是异步，如果想同步发送消息，可以调用get方法block直到消息发送成功
//            RecordMetadata metadata = data.get();
//            System.out.println(metadata.toString());
        }
        // 调用close方法后，client端会把本地的消息发送到server端口，在最终close整个client实例
        producer.close();
    }
}

package com.peaceful.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Date 14-10-16.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * 这是一个最简单的测试
 */
public class Test {

    public static String jmsProviderAddress = "tcp://localhost:61616";// 地址
    public static String queueName = "testQueue";// 队列名字

    public static void main(String[] args) {

        Test.send("hello world !");

        //Test.receive();
    }

    public static void send(String msg) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(jmsProviderAddress);
        try {
            Connection connection = activeMQConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            MessageProducer messageProducer = session.createProducer(destination);
            connection.start();
            Message message = session.createTextMessage(msg);// 消息
            for (int i = 0; i < 10000; i++) {
                messageProducer.send(message);
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public static void receive() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(jmsProviderAddress);
        try {
            Connection connection = activeMQConnectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("testQueue");
            MessageConsumer messageConsumer = session.createConsumer(destination);
            connection.start();
            Message message = messageConsumer.receive();
            System.out.println(((TextMessage) message).getText());
            messageConsumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}

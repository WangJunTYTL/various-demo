package com.peaceful.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;

/**
 * Date 14/10/18.
 * Author WangJun
 * Email wangjuntytl@163.com
 */
public class TestQueueReceive {

    Logger logger = LoggerFactory.getLogger(TestQueueReceive.class);
    private MessageConsumer messageConsumer;
    private MessageListener messageListener;

    public TestQueueReceive(MessageConsumer messageConsumer, MessageListener messageListener) {
        this.messageConsumer = messageConsumer;
        this.messageListener = messageListener;
    }

    public void receive() {
        try {
            messageConsumer.setMessageListener(messageListener);
            do{

            }while (true);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    ;
}

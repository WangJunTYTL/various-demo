package com.peaceful.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Date 14/10/18.
 * Author WangJun
 * Email wangjuntytl@163.com
 * <p/>
 * 监听消息队列:testQueue
 */
public class TestQueueListener implements MessageListener {


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String msg = textMessage.getText();
            System.out.println(msg);
        } catch (JMSException e) {
            logger.error("onMessage:{}", e);
        }


    }
}

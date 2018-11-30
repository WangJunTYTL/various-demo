package activemq;

import com.peaceful.activemq.TestQueueListener;
import com.peaceful.activemq.TestQueueReceive;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Before;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

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
    Session session = null;
    MessageProducer messageProducer = null;
    MessageConsumer messageConsumer = null;

    @Before
    public void before() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(jmsProviderAddress);
        try {
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(queueName);
            messageProducer = session.createProducer(destination);
            messageConsumer = session.createConsumer(destination);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void send() {
        try {
            Message message = session.createTextMessage("hello world");// 消息
            for (int i = 0; i < 10000; i++) {
                messageProducer.send(message);
            }
            messageProducer.close();
            session.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void receive() { //保证接受消息的线程一直存在
        TestQueueReceive testQueueReceive = new TestQueueReceive(messageConsumer,new TestQueueListener());
        testQueueReceive.receive();
    }


}

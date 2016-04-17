package me.gacl.test;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 *  * Created by outofmemory.cn on 14-8-26.
 *  *
 *  * activemq?????
 *  
 */
public class ConsumerApp implements MessageListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerApp.class);
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String SUBJECT = "test-activemq-queue";

    public static void main(String[] args) throws JMSException {
//???ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);

        //??mq??
        Connection conn = connectionFactory.createConnection();
        //????
        conn.start();

        //????
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

       //????????
        Destination dest = session.createQueue(SUBJECT);
       //??mq??????
        MessageConsumer consumer = session.createConsumer(dest);

        //???MessageListener
        ConsumerApp me = new ConsumerApp();

        //??????????
        consumer.setMessageListener(me);
    }

    public void onMessage(Message message) {
        TextMessage txtMessage = (TextMessage) message;
        try {
            LOGGER.info("get message " + txtMessage.getText());
        } catch (JMSException e) {
            LOGGER.error("error {}", e);
        }
    }
}

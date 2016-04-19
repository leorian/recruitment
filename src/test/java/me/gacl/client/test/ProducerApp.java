package me.gacl.client.test;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 *  
 */
public class ProducerApp {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerApp.class);
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String SUBJECT = "test-activemq-queue";

    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        Connection conn = connectionFactory.createConnection();
        conn.start();

        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination dest = session.createQueue(SUBJECT);

        MessageProducer producer = session.createProducer(dest);
        for (int i = 0; i < 100; i++) {

            TextMessage message = session.createTextMessage("hello active mq ??" + i);
            producer.send(message);
            LOGGER.debug("send message {}", i);
        }

        conn.close();
    }
}
package me.gacl.activemq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;

/**
 * Created by xiezhonggui on 16-5-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-activemq.xml")
public class ProducerConsumerTest {
    @Autowired
    private ProducerService producerService;

    @Autowired
    @Qualifier("queueDestination")
    private Destination destination;

    @Test
    public void testSend() {
        for (int i=0; i<2; i++) {
            producerService.sendMessage(destination, "Hello World"+i);
        }

    }
}

package me.gacl.activemq;

import javax.jms.Destination;

/**
 * Created by xiezhonggui on 16-5-20.
 */
public interface ProducerService {

    void sendMessage(Destination destination, final String message);
}

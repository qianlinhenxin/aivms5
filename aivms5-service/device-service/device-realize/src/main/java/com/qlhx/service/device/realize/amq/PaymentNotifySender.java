package com.qlhx.service.device.realize.amq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentNotifySender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
     
    public void sender(String msg){
        System.out.println("notify.payment send message: "+msg);
        rabbitTemplate.convertAndSend("notify.payment", msg);
    }
}

package com.qlhx.service.device.realize.amq;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DirectConfig {
    @Bean
    public Queue paymentNotifyQueue() {
    	System.out.println("00000000000000000000000000");
        return new Queue("notify.payment");
    }
}
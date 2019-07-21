package com.qlhx.service.logger.realize.mq;


import com.qlhx.base.model.MQLogger;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {


    @Bean
    public Queue createQueue(){
        return new Queue(MQLogger.ROUTING_KEY,true);
    }
} 

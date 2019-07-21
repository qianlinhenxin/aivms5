package com.qlhx.service.facedevice.realize.mq;

import com.qlhx.base.model.MQLogger;
import com.qlhx.base.model.MQRegFace;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MQConfig {


    @Bean
    public Queue createQueue(){
        return new Queue(MQLogger.ROUTING_KEY,true);
    }
    @Bean
    public Queue createQueueRegface(){
        return new Queue(MQRegFace.ROUTING_KEY,true);
    }
} 

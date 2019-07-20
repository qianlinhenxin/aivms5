package com.qlhx.service.control.realize.mq;

import com.alibaba.fastjson.JSON;
import com.qlhx.base.model.MQLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class MQSender {
	
	private  final Logger logger = LoggerFactory.getLogger(MQSender.class);
	
    @Autowired
    private AmqpTemplate rabbitTemplate;
    
    @Value("${spring.application.name}")
    private String serviceName;
    
    /**
     * 发送日志 
     * @param
     */
	public void sendLog(String title,String content) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				MQLogger mqLogger = new MQLogger(serviceName, title, content, new Date());
				try {
					String json = JSON.toJSONString(mqLogger);
					rabbitTemplate.convertAndSend(MQLogger.ROUTING_KEY,json );
					logger.info("发送了日志:" + json);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("发送日志发生错误",e);
				}
				
			}
		}) {
		}.start();

	}

}

package com.qlhx.service.facedevice.realize.mq;

import java.util.Date;

import com.qlhx.base.model.MQLogger;
import com.qlhx.base.model.MQRegFace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class MQSender {
	
	private  final Logger logger = LoggerFactory.getLogger(MQSender.class);
	
    @Autowired
    private AmqpTemplate rabbitTemplate;
    
    @Value("${spring.application.name}")
    private String serviceName;
    
    /**
     * 发送日志 
     * @param msg
     */
	public void sendLog(String msg) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				MQLogger mqLogger = new MQLogger(serviceName, "22", msg, new Date());
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
	
	public void sendLog1(String msg) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				MQRegFace mQRegFace = new MQRegFace("17","11","22","33");
				try {
					String json = JSON.toJSONString(mQRegFace);
					rabbitTemplate.convertAndSend(MQRegFace.ROUTING_KEY,json );
					logger.info("注册人脸:" + json);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("发送日志发生错误",e);
				}
				
			}
		}) {
		}.start();

	}
}

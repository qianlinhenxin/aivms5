package com.qlhx.service.logger.realize.mq;

import com.qlhx.base.model.MQLogger;
import com.qlhx.service.logger.realize.model.LoggerLogs;
import com.qlhx.service.logger.realize.service.LoggerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class PaymentNotifyReceive {
	
	private  final Logger logger = LoggerFactory.getLogger(PaymentNotifyReceive.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private LoggerService loggerService;
//	@RabbitListener(queues = MQLogger.ROUTING_KEY)
//    @RabbitHandler
//    public void receiveLogger(String log) {
//		logger.info("=========="+log+"==========");
//		try {
//			MQLogger mqLogger = JSON.parseObject(log, MQLogger.class);
//			mongoTemplate.insert(log,mqLogger.getServiceName());
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("日志存入mongodb发生错误",e);
//		}
//        
//    }
    
	
	@RabbitListener(queues = MQLogger.ROUTING_KEY)
    @RabbitHandler
    public void receiveLogger(String log) {
		logger.info("=========="+log+"==========");
		try {
			MQLogger mqLogger = JSON.parseObject(log, MQLogger.class);
			
			LoggerLogs loggerLogs = new LoggerLogs();
			loggerLogs.setServiceName(mqLogger.getServiceName());
			loggerLogs.setTitle(mqLogger.getTitle());
			loggerLogs.setContent(mqLogger.getContent());
			loggerLogs.setCreatedate(mqLogger.getCreateDate());
			loggerService.insertSelective(loggerLogs);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("日志存入mongodb发生错误",e);
		}
        
    }
    
    
}
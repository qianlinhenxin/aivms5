package com.qlhx.service.base.realize.util.mq;


import com.alibaba.fastjson.JSON;
import com.qlhx.base.model.MQLogger;
import com.qlhx.base.model.MQRegFace;
import com.qlhx.base.util.file.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.util.Date;

@Lazy(false)
@Component
@PropertySource("classpath:application-config.properties")
public class MQSender {
	
	private  final Logger logger = LoggerFactory.getLogger(MQSender.class);
	
    @Autowired
    private AmqpTemplate rabbitTemplate;
    
    @Value("${spring.application.name}")
    private String serviceName;

    @Value("${imgPath}")
    private String imgPath;
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
	
	public void regFace(MQRegFace mQRegFace) {

		new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("imgPath ++++++++++++++++++++++++++++++++++++++++++++++++"+imgPath);
                        mQRegFace.setDeviceIds("19"); //2019-7-13 byl  为了演示先写死
                        byte[] picArr = FileUtil.toByteArray(imgPath+"/"+mQRegFace.getPhoto());
                        String photo = new String(picArr);
                        final BASE64Encoder encoder = new BASE64Encoder();
                        final String photoBase64 = encoder.encode(picArr);
                        mQRegFace.setPhoto(photoBase64);
                        String json = JSON.toJSONString(mQRegFace);
                        rabbitTemplate.convertAndSend(MQRegFace.ROUTING_KEY,json );
                        logger.info("注册人脸:" + json);
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error("注册人脸发生错误",e);
                    }
                }
		}) {
		}.start();

	}
}

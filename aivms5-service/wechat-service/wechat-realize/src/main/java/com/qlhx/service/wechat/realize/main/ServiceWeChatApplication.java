package com.qlhx.service.wechat.realize.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableEurekaClient
@ServletComponentScan
@MapperScan(basePackages = {"com.qlhx.dao","com.qlhx.timer"})
@SpringBootApplication
@EnableTransactionManagement
public class ServiceWeChatApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceWeChatApplication.class, args);
	}

}

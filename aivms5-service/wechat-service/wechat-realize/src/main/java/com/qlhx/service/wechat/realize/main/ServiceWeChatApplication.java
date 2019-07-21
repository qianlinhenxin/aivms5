package com.qlhx.service.wechat.realize.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;


@EnableEurekaClient
@ServletComponentScan
@MapperScan(basePackages = {"com.qlhx.dao","com.qlhx.timer"})
@SpringBootApplication
@EnableTransactionManagement
public class ServiceWeChatApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceWeChatApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		return builder.build();
	}

}

package com.qlhx.service.door.realize.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling  
@ServletComponentScan
@MapperScan("com.qlhx.mapper")
public class DoorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoorApplication.class, args);
	}
	
	@Bean
    @LoadBalanced//在注册中心里进行查找微服务,负载均衡
    public RestTemplate restTemplate(){

        RestTemplate restTemplate=new RestTemplate();
        return  restTemplate;
    }
	
}

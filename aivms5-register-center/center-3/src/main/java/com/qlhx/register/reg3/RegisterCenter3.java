package com.qlhx.register.reg3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Create by xb
 * The file is [ registrationCenter] on [ x-file-system ] project
 * The file path is registrationCenter
 *
 * @versio 1.0.o
 * @Author he ming xi
 * @date 2019/3/14 10:45
 * @description
 *      spring-cloud eureka注册中心
 */
@SpringBootApplication
@EnableEurekaServer
@EnableDiscoveryClient
public class RegisterCenter3 {
    public static void main(String[] args) {
        SpringApplication.run(RegisterCenter3.class,args);
        System.out.println("<---------------register center 3 starting--------------->");
    }
}

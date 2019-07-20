package com.qlhx.web.auth.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Create by xb
 * The file is [ WebApplication] on [ x-file-system ] project
 * The file path is com.itgo.web.main.WebApplication
 *
 * @versio 1.0.0
 * @Author he ming xi
 * @date 2019/4/7 20:29
 * @description
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = {"com.qlhx.service.auth.api.api"})
@ComponentScan(basePackages = {"com.qlhx.base"})
@ServletComponentScan
@EnableAspectJAutoProxy(exposeProxy=true)
public class AuthWebApplication {

    private static Logger logger = LoggerFactory.getLogger(AuthWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AuthWebApplication.class, args);
        logger.info("<-------------auth web start success--------------->");
    }



}

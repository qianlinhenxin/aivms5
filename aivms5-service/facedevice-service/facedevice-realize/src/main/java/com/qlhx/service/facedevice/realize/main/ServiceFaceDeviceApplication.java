package com.qlhx.service.facedevice.realize.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@ServletComponentScan
@MapperScan("com.qlhx.dao")
@SpringBootApplication
public class ServiceFaceDeviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceFaceDeviceApplication.class, args);
    }
}

package com.qlhx.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Create by xb
 * The file is [ ConfigServerApplication] on [ x-file-system ] project
 * The file path is com.itgo.ConfigServerApplication
 *
 * @versio 1.0.o
 * @Author he ming xi
 * @date 2019/4/6 10:58
 * @description
 *      spring-cloud配置中心
 */
@EnableConfigServer
@SpringBootApplication
@EnableEurekaClient
public class ConfigServerApplication extends WebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

    @Bean
    public ConfigInterceptor configInterceptor(){
        return new ConfigInterceptor();
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(configInterceptor()).addPathPatterns("/encrypt/**");
        super.addInterceptors(registry);
    }
}

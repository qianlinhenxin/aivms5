package com.qlhx.config.cf1;

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
public class ConfigServer1Application extends WebMvcConfigurationSupport {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer1Application.class, args);
    }

    @Bean
    public ConfigInterceptor configInterceptor(){
        return new ConfigInterceptor();
    }

    /**
     * 配置拦截器
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(configInterceptor()).addPathPatterns("/encrypt/**","/decrypt/**");
        super.addInterceptors(registry);
    }


}

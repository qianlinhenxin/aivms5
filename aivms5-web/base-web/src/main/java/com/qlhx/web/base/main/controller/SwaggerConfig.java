package com.qlhx.web.base.main.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@ComponentScan(basePackages = { "com.qlhx.controller" })
public class SwaggerConfig {

    @Bean
    public Docket customDocket() {
	return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
		.select()
		.apis(RequestHandlerSelectors
				.basePackage("com.qlhx.controller")) // 对所有api进行监控
		.paths(PathSelectors.any()) // 对所有路径进行监控
		.build(); 
    }

    private ApiInfo apiInfo() { 
	ApiInfo apiInfo = new ApiInfo("基础数据微服务对外接口", "接口测试", "V1.0.0", "",
		new Contact("lidahai", "", "") , "", "", new ArrayList<>());
	return apiInfo;
    }

} 
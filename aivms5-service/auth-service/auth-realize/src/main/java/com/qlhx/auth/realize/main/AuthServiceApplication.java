package com.qlhx.auth.realize.main;

import com.qhlx.core.util.bean.BeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Create by xigexb
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/17 21:14
 * @Description desc:
 */
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
@SpringBootApplication(scanBasePackages = {"com.qlhx.auth.realize"})
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.qlhx.auth.api.api","com.qlhx.base.api.api"})
@ComponentScan(basePackages = {"com.qlhx.base","com.qlhx.auth.realize","com.qhlx.core"})
@EnableEurekaClient
@MapperScan({"com.qlhx.service.auth.realize.mapper"})
@RefreshScope
public class AuthServiceApplication {
    private static Logger logger = LoggerFactory.getLogger(AuthServiceApplication.class);

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(AuthServiceApplication.class, args);
        //设置上下文
        BeanUtil.setApplicationContext(run);
        logger.info("<-------------auth service start success--------------->");
    }

}

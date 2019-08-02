package com.qlhx.base.realize.main;

import com.qhlx.core.util.bean.BeanUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/20 23:34
 * @Description desc:
 */
@SpringBootApplication(scanBasePackages = {"com.qlhx.base.realize"})
@EnableTransactionManagement
@EnableFeignClients(basePackages = {"com.qlhx.base.api.api"})
@ComponentScan(basePackages = {"com.qlhx.base","com.qhlx.core"})
@EnableEurekaClient
@MapperScan({"com.qlhx.base.realize.mapper"})
@RefreshScope
public class BaseServerApplication {
    private static Logger logger = LoggerFactory.getLogger(BaseServerApplication.class);


    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(BaseServerApplication.class, args);
        //设置上下文
        BeanUtil.setApplicationContext(run);
        logger.info("Base service start success");
    }
}

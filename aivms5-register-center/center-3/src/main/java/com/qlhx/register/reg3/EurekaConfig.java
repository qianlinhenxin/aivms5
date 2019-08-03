package com.qlhx.register.reg3;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/8/3 12:16
 * @Description desc:
 */
@EnableWebSecurity
public class EurekaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
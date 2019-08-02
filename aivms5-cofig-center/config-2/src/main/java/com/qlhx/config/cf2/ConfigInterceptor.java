package com.qlhx.config.cf2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by xb
 * The file is [ PlatformInterceptor] on [ x-file-system ] project
 * The file path is com.itgo.web.interceptor.PlatformInterceptor
 *
 * @versio 1.0.0
 * @Author he ming xi
 * @date 2019/4/8 20:13
 * @description
 */
public class ConfigInterceptor implements HandlerInterceptor {

    @Value("${login.user.name}")
    private  String USER;

    @Value("${login.user.password}")
    private String PASSWORD;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        if("/encrypt/status".equals(uri) || "/encrypt".equals(uri) || "/decrypt".equals(uri)){
            String user = request.getHeader("user");
            String password = request.getHeader("password");
            if("post".equals(method.toLowerCase()) && USER.equals(user) && PASSWORD.equals(password)){
                return true;
            }else{
                returnData(response,"没有权限!");
                return false;
            }
        }
        return false;
    }



    private void returnData(HttpServletResponse response, String msg){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html; charset=utf-8");
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss").format(new Date()));
        try {
            writer = response.getWriter();
            writer.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null)
                writer.close();
        }
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}


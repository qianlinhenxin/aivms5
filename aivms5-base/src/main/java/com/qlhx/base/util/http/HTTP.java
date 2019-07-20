package com.qlhx.base.util.http;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HTTP {

   static Logger logger = LoggerFactory.getLogger(HTTP.class);
    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果<br />
     */
    public static String post(String uri, String body) {
	HttpClient client = new HttpClient();
	client.setTimeout(6000);
	PostMethod method = new PostMethod(uri);
	// for (Map.Entry<String, String> entry : headers.entrySet()) {
	method.addRequestHeader("Content-type",
		"application/json; charset=utf-8");
	method.addRequestHeader("Accept", "application/json");
	// method.setEntity(new StringEntity(jsonParam.toString(),
	// Charset.forName("UTF-8")));

	// method.setRequestHeader(entry.getKey(), entry.getValue());
	// method.setRequestHeader("Accept-Charset", "UTF-8");
	// }
	method.setRequestBody(body);
	try {
	    int statusCode = client.executeMethod(method);
	    logger.info("url:"+uri+" body:"+body+" statusCode:"+statusCode);
	    if (statusCode == HttpStatus.SC_OK) {
		return new String(method.getResponseBody(), "UTF-8");
		// return method.getResponseBodyAsString();
	    }
	    

	} catch (IOException e) {
	    logger.error("请求连接发生错误:"+uri, e);
	    e.printStackTrace();
	} catch (Exception e) {
	    logger.error("请求连接发生错误:"+uri, e);
	    e.printStackTrace();
	}
	return null;
    }
    
    /**
     * post提交表单
     */
    public static String postForm(String uri, String body) {
	HttpClient client = new HttpClient();
	client.setTimeout(6000);
	PostMethod method = new PostMethod(uri);
	method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");//
	method.addRequestHeader("Accept", "application/json");
	method.setRequestBody(body);
	try {
	    int statusCode = client.executeMethod(method);
	    if (statusCode == HttpStatus.SC_OK) {
		return new String(method.getResponseBody(), "UTF-8");
//	    return method.getResponseBodyAsString();
		// return method.getResponseBodyAsString();
	    }else
	    {
		logger.info("====url:"+uri+body+"====result:"+new String(method.getResponseBody(), "UTF-8"));
	    }

	} catch (IOException e) {
	    logger.error("请求连接发生错误:"+uri, e);
	    e.printStackTrace();
	    return e.getMessage();
	} catch (Exception e) {
	    logger.error("请求连接发生错误:"+uri, e);
	    e.printStackTrace();
	    return e.getMessage();
	}
	return null;
    }

    /**
     * 发送 get请求
     */
    @SuppressWarnings("deprecation")
    public static String get(String uri) {
	HttpClient client = new HttpClient();
	client.setTimeout(6000);
	GetMethod method = new GetMethod(uri);
	try {
	    int statusCode = client.executeMethod(method);
	    if (statusCode == HttpStatus.SC_OK) {
		return method.getResponseBodyAsString();
	    }

	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return null;
    }

 
}

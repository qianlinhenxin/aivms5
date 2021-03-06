package com.qhlx.core.util.web;

import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

/**
 * Create by xb
 * The file is [ HttpClient] on [ case ] project
 * The file path is HttpClient
 *
 * @versio 1.0.0
 * @Author he ming xi
 * @date 2019/6/15 22:40
 * @description
 *      desc:基于HttpClient 4.5
 */
public class HttpClient {

    private static final int READ_TIMEOUT = 60000;

    private static final int CONNECT_TIMEOUT = 60000;

    private static Logger logger = LoggerFactory.getLogger(HttpClient.class);


    /**
     * GET 请求
     * @param url
     * @param headerParams
     * @return
     */
    public static String doGet(String url, Map<String, String> headerParams, Map<String, String> requestParams,int connectTimeout, int readTimeout) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        String params = "";
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            if (requestParams != null && !requestParams.isEmpty()) {
                StringBuffer str = new StringBuffer();
                Set set = requestParams.keySet();
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    String key = iter.next().toString();
                    if (requestParams.get(key) == null) {
                        continue;
                    }
                    str.append(key).append("=").append(requestParams.get(key)).append("&");
                }
                if (str.length() > 0) {
                    params = "?" + str.substring(0, str.length() - 1);
                }
            }
            HttpGet httpGet = new HttpGet(url+params);
            // 设置请求头 参数
            if(headerParams != null && !headerParams.isEmpty()){
                for (String key : headerParams.keySet()) {
                    httpGet.setHeader(key, headerParams.get(key));
                }
            }
            if (connectTimeout < 1) {
                connectTimeout = CONNECT_TIMEOUT;
            }
            if (readTimeout < 1) {
                readTimeout = READ_TIMEOUT;
            }
            // 设置配置请求参数
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout)// 连接主机服务超时时间
                    .setConnectionRequestTimeout(connectTimeout)// 请求超时时间
                    .setSocketTimeout(readTimeout)// 数据读取超时时间
                    .build();
            // 为httpGet实例设置配置
            httpGet.setConfig(requestConfig);
            // 执行get请求得到返回对象
            response = httpClient.execute(httpGet);
            // 通过返回对象获取返回数据
            HttpEntity entity = response.getEntity();
            // 通过EntityUtils中的toString方法将结果转换为字符串
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static String doPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<>();
            // 通过map集成entrySet方法获取entity
            Set<Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 发送 post请求访问本地应用并根据传递参数不同返回不同结果<br />
     */
    public static String post(String uri, String body) {
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
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
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
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
        org.apache.commons.httpclient.HttpClient client = new org.apache.commons.httpclient.HttpClient();
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

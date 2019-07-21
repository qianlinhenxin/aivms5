package com.qlhx.service.wechat.realize.utils;

import com.alibaba.fastjson.JSONObject;
import tools.HTTP;

import java.util.HashMap;
import java.util.Map;

public class SmsTest {

	 public static String SendMsg2(String content ,String phone) 
	    {
	    	Map<String,String> paraMap = new HashMap<String,String>();
	    	paraMap.put("appkey", "6ed29a603e2107f0ad77c09550401076");
	    	paraMap.put("content", content);
	    	paraMap.put("num", "tongyong");
	    	paraMap.put("phone", phone);
//	    	String res = HTTP.post(newSmsUrl, JSONObject.toJSONString(paraMap));
	    	String res = HTTP.post("http://wx.xjucan.com/sms/smshandle/sendsmsnew", JSONObject.toJSONString(paraMap));
	    	System.out.println(res);
	    	
	    	return res;
	    }
	    
	    public static void main(String arg[])
	    {
	    	SmsTest.SendMsg2("【钱林访客】663325", "18511893051");
	    }

}

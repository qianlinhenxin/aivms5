package com.qlhx.service.wechat.realize.utils;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	public static String getIpAddr(HttpServletRequest request) {  
		   String ipFromNginx = getHeader(request, "X-Real-IP");  
		   if (StringUtils.isBlank(ipFromNginx) )
		   {
			   String ip = request.getHeader("x-forwarded-for");  
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		           ip = request.getHeader("Proxy-Client-IP");  
		       }  
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		           ip = request.getHeader("WL-Proxy-Client-IP");  
		       }  
		       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
		           ip = request.getRemoteAddr();  
		       }  
		       return ip;  
		   }
		   else
		   {
			   return  ipFromNginx;  
		   }
	      
	   }  
	private static String getHeader(HttpServletRequest request, String headName) {  
	    String value = request.getHeader(headName);  
	    return !StringUtils.isBlank(value) && !"unknown".equalsIgnoreCase(value) ? value : "";
	}  

}

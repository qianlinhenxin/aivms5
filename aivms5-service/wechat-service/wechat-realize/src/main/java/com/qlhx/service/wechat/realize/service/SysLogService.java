package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.USysLogs;

public interface SysLogService {
	
	int add(USysLogs sysLog) throws Exception;
	
	int add(String title, String content, Long userId);

}

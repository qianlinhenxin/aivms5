package com.qlhx.service.logger.realize.service;

import java.util.List;

import com.qlhx.service.logger.realize.model.LoggerLogs;

public interface LoggerService {
	
	int insertSelective(LoggerLogs record);
	
	List<LoggerLogs> list();

}

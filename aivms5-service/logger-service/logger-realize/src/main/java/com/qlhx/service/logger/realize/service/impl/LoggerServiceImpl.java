package com.qlhx.service.logger.realize.service.impl;

import java.util.List;

import com.qlhx.service.logger.realize.dao.LoggerLogsMapper;
import com.qlhx.service.logger.realize.model.LoggerLogs;
import com.qlhx.service.logger.realize.service.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoggerServiceImpl implements LoggerService {
	
	@Autowired
    LoggerLogsMapper loggerLogsMapper;

	@Override
	public int insertSelective(LoggerLogs record) {
		return loggerLogsMapper.insertSelective(record);
	}

	@Override
	public List<LoggerLogs> list() {
		return loggerLogsMapper.list();
	}
	
	

}

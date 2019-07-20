package com.qlhx.service.logger.realize.dao;

import com.qlhx.service.logger.realize.model.LoggerLogs;

import java.util.List;


public interface LoggerLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoggerLogs record);

    int insertSelective(LoggerLogs record);

    LoggerLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoggerLogs record);

    int updateByPrimaryKey(LoggerLogs record);
    
    List<LoggerLogs> list();
}
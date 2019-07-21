package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.USysLogs;

public interface USysLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(USysLogs record)  ;

    int insertSelective(USysLogs record);

    USysLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(USysLogs record);

    int updateByPrimaryKeyWithBLOBs(USysLogs record);

    int updateByPrimaryKey(USysLogs record);
}
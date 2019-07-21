package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UPoliceLogs;

public interface UPoliceLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UPoliceLogs record);

    int insertSelective(UPoliceLogs record);

    UPoliceLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UPoliceLogs record);

    int updateByPrimaryKey(UPoliceLogs record);
}
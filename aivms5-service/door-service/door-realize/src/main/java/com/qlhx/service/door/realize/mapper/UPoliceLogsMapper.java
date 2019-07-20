package com.qlhx.service.door.realize.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UPoliceLogsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UPoliceLogs record);

    int insertSelective(UPoliceLogs record);

    UPoliceLogs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UPoliceLogs record);

    int updateByPrimaryKey(UPoliceLogs record);
}
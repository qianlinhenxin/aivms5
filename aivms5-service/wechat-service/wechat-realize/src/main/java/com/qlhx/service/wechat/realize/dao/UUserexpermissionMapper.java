package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UUserexpermission;

public interface UUserexpermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UUserexpermission record);

    int insertSelective(UUserexpermission record);

    UUserexpermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UUserexpermission record);

    int updateByPrimaryKey(UUserexpermission record);
    
    UUserexpermission selectUserexpermission(UUserexpermission userexpermission);
}
package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.UWxuserPreapplygoup;

import java.util.Map;

public interface UWxuserPreapplygoupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UWxuserPreapplygoup record);

    int insertSelective(UWxuserPreapplygoup record);

    UWxuserPreapplygoup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UWxuserPreapplygoup record);

    int updateByPrimaryKey(UWxuserPreapplygoup record);
    
    UWxuserPreapplygoup selectByParam(Map param);
}
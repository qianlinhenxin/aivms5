package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UWxappid;

public interface UWxappidMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UWxappid record);

    int insertSelective(UWxappid record);

    UWxappid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UWxappid record);

    int updateByPrimaryKey(UWxappid record);
    
    UWxappid selectByAreaCode(String areaCode);
}
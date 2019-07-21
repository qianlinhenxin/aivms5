package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UUserArea;

public interface UUserAreaMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByUserId(Long uid);

    int insert(UUserArea record);

    int insertSelective(UUserArea record);

    UUserArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUserArea record);

    int updateByPrimaryKey(UUserArea record);
}
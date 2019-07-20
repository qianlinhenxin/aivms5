package com.qlhx.service.base.realize.mapper;


import com.qlhx.service.base.realize.model.BaseBlacklistShare;

public interface BaseBlacklistShareMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseBlacklistShare record);

    int insertSelective(BaseBlacklistShare record);

    BaseBlacklistShare selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseBlacklistShare record);

    int updateByPrimaryKey(BaseBlacklistShare record);
}
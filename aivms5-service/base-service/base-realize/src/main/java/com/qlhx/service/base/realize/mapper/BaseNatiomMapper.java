package com.qlhx.service.base.realize.mapper;


import com.qlhx.service.base.realize.model.BaseNatiom;

public interface BaseNatiomMapper {
    int deleteByPrimaryKey(Integer nationnum);

    int insert(BaseNatiom record);

    int insertSelective(BaseNatiom record);

    BaseNatiom selectByPrimaryKey(Integer nationnum);

    int updateByPrimaryKeySelective(BaseNatiom record);

    int updateByPrimaryKey(BaseNatiom record);
}
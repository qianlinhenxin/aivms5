package com.qlhx.service.base.realize.mapper;


import com.qlhx.service.base.realize.model.BaseVisitReasons;

import java.util.List;

public interface BaseVisitReasonsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseVisitReasons record);

    int insertSelective(BaseVisitReasons record);

    BaseVisitReasons selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseVisitReasons record);

    int updateByPrimaryKey(BaseVisitReasons record);
    
    List<BaseVisitReasons> list();
}
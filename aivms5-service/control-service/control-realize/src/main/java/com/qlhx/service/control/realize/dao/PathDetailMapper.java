package com.qlhx.service.control.realize.dao;


import com.qlhx.service.control.realize.model.PathDetail;

import java.util.List;

public interface PathDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PathDetail record);

    int insertSelective(PathDetail record);

    PathDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PathDetail record);

    int updateByPrimaryKey(PathDetail record);
    
    List<PathDetail> list(Integer pathId);
}
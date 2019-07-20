package com.qlhx.service.control.realize.dao;

import com.qlhx.service.control.realize.model.Path;

import java.util.List;


public interface PathMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Path record);

    int insertSelective(Path record);

    Path selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Path record);

    int updateByPrimaryKey(Path record);
    
    List<Path> list();
}
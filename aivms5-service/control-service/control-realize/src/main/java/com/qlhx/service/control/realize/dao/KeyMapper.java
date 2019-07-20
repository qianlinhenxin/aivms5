package com.qlhx.service.control.realize.dao;


import com.qlhx.service.control.realize.model.Key;

import java.util.List;

public interface KeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Key record);

    int insertSelective(Key record);

    Key selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Key record);

    int updateByPrimaryKey(Key record);
    
    List<Key> list();
}
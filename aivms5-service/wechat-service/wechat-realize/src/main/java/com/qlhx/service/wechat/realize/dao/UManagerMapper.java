package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UManager;

import java.util.Map;

public interface UManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UManager record);

    int insertSelective(UManager record);

    UManager selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UManager record);

    int updateByPrimaryKey(UManager record);
    
    UManager selectByUsernameAndPass(Map param) ;
}
package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.UBlacklistShare;

import java.util.List;
import java.util.Map;

public interface UBlacklistShareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UBlacklistShare record);

    int insertSelective(UBlacklistShare record);

    UBlacklistShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UBlacklistShare record);

    int updateByPrimaryKey(UBlacklistShare record);
    
    List<UBlacklistShare> selectByParam(Map param);
}
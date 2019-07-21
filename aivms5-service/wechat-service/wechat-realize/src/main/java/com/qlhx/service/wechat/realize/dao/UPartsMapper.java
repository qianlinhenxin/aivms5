package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.UParts;

import java.util.List;
import java.util.Map;

public interface UPartsMapper {
    int deleteByPrimaryKey(Long primaryid);

    int insert(UParts record);

    int insertSelective(UParts record);

    UParts selectByPrimaryKey(Long primaryid);

    int updateByPrimaryKeySelective(UParts record);

    int updateByPrimaryKey(UParts record);
    
    int updateByCompanyCodeAndId(UParts record);
    
    List<UParts> list(Map param);
}
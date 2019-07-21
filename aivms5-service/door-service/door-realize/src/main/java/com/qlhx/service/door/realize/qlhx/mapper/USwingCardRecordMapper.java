package com.qlhx.service.door.realize.qlhx.mapper;


import com.qlhx.service.door.realize.qlhx.model.USwingCardRecord;

import java.util.List;
import java.util.Map;

public interface USwingCardRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(USwingCardRecord record);

    int insertSelective(USwingCardRecord record);

    USwingCardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(USwingCardRecord record);

    int updateByPrimaryKey(USwingCardRecord record);
    
    List<USwingCardRecord> list(Map param);
}
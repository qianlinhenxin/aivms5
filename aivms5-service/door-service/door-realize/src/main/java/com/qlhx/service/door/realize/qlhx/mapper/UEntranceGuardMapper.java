package com.qlhx.service.door.realize.qlhx.mapper;


import com.qlhx.service.door.realize.qlhx.model.UEntranceGuard;

import java.util.List;

public interface UEntranceGuardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UEntranceGuard record);

    int insertSelective(UEntranceGuard record);

    UEntranceGuard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UEntranceGuard record);

    int updateByPrimaryKey(UEntranceGuard record);
    
    List<UEntranceGuard> list();
    
    UEntranceGuard selectBySn(String sn);
}
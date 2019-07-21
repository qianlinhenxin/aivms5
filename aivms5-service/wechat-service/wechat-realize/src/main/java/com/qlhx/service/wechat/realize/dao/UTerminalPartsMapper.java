package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UTerminalParts;

public interface UTerminalPartsMapper {
    int deleteByPrimaryKey(Long primaryid);

    int insert(UTerminalParts record);

    int insertSelective(UTerminalParts record);

    UTerminalParts selectByPrimaryKey(Long primaryid);

    int updateByPrimaryKeySelective(UTerminalParts record);

    int updateByPrimaryKey(UTerminalParts record);
    
    int updateByCompanyCodeAndId(UTerminalParts record);
}
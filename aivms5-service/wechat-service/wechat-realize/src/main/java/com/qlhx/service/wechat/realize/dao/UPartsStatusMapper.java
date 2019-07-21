package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UPartsStatus;

public interface UPartsStatusMapper {
    int deleteByPrimaryKey(Long primaryid);

    int insert(UPartsStatus record);

    int insertSelective(UPartsStatus record);

    UPartsStatus selectByPrimaryKey(Long primaryid);

    int updateByPrimaryKeySelective(UPartsStatus record);

    int updateByPrimaryKey(UPartsStatus record);
    
    int updateByCompanyCodeAndId(UPartsStatus record);
}
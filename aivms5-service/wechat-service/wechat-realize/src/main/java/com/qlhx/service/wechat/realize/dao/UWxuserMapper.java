package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.DownWxUserModel;
import com.qlhx.service.wechat.realize.model.UWxuser;

import java.util.List;
import java.util.Map;

public interface UWxuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UWxuser record);

    int insertSelective(UWxuser record);

    UWxuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UWxuser record);

    int updateByPrimaryKey(UWxuser record);
    
    List<UWxuser> listByDownWxUserModel(DownWxUserModel downWxUserModel);
    
    UWxuser selectWxUserByParam(Map param);
    
    List<UWxuser> selectWxUserByApplyDetail(Map param);
    
    UWxuser selectWxUserByUid(Map param);
    
    int updateByOpenIdAndArea(UWxuser user);
    
    UWxuser selectWxUserByTel(Map param);
    
    int updateWxuserUidtoNull(UWxuser user);
    
}
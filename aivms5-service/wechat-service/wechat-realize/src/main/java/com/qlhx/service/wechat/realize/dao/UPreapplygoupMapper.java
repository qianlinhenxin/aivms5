package com.qlhx.service.wechat.realize.dao;




import com.qlhx.service.wechat.realize.model.DownWxUserModel;
import com.qlhx.service.wechat.realize.model.UPreapplygoup;

import java.util.List;
import java.util.Map;

public interface UPreapplygoupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UPreapplygoup record);

    int insertSelective(UPreapplygoup record);

    UPreapplygoup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UPreapplygoup record);

    int updateByPrimaryKey(UPreapplygoup record);
    
    List<UPreapplygoup> preapplygouplist(DownWxUserModel downWxUserModel);
    
    Integer agreeOrRefusedUPreapplygoup(UPreapplygoup preapplygoup);
    
    UPreapplygoup selectBySyncId(Map param); 
    
    int teamSubmitApply(UPreapplygoup preapplygoup);
    
    int updateJoinPersonNum(Map param);
}
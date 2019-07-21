package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.UPreapplygoupdetail;

import java.util.List;
import java.util.Map;

public interface UPreapplygoupdetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UPreapplygoupdetail record);

    int insertSelective(UPreapplygoupdetail record);

    UPreapplygoupdetail selectByPrimaryKey(Integer id);

    int updateByApplyid(UPreapplygoupdetail record);
    
    int updateByPrimaryKeySelective(UPreapplygoupdetail record);

    int updateByPrimaryKey(UPreapplygoupdetail record);
    
    List<UPreapplygoupdetail> preapplygoupdetaillist(Map param);
    
    UPreapplygoupdetail selectByOpenIdAndSyncIdAndAreaCode(Map param);
    
    Integer updateCardNum(UPreapplygoupdetail preapplygoupdetail);
    
    int updateOpenIdIsNull(Map param);
    
    List<UPreapplygoupdetail> selectBySyncId(Map param);
    
    UPreapplygoupdetail selectByApplyid(Map param);
}
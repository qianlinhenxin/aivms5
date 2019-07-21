package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UArea;

import java.util.List;
import java.util.Map;

public interface UAreaMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(UArea record);

    int insertSelective(UArea record);

    UArea selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UArea record);

    int updateByPrimaryKey(UArea record);
    
    UArea selectUAreaByPama(UArea record) throws Exception;
    
    List<UArea> list(UArea area);
    
    List<Map> listTree(UArea area);
    
    int updateByCompanyCodeAndId(UArea area);
    
    List<UArea> listByCompanyCode(Map<String, Object> param);
    
    List<String> selectAreasByUserId(Long userId);
    
    String findMaxAreaCode(Map param);
    
    UArea selectByAreaCode(String areaCode);
}
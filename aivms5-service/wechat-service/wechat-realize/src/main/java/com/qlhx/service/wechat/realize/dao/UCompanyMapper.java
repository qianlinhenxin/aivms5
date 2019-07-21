package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.UCompany;

import java.util.List;
import java.util.Map;

public interface UCompanyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UCompany record);

    int insertSelective(UCompany record);

    UCompany selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UCompany record);

    int updateByPrimaryKey(UCompany record);
    
    List<UCompany> list(UCompany company);
    
    String selectMaxCompanyCode();
    
    List<Map> listTree(UCompany company);
    
    List<Map> companyDistribution(Map param);
}
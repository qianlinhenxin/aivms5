package com.qlhx.service.wechat.realize.dao;




import com.qlhx.service.wechat.realize.model.UMember;

import java.util.List;
import java.util.Map;

public interface UMemberMapper {
    int deleteByPrimaryKey(Long id);
    
    int deleteByUid(Map param);

    int insert(UMember record);

    int insertSelective(UMember record) throws Exception;

    UMember selectByPrimaryKey(Long id);

    UMember selectUserInfoByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(UMember record) throws Exception;

    int updateByPrimaryKey(UMember record);
    
    UMember selectMemberByPama(UMember user) throws Exception;
    
    int updateByCompanyCodeAndId(UMember record) throws Exception;
    
    int findCount(Map param);
    
    List countReport(Map param);
    
    List newCountReportByMonth(Map param);
    
    List newCountReportByYear(Map param);
    
    UMember login(Map<String, Object> map);
    
    UMember selectByPhone(Map param);
    
    UMember selectByUid(Map param);

}
package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.UMember;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface UMemberService {

    int deleteByPrimaryKey(Long id);
    
    int deleteByUid(int uid, String areaCode);

    UMember insert(UMember record);

    UMember insertSelective(UMember record) throws Exception;

    UMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UMember record) throws Exception;

    int updateByPrimaryKey(UMember record);


    Integer insertOrUpdateMembers(String companyCode, String areaCode, String terminalCode, List<UMember> memberList) throws Exception;

    Integer insertOrUpdateMembers(List<UMember> userList) throws Exception;

    Pagination<UMember> findUserAndRole(ModelMap modelMap,
                                        Integer pageNo, Integer pageSize);
    
    int findCount(Map param);
    
    List countReport(Map param);
    
    List newCountReportByMonth(Map param);
    
    List newCountReportByYear(Map param);
    
    UMember login(String email, String pswd, String areaCode);
    
    UMember selectByPhone(String phone, String areaCode);
    
    UMember selectByUid(Integer uid, String areaCode);
}

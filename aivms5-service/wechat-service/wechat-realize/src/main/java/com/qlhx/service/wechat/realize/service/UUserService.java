package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.*;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface UUserService {

    int deleteByPrimaryKey(Long id);

    UUser insert(UUser record);

    UUser insertSelective(UUser record) throws Exception;

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record) throws Exception;

    int updateByPrimaryKey(UUser record);

    UUser login(String email, String pswd);

    UUser findUserByEmail(String email);

    Pagination<UUser> findByPage(Map<String, Object> resultMap, Integer pageNo,
                                 Integer pageSize);

    Map<String, Object> deleteUserById(String ids);

    Map<String, Object> updateForbidUserById(Long id, Long status);

    Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap,
                                                     Integer pageNo, Integer pageSize);

    List<URoleBo> selectRoleByUserId(Long id);

    Map<String, Object> addRole2User(Long userId, String ids);
    
    int addRoleUser(Long userId, String ids);

    Map<String, Object> deleteRoleByUserIds(String userIds);

    List<ComboResult> findUserComboList();

    List<UUser> selectUserByDeptId(Integer id);

    Object findNationComboList();

    List<UUser> selectExportUserList(ModelMap modelMap);

    UUserNation selectNationByName(String nName);

    Map<String, Object> insertUsers(List<UUser> userList);

    UUser findByIdNum(String fileName);

    List<UUser> findUserByPhone(String phone, String email);

    boolean findByPhoneOrEmailCount(String s, String s1, String cardnum);

    List<UUserNation> selectAllNation();

    Integer insertMembers(List<UUser> members) throws Exception;

    UUser findByPhone(String phone) throws Exception;

    UUser findByEcardNum(String ecardNum) throws Exception;

    UUser findByAllPhone(String phone);
    
    int addUserArea(UUser record) throws Exception;
    
    int updateUserArea(UUser record) throws Exception;
    
}

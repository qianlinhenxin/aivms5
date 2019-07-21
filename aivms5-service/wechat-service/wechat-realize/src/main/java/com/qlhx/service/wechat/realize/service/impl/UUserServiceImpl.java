package com.qlhx.service.wechat.realize.service.impl;


import com.qlhx.service.wechat.realize.dao.UUserAreaMapper;
import com.qlhx.service.wechat.realize.dao.UUserMapper;
import com.qlhx.service.wechat.realize.dao.UUserNationMapper;
import com.qlhx.service.wechat.realize.dao.UUserRoleMapper;
import com.qlhx.service.wechat.realize.model.*;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.UUserService;
import com.qlhx.service.wechat.realize.shiro.token.manager.TokenManager;
import com.qlhx.service.wechat.realize.utils.LoggerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UUserServiceImpl extends BaseMybatisDao<UUserMapper> implements
        UUserService {
    /***
     * 用户手动操作Session
     * */
//    @Autowired
//    CustomSessionManager customSessionManager;
    @Autowired
    UUserMapper userMapper;
    @Autowired
    UUserRoleMapper userRoleMapper;

    @Autowired
    private UUserNationMapper userNationMapper;
    
    @Autowired
    private UUserAreaMapper userAreaMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
	return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public UUser insert(UUser entity) {
	userMapper.insert(entity);
	return entity;
    }

    @Override
    public UUser insertSelective(UUser entity) throws Exception {
	userMapper.insertSelective(entity);
	return entity;
    }

    @Override
    public UUser selectByPrimaryKey(Long id) {
	return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(UUser entity) {
	return userMapper.updateByPrimaryKey(entity);
    }

    @Override
    public int updateByPrimaryKeySelective(UUser entity) throws Exception {
	return userMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public UUser login(String email, String pswd) {
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("email", email);
	map.put("pswd", pswd);
	UUser user = userMapper.login(map);
	return user;
    }

    @Override
    public UUser findUserByEmail(String email) {
	return userMapper.findUserByEmail(email);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<UUser> findByPage(Map<String, Object> resultMap,
                                        Integer pageNo, Integer pageSize) {
	return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Map<String, Object> deleteUserById(String ids) {
	Map<String, Object> resultMap = new HashMap<String, Object>();
	try {
	    int count = 0;
	    String resultMsg = "";
	    String[] idArray = new String[] {};
	    if (StringUtils.contains(ids, ",")) {
		idArray = ids.split(",");
	    } else {
		idArray = new String[] { ids };
	    }

	    for (String id : idArray) {
		UUser user = userRoleMapper.checkUserRoleById(Integer
			.parseInt(id));
		if (null != user) {
		    resultMsg += user.getNickname() + ",";
		    continue;
		}
		count += this.deleteByPrimaryKey(new Long(id));
	    }
	    resultMap.put("status", 200);
	    resultMap.put("count", count);
	    resultMap.put("message", StringUtils.stripEnd(resultMsg, ","));
	} catch (Exception e) {
	    LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
	    resultMap.put("status", 500);
	    resultMap.put("message", "删除出现错误，请刷新后再试！");
	}
	return resultMap;
    }

    @Override
    public Map<String, Object> updateForbidUserById(Long id, Long status) {
	Map<String, Object> resultMap = new HashMap<String, Object>();
	try {
	    UUser user = selectByPrimaryKey(id);
	    user.setStatus(status);
	    updateByPrimaryKeySelective(user);

	    // 如果当前用户在线，需要标记并且踢出
//	    customSessionManager.forbidUserById(id, status);

	    resultMap.put("status", 200);
	} catch (Exception e) {
	    resultMap.put("status", 500);
	    resultMap.put("message", "操作失败，请刷新再试！");
	    LoggerUtils.fmtError(getClass(), "禁止或者激活用户登录失败，id[%s],status[%s]",
		    id, status);
	}
	return resultMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Pagination<UserRoleAllocationBo> findUserAndRole(ModelMap modelMap,
                                                            Integer pageNo, Integer pageSize) {
	return super.findPage("findUserAndRole", "findCount", modelMap, pageNo,
		pageSize);
    }

    @Override
    public List<URoleBo> selectRoleByUserId(Long id) {
	return userMapper.selectRoleByUserId(id);
    }

    @Override
    public Map<String, Object> addRole2User(Long userId, String ids) {
	Map<String, Object> resultMap = new HashMap<String, Object>();
	int count = 0;
	try {
	    // 先删除原有的。
	    userRoleMapper.deleteByUserId(userId);
	    // 如果ids,role 的id 有值，那么就添加。没值象征着：把这个用户（userId）所有角色取消。
	    if (StringUtils.isNotBlank(ids)) {
		String[] idArray = null;

		// 这里有的人习惯，直接ids.split(",") 都可以，我习惯这么写。清楚明了。
		if (StringUtils.contains(ids, ",")) {
		    idArray = ids.split(",");
		} else {
		    idArray = new String[] { ids };
		}
		// 添加新的。
		for (String rid : idArray) {
		    // 这里严谨点可以判断，也可以不判断。这个{@link StringUtils 我是重写了的}
		    if (StringUtils.isNotBlank(rid)) {
			UUserRole entity = new UUserRole(userId, new Long(rid));
			count += userRoleMapper.insertSelective(entity);
		    }
		}
	    }
	    resultMap.put("status", 200);
	    resultMap.put("message", "操作成功");
	} catch (Exception e) {
	    resultMap.put("status", 200);
	    resultMap.put("message", "操作失败，请重试！");
	}
	// 清空用户的权限，迫使再次获取权限的时候，得重新加载
	TokenManager.clearUserAuthByUserId(userId);
	resultMap.put("count", count);
	return resultMap;
    }
    
    

    @Override
	public int addRoleUser(Long userId, String ids) 
	{
		int count = 0;
		userRoleMapper.deleteByUserId(userId);
		if (StringUtils.isNotBlank(ids)) {
			for (String rid : ids.split(",")) {
				UUserRole entity = new UUserRole(userId, new Long(rid));
				count += userRoleMapper.insertSelective(entity);

			}
		}
		return count;
	}

	@Override
    public Map<String, Object> deleteRoleByUserIds(String userIds) {

	Map<String, Object> resultMap = new HashMap<String, Object>();
	try {
	    resultMap.put("userIds", userIds);
	    userRoleMapper.deleteRoleByUserIds(resultMap);
	    resultMap.put("status", 200);
	    resultMap.put("message", "操作成功");
	} catch (Exception e) {
	    resultMap.put("status", 200);
	    resultMap.put("message", "操作失败，请重试！");
	}
	return resultMap;

    }

    @Override
    public List<ComboResult> findUserComboList() {
	return userMapper.findUserComboList();
    }

    @Override
    public List<UUser> selectUserByDeptId(Integer id) {
	return userMapper.selectUserByDeptId(id);
    }

    @Override
    public Object findNationComboList() {
	return userNationMapper.selectNationComboResult();
    }

    @Override
    public List<UUser> selectExportUserList(ModelMap modelMap) {
	return userMapper.selectExportUserList(modelMap);
    }

    @Override
    public UUserNation selectNationByName(String nName) {
	return userNationMapper.selectByValue(nName);
    }

    @Override
    public Map<String, Object> insertUsers(List<UUser> userList) {
	Map<String, Object> resultMap = new HashMap<>();
	try {

	    int count = 0;
	    int errorcount = 0;
	    for (UUser user : userList) {
		// UUser userByEmail = this.findUserByEmail(user.getEmail());
		// if (null != userByEmail) {
		// errorcount++;
		// continue;
		// }
		List<UUser> userByPhone = this.findUserByPhone(user.getPhone(),
			user.getEmail());
		if (null != userByPhone && userByPhone.size() > 0) {
		    errorcount++;
		    continue;
		}
		this.insertSelective(user);
		count++;
	    }
	    resultMap.put("status", 200);
	    resultMap.put("successCount", count);
	    resultMap.put("errorCount", errorcount);
	    resultMap.put("message", "添加成功!");
	} catch (Exception e) {
	    resultMap.put("status", 500);
	    resultMap.put("message", "添加失败！");
	    LoggerUtils.fmtError(getClass(), e, "导入员工出错visitorIds Id[%s]",
		    userList);
	}
	return resultMap;
    }

    @Override
    public Integer insertMembers(List<UUser> members) throws Exception {
	// TODO Auto-generated method stub
	return userMapper.insertMembers(members);
    }

    @Override
    public UUser findByPhone(String phone) throws Exception {
	return userMapper.findUserByPhoneNum(phone);
    }

    @Override
    public UUser findByEcardNum(String ecardNum) throws Exception {
	return userMapper.findUserByCardNum(ecardNum);
    }

    @Override
    public UUser findByAllPhone(String phone) {
	return userMapper.findByPhone(phone);
    }

    @Override
    public List<UUser> findUserByPhone(String phone, String email) {
	return userMapper.findByPhoneOreMail(phone, email);
    }

    @Override
    public boolean findByPhoneOrEmailCount(String s, String s1, String cardnum) {
	Integer userCount = userMapper.findByPhoneOrEmailCount(s, s1, cardnum);
	return userCount > 0;
    }

    @Override
    public List<UUserNation> selectAllNation() {
	return userNationMapper.selectAllNation();
    }

    @Override
    public UUser findByIdNum(String idNum) {
	return userMapper.findByIdNum(idNum);
    }
    
    @Override
    @Transactional
    public int addUserArea(UUser record) throws Exception
    {
    	int i = userMapper.insertSelective(record);
    	int type = record.getType();
    	if (type == 2)//区域
    	{
    		String[] areaCodeArr = record.getAreaCodes().split(",");
    		for(String areaCode : areaCodeArr)
    		{
    			UUserArea userArea = new UUserArea();
    			userArea.setUid(record.getId());
    			userArea.setAreacode(areaCode);
    			userArea.setCreatedate(new Date());
    			userArea.setCreateuser(record.getCreateUid());
    			userAreaMapper.insertSelective(userArea);
    		}
    	}
    	return 0;
    }

	@Override
	@Transactional
	public int updateUserArea(UUser record) throws Exception {
		int i = userMapper.updateByPrimaryKeySelective(record);
    	int type = record.getType();
    	if (type == 2)//区域
    	{
    		userAreaMapper.deleteByUserId(record.getId());
    		String[] areaCodeArr = record.getAreaCodes().split(",");
    		for(String areaCode : areaCodeArr)
    		{
    			UUserArea userArea = new UUserArea();
    			userArea.setUid(record.getId());
    			userArea.setAreacode(areaCode);
    			userArea.setCreatedate(new Date());
    			userArea.setCreateuser(record.getCreateUid());
    			userAreaMapper.insertSelective(userArea);
    		}
    	}
    	return 0;
	}
    
    

}

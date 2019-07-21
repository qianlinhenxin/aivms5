package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.URole;
import com.qlhx.service.wechat.realize.model.URoleBo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface URoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    URole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

	Set<String> findRoleByUserId(Long id);

	List<URole> findNowAllPermission(Map<String, Object> map);
	
	void initData();
	
	List<URoleBo> findAllByUserId(String userId);
}
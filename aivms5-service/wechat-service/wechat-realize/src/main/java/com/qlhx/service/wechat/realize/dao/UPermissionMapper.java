package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.UPermission;
import com.qlhx.service.wechat.realize.model.UPermissionBo;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UPermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UPermission record);

    int insertSelective(UPermission record);

    UPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);

	List<UPermissionBo> selectPermissionById(Long id);
	//根据用户ID获取权限的Set集合
	Set<String> findPermissionByUserId(Long id);
	
	List<UPermission>  selectPermissionListByUserId(Long userId);
	
	List<UPermission>  selectPermissionListByRoleId(Map param);
	
	List<UPermission> selectPermissionAll();
}
package com.qlhx.service.wechat.realize.service;




import com.qlhx.service.wechat.realize.model.UPermission;
import com.qlhx.service.wechat.realize.model.UPermissionBo;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PermissionService {

	int deleteByPrimaryKey(Long id);

	UPermission insert(UPermission record);

    UPermission insertSelective(UPermission record);

    UPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);

	Map<String, Object> deletePermissionById(String ids);

	Pagination<UPermission> findPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
	List<UPermissionBo> selectPermissionById(Long id);

	Map<String, Object> addPermission2Role(Long roleId, String ids);

	int addPermissionRole(Long roleId, String ids);

	Map<String, Object> deleteByRids(String roleIds);
	//根据用户ID查询权限（permission），放入到Authorization里。
	Set<String> findPermissionByUserId(Long userId);
	
	/**
	 * 根据账号ID查询权限列表
	 * @return
	 */
	List<UPermission> selectPermissionListByUserId(Long userId);
	
	List<UPermission>  selectPermissionListByRoleId(Map param);
	
	List<UPermission> selectPermissionAll();
	
}

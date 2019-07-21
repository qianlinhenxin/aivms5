package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.RolePermissionAllocationBo;
import com.qlhx.service.wechat.realize.model.URole;
import com.qlhx.service.wechat.realize.model.URoleBo;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RoleService {

	int deleteByPrimaryKey(Long id);

    int insert(URole record);

    int insertSelective(URole record);

    URole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(URole record);

    int updateByPrimaryKey(URole record);

	Pagination<URole> findPage(Map<String, Object> resultMap, Integer pageNo,
                               Integer pageSize);

	Map<String, Object> deleteRoleById(String ids);

	Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(
            Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
	//根据用户ID查询角色（role），放入到Authorization里。
	Set<String> findRoleByUserId(Long userId);

	List<URole> findNowAllPermission();
	
	List<URoleBo> findAllByUserId(String userId);
	//初始化数据
	void initData();
}

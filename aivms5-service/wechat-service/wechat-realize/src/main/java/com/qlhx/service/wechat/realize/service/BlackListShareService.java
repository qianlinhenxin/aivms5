package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.UBlacklistShare;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

public interface BlackListShareService {
	
	 int insert(UBlacklistShare record);

	 int insertSelective(UBlacklistShare record);

	 UBlacklistShare selectByPrimaryKey(Integer id);

	 int updateByPrimaryKeySelective(UBlacklistShare record);

	 int updateByPrimaryKey(UBlacklistShare record);
	
	 Pagination<UBlacklistShare> selectBlacklistShare(Map<String, Object> modelMap, Integer pageNo, Integer pageSize);
	 
	 List<UBlacklistShare> selectByParam(Map param);
	 

}

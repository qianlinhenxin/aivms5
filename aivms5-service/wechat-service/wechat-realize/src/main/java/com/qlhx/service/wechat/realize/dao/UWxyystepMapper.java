package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.UWxyystep;

import java.util.List;
import java.util.Map;

public interface UWxyystepMapper {
	int deleteByPrimaryKey(Integer primaryid);

	int insert(UWxyystep record);

	int insertSelective(UWxyystep record);

	UWxyystep selectByPrimaryKey(Integer primaryid);

	int updateByPrimaryKeySelective(UWxyystep record);

	int updateByIdAndAreaCode(UWxyystep record);

	UWxyystep selectIdAndAreaCode(UWxyystep record);

	int updateByPrimaryKey(UWxyystep record);

	List<UWxyystep> list(Map<String, String> param);
}
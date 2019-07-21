package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.UWxyystep;

import java.util.List;

public interface WxyystepService {
	
	Integer insertOrUpdateWxyystep(List<UWxyystep> wxyystepList) throws Exception;
	
	int insertSelective(UWxyystep record);
	
	int insert(String syncId, String applyId, String wxyyremark, Integer wxyystatus, Integer yytype, String areacode);

	List<UWxyystep> list(String syncId, String applyId, String areaCode);

}

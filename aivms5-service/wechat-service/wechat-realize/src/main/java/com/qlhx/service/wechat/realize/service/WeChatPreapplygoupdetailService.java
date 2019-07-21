package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.UPreapplygoupdetail;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;



public interface WeChatPreapplygoupdetailService {
	
	int insertSelective(UPreapplygoupdetail record);
	
	Pagination<UPreapplygoupdetail> preApplyGroupdetailList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	UPreapplygoupdetail selectByOpenIdAndSyncIdAndAreaCode(String openId, String syncId, String areaCode);

	int joinActive(UPreapplygoupdetail record) throws Exception;
	
	int updateOpenIdIsNull(String tel, String openId, String areaCode);
	
	List<UPreapplygoupdetail> selectBySyncId(String syncid, String areaCode);
	
	int updateByPrimaryKeySelective(UPreapplygoupdetail record);
	
	int updateByApplyid(UPreapplygoupdetail record);
	
	UPreapplygoupdetail selectByApplyid(String applyid, String areaCode);
}

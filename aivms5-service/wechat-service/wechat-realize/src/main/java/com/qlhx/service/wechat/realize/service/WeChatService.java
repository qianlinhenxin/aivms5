package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.DownWxUserModel;
import com.qlhx.service.wechat.realize.model.UPreapplygoup;
import com.qlhx.service.wechat.realize.model.UPreapplygoupdetail;
import com.qlhx.service.wechat.realize.model.UWxuser;

import java.util.List;
import java.util.Map;


public interface WeChatService {
	
	int insertSelective(UWxuser record);
	
	int updateByPrimaryKeySelective(UWxuser record);
	
	int updateByOpenIdAndArea(UWxuser user);
	
	int updateWxuserUidtoNull(UWxuser user);
	
	List<UWxuser> listByDownWxUserModel(DownWxUserModel downWxUserModel);
	
	int insertWxApply(UPreapplygoup preapplygoup, List<UPreapplygoupdetail> UPreapplygoupdetailList);
	
	Integer insertOrUpdateUPreapplygoup(List<UPreapplygoup> preapplygoupList) throws Exception;
	
	Integer insertOrUpdateUPreapplygoupdetail(List<UPreapplygoupdetail> preapplygoupdetailList) throws Exception;
	
	List<UPreapplygoup> preapplygouplist(DownWxUserModel downWxUserModel);
	
	List<UPreapplygoupdetail> preapplygoupdetaillist(String syncId, String areaCode);
	
	List<UPreapplygoupdetail> preapplygoupdetaillist(String areaCode, String tabTime, String isActive);
	
//	Integer updateUPreapplygoupdetail(UPreapplygoupdetail preapplygoupdetail);
	
	Integer updateCardNum(UPreapplygoupdetail preapplygoupdetail);
	
	Integer updateUPreapplygoup(UPreapplygoup preapplygoup);
	
	Integer agreeOrRefusedUPreapplygoup(UPreapplygoup preapplygoup);
	
	UWxuser selectWxUserByOpenId(String openId, String areaCode);
	
	UWxuser selectWxUserByTel(String tel, String areaCode);
	
	UPreapplygoup selectBySyncId(String syncId, String areaCode, String openId);
	
	UPreapplygoup selectBySyncId(String syncId, String areaCode);
	
	List<UWxuser> selectWxUserByApplyDetail(String syncId, String areaCode);
	
	UWxuser selectWxUserByUid(Integer uid, String areaCode);

}

package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.UPreapplygoup;
import com.qlhx.service.wechat.realize.model.UPreapplygoupdetail;
import com.qlhx.service.wechat.realize.model.UWxuser;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;

import java.util.Map;


public interface WeChatPreApplyService 
{
	Pagination<UPreapplygoup> preApplyGroupList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	Pagination<UPreapplygoup> myPreApplyGroupList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	Pagination<UPreapplygoup> myTeamPreApplyGroupList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	Pagination<UPreapplygoup> activeList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize);
	
	Pagination<UPreapplygoup> myActiveList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	Pagination<UPreapplygoup> myShareActiveList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	Pagination<UPreapplygoup> myPreApplyGroupHistoryList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	Pagination<UPreapplygoup> myReceiveInviteList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) ;
	
	int personalApply(UPreapplygoup preapplygoup, UWxuser wxuser);
	
	int agentApply(UPreapplygoup preapplygoup, UPreapplygoupdetail preapplygoupdetail, UWxuser wxuser);
	
	int agentApply(UPreapplygoup preapplygoup, UPreapplygoupdetail preapplygoupdetail);
	
	int teamApply(UPreapplygoup preapplygoup);
	
	int teamSubmitApply(UPreapplygoup preapplygoup);
	
	int insertSelective(UPreapplygoup preapplygoup);

}

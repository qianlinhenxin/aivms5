package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.UUserexpermission;

import java.util.List;


public interface UserexpermissionService {
	
	Integer insertOrUpdateUserexpermission(List<UUserexpermission> userexpermissionList) throws Exception;
	
	UUserexpermission selectUserexpermission(UUserexpermission userexpermission);
	
	int  updateByPrimaryKeySelective(UUserexpermission record);

}

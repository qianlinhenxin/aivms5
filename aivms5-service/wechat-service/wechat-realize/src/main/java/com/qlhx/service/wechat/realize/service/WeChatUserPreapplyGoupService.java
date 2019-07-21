package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.UWxuserPreapplygoup;

public interface WeChatUserPreapplyGoupService {
	
	int insertSelective(UWxuserPreapplygoup record);

    UWxuserPreapplygoup selectByParam(String syncId, String areaCode, String opendId);
	
}

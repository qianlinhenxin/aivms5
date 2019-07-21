package com.qlhx.service.wechat.realize.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.qlhx.service.wechat.realize.dao.UWxuserPreapplygoupMapper;
import com.qlhx.service.wechat.realize.model.UWxuserPreapplygoup;
import com.qlhx.service.wechat.realize.service.WeChatUserPreapplyGoupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeChatUserPreapplyGoupServiceImpl implements WeChatUserPreapplyGoupService {

	@Autowired
    private UWxuserPreapplygoupMapper wxuserPreapplygoupMapper;
	
	@Override
	public int insertSelective(UWxuserPreapplygoup record) {
		return wxuserPreapplygoupMapper.insertSelective(record);
	}

	@Override
	public UWxuserPreapplygoup selectByParam(String syncId, String areaCode, String openId) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("syncId", syncId);
		param.put("areaCode", areaCode);
		param.put("opendId", openId);
		return wxuserPreapplygoupMapper.selectByParam(param);
	}

}

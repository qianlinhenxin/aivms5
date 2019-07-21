package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.UWxappid;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;


public interface WeChatAppIdService {
	
	Pagination<UWxappid> findWeChatAppId(ModelMap modelMap, Integer pageNo, int pageSize);

	int insertWxAppId(UWxappid wxappid);

	UWxappid selectByAreaCode(String areaCode);

	int deleteWxAppId(Integer id);
	
	UWxappid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UWxappid record);

}

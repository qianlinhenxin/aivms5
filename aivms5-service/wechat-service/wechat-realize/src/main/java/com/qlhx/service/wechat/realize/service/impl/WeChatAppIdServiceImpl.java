package com.qlhx.service.wechat.realize.service.impl;

import com.qlhx.service.wechat.realize.dao.UWxappidMapper;
import com.qlhx.service.wechat.realize.model.UWxappid;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.WeChatAppIdService;
import com.qlhx.service.wechat.realize.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;


@Service
public class WeChatAppIdServiceImpl extends BaseMybatisDao<UWxappidMapper> implements WeChatAppIdService {

	@Autowired
    private UWxappidMapper wxappidMapper;
	
	 @Override
	    public Pagination<UWxappid> findWeChatAppId(ModelMap modelMap, Integer pageNo, int pageSize) {
	        return super.findPage(modelMap, pageNo, pageSize);
	    }
	 
	@Override
	public int insertWxAppId(UWxappid wxappid) 
	{
		wxappid.setCreatedate(DateUtil.dateToStringWithTime());
		return wxappidMapper.insertSelective(wxappid);
	}
	
	@Override
	public UWxappid selectByAreaCode(String areaCode) {
		return wxappidMapper.selectByAreaCode(areaCode);
	}

	@Override
	public int deleteWxAppId(Integer id) {
		return wxappidMapper.deleteByPrimaryKey(id);
	}

	@Override
	public UWxappid selectByPrimaryKey(Integer id) {
		return wxappidMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UWxappid record) {
		return wxappidMapper.updateByPrimaryKey(record);
	}
	
	
}

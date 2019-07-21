package com.qlhx.service.wechat.realize.service.impl;

import com.qlhx.service.wechat.realize.dao.UBlacklistShareMapper;
import com.qlhx.service.wechat.realize.model.UBlacklistShare;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.BlackListShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BlackListShareServiceImpl extends BaseMybatisDao<UBlacklistShareMapper> implements BlackListShareService {

	@Autowired
    private UBlacklistShareMapper blacklistShareMapper;
	

	@Override
	public int insert(UBlacklistShare record) {
		return blacklistShareMapper.insert(record);
	}

	@Override
	public int insertSelective(UBlacklistShare record) {
		return blacklistShareMapper.insertSelective(record);
	}

	@Override
	public UBlacklistShare selectByPrimaryKey(Integer id) {
		return blacklistShareMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UBlacklistShare record) {
		return blacklistShareMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UBlacklistShare record) {
		return blacklistShareMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<UBlacklistShare> selectByParam(Map param) {
		return blacklistShareMapper.selectByParam(param);
	}

	@Override
	public Pagination<UBlacklistShare> selectBlacklistShare(Map<String, Object> modelMap, Integer pageNo,
                                                            Integer pageSize) {
		return super.findPage("selectBlacklistShare", "selectBlacklistShareCount", modelMap, pageNo,
                pageSize);
	}
	
	

}

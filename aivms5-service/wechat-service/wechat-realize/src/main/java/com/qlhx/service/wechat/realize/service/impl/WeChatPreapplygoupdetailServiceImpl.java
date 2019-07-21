package com.qlhx.service.wechat.realize.service.impl;

import com.qlhx.service.wechat.realize.dao.UPreapplygoupMapper;
import com.qlhx.service.wechat.realize.dao.UPreapplygoupdetailMapper;
import com.qlhx.service.wechat.realize.model.UPreapplygoupdetail;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.WeChatPreapplygoupdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class WeChatPreapplygoupdetailServiceImpl extends BaseMybatisDao<UPreapplygoupdetailMapper> implements WeChatPreapplygoupdetailService {
	
	@Autowired
    private UPreapplygoupMapper preapplygoupMapper;
	
	@Autowired
    private UPreapplygoupdetailMapper preapplygoupdetailMapper;
	
	@Override
	public int insertSelective(UPreapplygoupdetail record) {
		return preapplygoupdetailMapper.insertSelective(record);
	}

	@Override
	public Pagination<UPreapplygoupdetail> preApplyGroupdetailList(Map<String, Object> modelMap, Integer pageNo,
                                                                   Integer pageSize) {
		return super.findPage(modelMap, pageNo, pageSize);
	}

	@Override
	public UPreapplygoupdetail selectByOpenIdAndSyncIdAndAreaCode(String openId, String syncId, String areaCode) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("openId", openId);
		param.put("syncId", syncId);
		param.put("areaCode", areaCode);
		return preapplygoupdetailMapper.selectByOpenIdAndSyncIdAndAreaCode(param);
	}

	@Override
	@Transactional
	public int joinActive(UPreapplygoupdetail record) throws Exception
	{
		Map<String,String> param = new HashMap<String,String>();
		param.put("syncid", record.getSyncid());
		param.put("areacode", record.getAreacode());
		int j = 0;
		int i = preapplygoupMapper.updateJoinPersonNum(param);
		if (i == 1)
		{
			j = preapplygoupdetailMapper.insertSelective(record);
		}
		else
		{
			return j;
		}
		
		return j;
	}

	@Override
	public int updateOpenIdIsNull(String tel, String openId, String areaCode) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("utel", tel);
		param.put("openid", openId);
		param.put("areacode", areaCode);
		return preapplygoupdetailMapper.updateOpenIdIsNull(param);
	}

	@Override
	public List<UPreapplygoupdetail> selectBySyncId(String syncid, String areaCode) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("syncId", syncid);
		param.put("areaCode", areaCode);
		return preapplygoupdetailMapper.selectBySyncId(param);
	}

	@Override
	public int updateByPrimaryKeySelective(UPreapplygoupdetail record) {
		return preapplygoupdetailMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByApplyid(UPreapplygoupdetail record) {
		return preapplygoupdetailMapper.updateByApplyid(record);
	}

	@Override
	public UPreapplygoupdetail selectByApplyid(String applyid, String areaCode) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("applyid", applyid);
		param.put("areaCode", areaCode);
		return preapplygoupdetailMapper.selectByApplyid(param);
	}

}

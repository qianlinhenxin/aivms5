package com.qlhx.service.wechat.realize.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import com.qlhx.service.wechat.realize.dao.UPreapplygoupMapper;
import com.qlhx.service.wechat.realize.dao.UPreapplygoupdetailMapper;
import com.qlhx.service.wechat.realize.dao.UWxuserMapper;
import com.qlhx.service.wechat.realize.model.UPreapplygoup;
import com.qlhx.service.wechat.realize.model.UPreapplygoupdetail;
import com.qlhx.service.wechat.realize.model.UWxuser;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.WeChatPreApplyService;
import com.qlhx.service.wechat.realize.service.WxyystepService;
import com.qlhx.service.wechat.realize.utils.WxStepEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WeChatPreApplyServiceImpl extends BaseMybatisDao<UPreapplygoupMapper> implements WeChatPreApplyService
{
	
	@Autowired
    private UPreapplygoupMapper preapplygoupMapper;
	
	@Autowired
    private UPreapplygoupdetailMapper preapplygoupdetailMapper;
	
	@Autowired
    private UWxuserMapper wxuserMapper;

	@Autowired
	private WxyystepService xxyystepService;
	
	@Override
    public Pagination<UPreapplygoup> preApplyGroupList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize)
	{
        return super.findPage(modelMap, pageNo,pageSize);
    }

	@Override
	public Pagination<UPreapplygoup> myPreApplyGroupList(Map<String, Object> modelMap, Integer pageNo,
			Integer pageSize) {
		 return super.findPage("findMyAll","findMyCount",modelMap, pageNo,pageSize);
	}
	
	@Override
	public Pagination<UPreapplygoup> myTeamPreApplyGroupList(Map<String, Object> modelMap, Integer pageNo,
			Integer pageSize) {
		return super.findPage("findMyTeamAll","findMyTeamCount",modelMap, pageNo,pageSize);
	}
	
	@Override
	public Pagination<UPreapplygoup> activeList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage("findActiveAll","findActiveCount",modelMap, pageNo,pageSize);
	}

	@Override
	public Pagination<UPreapplygoup> myActiveList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage("findMyActiveAll","findMyActiveCount",modelMap, pageNo,pageSize);
	}
	

	@Override
	public Pagination<UPreapplygoup> myShareActiveList(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage("findMyShareActiveAll","findMyShareActiveCount",modelMap, pageNo,pageSize);
	}
	
	

	@Override
	public Pagination<UPreapplygoup> myPreApplyGroupHistoryList(Map<String, Object> modelMap, Integer pageNo,
			Integer pageSize) {
		return super.findPage("findMyPreApplyGroupHistoryAll","findMyPreApplyGroupHistoryCount",modelMap, pageNo,pageSize);
	}
	
	

	@Override
	public Pagination<UPreapplygoup> myReceiveInviteList(Map<String, Object> modelMap, Integer pageNo,
			Integer pageSize) {
		return super.findPage("findMyReceiveInviteAll","findMyReceiveInviteCount",modelMap, pageNo,pageSize);
	}

	@Override
	@Transactional
	public int personalApply(UPreapplygoup preapplygoup, UWxuser wxuser) {
		 
		 preapplygoupMapper.insertSelective(preapplygoup);
		 
		 UPreapplygoupdetail detail = new UPreapplygoupdetail();
		 detail.setOpenid(preapplygoup.getApplyopenid());
		 detail.setSyncid(preapplygoup.getSyncid());
		 detail.setAreacode(preapplygoup.getAreacode());
		 detail.setType(0);//线上
		 detail.setUname(wxuser.getUname());
		 detail.setUtel(wxuser.getUtel());
//		 detail.setRstatus(0);//未审批
		 detail.setJointime(new Date());
		 detail.setType(1);//线上
		 String applyId = UUID.randomUUID().toString().toUpperCase();
		 detail.setApplyid(applyId);
		 preapplygoupdetailMapper.insertSelective(detail);
		 xxyystepService.insert(preapplygoup.getSyncid(),applyId, WxStepEnum.APPLY.getName(), WxStepEnum.APPLY.getValue(), WxStepEnum.PERSINAL, detail.getAreacode());
		 return 1;
	}

	@Override
	public int teamApply(UPreapplygoup preapplygoup) 
	{
		return preapplygoupMapper.insertSelective(preapplygoup);
	}

	@Override
	public int teamSubmitApply(UPreapplygoup preapplygoup) {
		return preapplygoupMapper.teamSubmitApply(preapplygoup);
	}

	@Override
	public int insertSelective(UPreapplygoup preapplygoup) {
		return preapplygoupMapper.insertSelective(preapplygoup);
	}

	@Override
	@Transactional
	public int agentApply(UPreapplygoup preapplygoup, UPreapplygoupdetail preapplygoupdetail, UWxuser wxuser) 
	{
		preapplygoupMapper.insertSelective(preapplygoup);
		preapplygoupdetailMapper.insertSelective(preapplygoupdetail);
		wxuserMapper.insertSelective(wxuser);
		return 1;
	}

	@Override
	public int agentApply(UPreapplygoup preapplygoup, UPreapplygoupdetail preapplygoupdetail) {
		preapplygoupMapper.insertSelective(preapplygoup);
		preapplygoupdetailMapper.insertSelective(preapplygoupdetail);
		return 1;
	}
	
	
	
}

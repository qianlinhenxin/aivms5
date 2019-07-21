package com.qlhx.service.wechat.realize.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qlhx.service.wechat.realize.dao.UPreapplygoupMapper;
import com.qlhx.service.wechat.realize.dao.UPreapplygoupdetailMapper;
import com.qlhx.service.wechat.realize.dao.UWxuserMapper;
import com.qlhx.service.wechat.realize.model.DownWxUserModel;
import com.qlhx.service.wechat.realize.model.UPreapplygoup;
import com.qlhx.service.wechat.realize.model.UPreapplygoupdetail;
import com.qlhx.service.wechat.realize.model.UWxuser;
import com.qlhx.service.wechat.realize.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;



@Service
public class WeChatServiceImpl implements WeChatService {

	
	@Autowired
    private UWxuserMapper wxuserMapper;
	
	@Autowired
    private UPreapplygoupMapper preapplygoupMapper;
	
	@Autowired
    private UPreapplygoupdetailMapper preapplygoupdetailMapper;
	
	@Override
	public int insertSelective(UWxuser record) {
		return wxuserMapper.insertSelective(record);
	}

	@Override
	public List<UWxuser> listByDownWxUserModel(DownWxUserModel downWxUserModel)
	{
		return wxuserMapper.listByDownWxUserModel(downWxUserModel);
	}

	@Override
	@Transactional
	public int insertWxApply(UPreapplygoup preapplygoup, List<UPreapplygoupdetail> UPreapplygoupdetailList) {
		int i = preapplygoupMapper.insertSelective(preapplygoup);
		for(UPreapplygoupdetail preapplygoupdetail:UPreapplygoupdetailList)
		{
			int j = preapplygoupdetailMapper.insertSelective(preapplygoupdetail);
		}
		return i;
	}


	@Override
	public List<UPreapplygoup> preapplygouplist(DownWxUserModel downWxUserModel) {
		return preapplygoupMapper.preapplygouplist(downWxUserModel);
	}

	
	@Override
	public List<UPreapplygoupdetail> preapplygoupdetaillist(String syncId, String areaCode) {
		Map param = new HashMap();
		param.put("areacode", areaCode);
		param.put("syncid", syncId);
		param.put("isActive", "0");
		return preapplygoupdetailMapper.preapplygoupdetaillist(param);
	}

	@Override
	public List<UPreapplygoupdetail> preapplygoupdetaillist(String areaCode,String tabTime ,String isActive) {
		Map param = new HashMap();
		param.put("areacode", areaCode);
		param.put("tabTime", tabTime);
		param.put("isActive", isActive);
		param.put("notHaveCardNum", "1");
		return preapplygoupdetailMapper.preapplygoupdetaillist(param);
	}

	@Override
	public Integer insertOrUpdateUPreapplygoup(List<UPreapplygoup> preapplygoupList) throws Exception {
		for (UPreapplygoup preapplygoup : preapplygoupList) 
		{
			preapplygoup.setAreacode(preapplygoup.getCompanycode());
			preapplygoup.setCompanycode(preapplygoup.getAreacode().substring(0, preapplygoup.getAreacode().indexOf("_")));
//			if(preapplygoup.getActivitylogo())
			
			if (preapplygoup.getTabtime() == null || preapplygoup.getUploadtime() == null) {
//				swingCardRecord.setNative_createDate(new Date());
//				swingCardRecord.setNative_updateDate(new Date());
				try {
					preapplygoup.setId(null);
					preapplygoupMapper.insertSelective(preapplygoup);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = preapplygoupMapper.updateByPrimaryKeySelective(preapplygoup);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(preapplygoup));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(preapplygoup));
					}

				}

			} else if (preapplygoup.getTabtime() .getTime() != preapplygoup.getUploadtime().getTime()) {
//				swingCardRecord.setNative_updateDate(new Date());
				try {
					preapplygoupMapper.updateByPrimaryKeySelective(preapplygoup);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}

	@Override
	public Integer insertOrUpdateUPreapplygoupdetail(List<UPreapplygoupdetail> preapplygoupdetailList)
			throws Exception 
	{
		for (UPreapplygoupdetail preapplygoup : preapplygoupdetailList) {
			preapplygoup.setAreacode(preapplygoup.getCompanycode());
			preapplygoup.setCompanycode(preapplygoup.getAreacode().substring(0, preapplygoup.getAreacode().indexOf("_")));
			if (preapplygoup.getTabtime() == null || preapplygoup.getUploadtime() == null) {
//				swingCardRecord.setNative_createDate(new Date());
//				swingCardRecord.setNative_updateDate(new Date());
				try {
					preapplygoup.setId(null);
					preapplygoupdetailMapper.insertSelective(preapplygoup);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = preapplygoupdetailMapper.updateByApplyid(preapplygoup);
//						if (i == 0) 
//						{
//							msg = e.getMessage();
//							cause = e.getCause()==null?"":e.getCause().getMessage();
//							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(preapplygoup));
//						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(preapplygoup));
					}

				}

			} else if (preapplygoup.getTabtime() .getTime() != preapplygoup.getUploadtime().getTime()) {
//				swingCardRecord.setNative_updateDate(new Date());
				try {
					preapplygoupdetailMapper.updateByApplyid(preapplygoup);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}


	@Override
	public Integer updateUPreapplygoup(UPreapplygoup preapplygoup) 
	{
		return preapplygoupMapper.updateByPrimaryKeySelective(preapplygoup);
	}

	@Override
	public Integer agreeOrRefusedUPreapplygoup(UPreapplygoup preapplygoup) 
	{
		return preapplygoupMapper.agreeOrRefusedUPreapplygoup(preapplygoup);
	}

	@Override
	public UWxuser selectWxUserByOpenId(String openId, String areaCode) {
		Map param = new HashMap();
		param.put("openid", openId);
		param.put("areacode", areaCode);
		return wxuserMapper.selectWxUserByParam(param);
	}

	@Override
	public UPreapplygoup selectBySyncId(String syncId, String areaCode) {
		Map param = new HashMap();
		param.put("syncid", syncId);
		param.put("areacode", areaCode);
		return preapplygoupMapper.selectBySyncId(param);
	}
	
	@Override
	public UPreapplygoup selectBySyncId(String syncId, String areaCode ,String openId) {
		Map param = new HashMap();
		param.put("syncid", syncId);
		param.put("areacode", areaCode);
		param.put("openid", openId);
		return preapplygoupMapper.selectBySyncId(param);
	}

	@Override
	public List<UWxuser> selectWxUserByApplyDetail(String syncId, String areaCode) {
		Map param = new HashMap();
		param.put("syncid", syncId);
		param.put("areacode", areaCode);
		return wxuserMapper.selectWxUserByApplyDetail(param);
	}

	@Override
	public UWxuser selectWxUserByUid(Integer uid, String areaCode) {
		Map param = new HashMap();
		param.put("uid", uid);
		param.put("areacode", areaCode);
		return wxuserMapper.selectWxUserByUid(param);
	}

	@Override
	public int updateByPrimaryKeySelective(UWxuser record) {
		return wxuserMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public int updateWxuserUidtoNull(UWxuser user) {
		return wxuserMapper.updateWxuserUidtoNull(user);
	}

	@Override
	public Integer updateCardNum(UPreapplygoupdetail preapplygoupdetail) {
		return preapplygoupdetailMapper.updateCardNum(preapplygoupdetail);
	}

	@Override
	public int updateByOpenIdAndArea(UWxuser user) {
		return wxuserMapper.updateByOpenIdAndArea(user);
	}

	@Override
	public UWxuser selectWxUserByTel(String tel, String areaCode) {
		Map param = new HashMap();
		param.put("utel", tel);
		param.put("areacode", areaCode);
		return wxuserMapper.selectWxUserByTel(param);
	}
	

}

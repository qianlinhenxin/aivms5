package com.qlhx.service.wechat.realize.service.impl;

import java.util.List;

import com.qlhx.service.wechat.realize.dao.UUserexpermissionMapper;
import com.qlhx.service.wechat.realize.model.UUserexpermission;
import com.qlhx.service.wechat.realize.service.UserexpermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;


@Service
public class UserexpermissionServiceImpl implements UserexpermissionService {

	@Autowired
    private UUserexpermissionMapper userexpermissionMapper;
	
	@Override
	public Integer insertOrUpdateUserexpermission(List<UUserexpermission> userexpermissionList) throws Exception {
		for (UUserexpermission preapplygoup : userexpermissionList) 
		{
			preapplygoup.setId(null);
			preapplygoup.setAreacode(preapplygoup.getCompanycode());
			preapplygoup.setCompanycode(preapplygoup.getAreacode().substring(0, preapplygoup.getAreacode().indexOf("_")));
			if (preapplygoup.getTabtime() == null || preapplygoup.getUploadtime() == null) {
//				swingCardRecord.setNative_createDate(new Date());
//				swingCardRecord.setNative_updateDate(new Date());
				try {
					preapplygoup.setId(null);
					userexpermissionMapper.insertSelective(preapplygoup);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = userexpermissionMapper.updateByPrimaryKeySelective(preapplygoup);
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
					userexpermissionMapper.updateByPrimaryKeySelective(preapplygoup);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}

	@Override
	public UUserexpermission selectUserexpermission(UUserexpermission userexpermission) {
		return userexpermissionMapper.selectUserexpermission(userexpermission);
	}

	@Override
	public int updateByPrimaryKeySelective(UUserexpermission record) {
		return userexpermissionMapper.updateByPrimaryKeySelective(record);
	}
	
	

	
}

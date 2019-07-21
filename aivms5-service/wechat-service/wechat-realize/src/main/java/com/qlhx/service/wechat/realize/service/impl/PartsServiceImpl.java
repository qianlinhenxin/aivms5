package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.UPartsMapper;
import com.qlhx.service.wechat.realize.model.UParts;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.service.PartsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class PartsServiceImpl extends BaseMybatisDao<UPartsMapper> implements PartsService {

    
    protected final static Logger logger = LoggerFactory
	    .getLogger(TerminalServiceImpl.class);

    
    @Autowired
    UPartsMapper partsMapper;
    

	@Override
	public Integer insertOrUpdateParts(List<UParts> partsList) throws Exception{
    	for (UParts parts : partsList) 
    	{
    		parts.setAreacode(parts.getCompanycode());
    		parts.setCompanycode(parts.getCompanycode().substring(0, parts.getCompanycode().indexOf("_")));
    		if (parts.getTabtime() == null || parts.getUploadtime() == null) {
    			parts.setNativeCreatedate(new Date());
    			parts.setNativeUpdatedate(new Date());
				try {
					partsMapper.insertSelective(parts);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = partsMapper.updateByCompanyCodeAndId(parts);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(parts));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(parts));
					}

				}

			} else if (parts.getTabtime().getTime() != parts.getUploadtime().getTime()) {
				parts.setNativeUpdatedate(new Date());
				partsMapper.updateByCompanyCodeAndId(parts);
			}
		}
    	return 1;
	}


	@Override
	public List<UParts> list(Map param) {
		return partsMapper.list(param);
	}


	
	
	
	

	

}

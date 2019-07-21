package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.UPartsMapper;
import com.qlhx.service.wechat.realize.dao.UPartsStatusMapper;
import com.qlhx.service.wechat.realize.model.UPartsStatus;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.service.PartsStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PartsStatusServiceImpl extends BaseMybatisDao<UPartsMapper> implements PartsStatusService {

    
    protected final static Logger logger = LoggerFactory
	    .getLogger(TerminalServiceImpl.class);

    
    @Autowired
    UPartsStatusMapper partsStatusMapper;
    

	@Override
	public Integer insertOrUpdatePartsStatus(List<UPartsStatus> partsStatusList) throws Exception{
    	for (UPartsStatus partsStatus : partsStatusList) 
    	{
    		partsStatus.setAreacode(partsStatus.getCompanycode());
    		partsStatus.setCompanycode(partsStatus.getCompanycode().substring(0, partsStatus.getCompanycode().indexOf("_")));
    		if (partsStatus.getTabtime() == null || partsStatus.getUploadtime() == null) {
    			partsStatus.setNativeCreatedate(new Date());
    			partsStatus.setNativeUpdatedate(new Date());
				try {
					partsStatusMapper.insertSelective(partsStatus);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = partsStatusMapper.updateByCompanyCodeAndId(partsStatus);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(partsStatus));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(partsStatus));
					}

				}

			} else if (partsStatus.getTabtime().getTime() != partsStatus.getUploadtime().getTime()) {
				partsStatus.setNativeUpdatedate(new Date());
				partsStatusMapper.updateByCompanyCodeAndId(partsStatus);
			}
		}
    	return 1;
	}

	

}

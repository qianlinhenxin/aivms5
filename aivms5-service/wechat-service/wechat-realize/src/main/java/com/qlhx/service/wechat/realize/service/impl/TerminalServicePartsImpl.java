package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.UTerminalPartsMapper;
import com.qlhx.service.wechat.realize.model.UTerminalParts;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.service.TerminalPartsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class TerminalServicePartsImpl extends BaseMybatisDao<UTerminalPartsMapper> implements TerminalPartsService {

    
    protected final static Logger logger = LoggerFactory
	    .getLogger(TerminalServiceImpl.class);

    
    @Autowired
    UTerminalPartsMapper terminalPartsMapper;
    

	@Override
	public Integer insertOrUpdateTerminalParts(List<UTerminalParts> terminalPartsList) throws Exception{
    	for (UTerminalParts terminalPart : terminalPartsList) 
    	{
    		terminalPart.setAreacode(terminalPart.getCompanycode());
    		terminalPart.setCompanycode(terminalPart.getCompanycode().substring(0, terminalPart.getCompanycode().indexOf("_")));
    		if (terminalPart.getTabtime() == null || terminalPart.getUploadtime() == null) {
    			
    			terminalPart.setNativeCreatedate(new Date());
    			terminalPart.setNativeUpdatedate(new Date());
				try {
					terminalPartsMapper.insertSelective(terminalPart);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = terminalPartsMapper.updateByCompanyCodeAndId(terminalPart);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(terminalPart));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(terminalPart));
					}

				}

			} else if (terminalPart.getTabtime().getTime() != terminalPart.getUploadtime().getTime()) {
				terminalPart.setNativeUpdatedate(new Date());
				terminalPartsMapper.updateByCompanyCodeAndId(terminalPart);
			}
		}
    	return 1;
	}

	

}

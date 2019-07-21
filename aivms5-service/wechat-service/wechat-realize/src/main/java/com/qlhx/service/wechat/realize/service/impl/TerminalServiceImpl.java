package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.UPartsMapper;
import com.qlhx.service.wechat.realize.dao.UPartsStatusMapper;
import com.qlhx.service.wechat.realize.dao.UTerminalMapper;
import com.qlhx.service.wechat.realize.dao.UTerminalPartsMapper;
import com.qlhx.service.wechat.realize.model.UTerminal;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.TerminalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class TerminalServiceImpl extends BaseMybatisDao<UTerminalMapper> implements TerminalService {

    
    protected final static Logger logger = LoggerFactory
	    .getLogger(TerminalServiceImpl.class);

    @Autowired
    UTerminalMapper terminalMapper;
    
    @Autowired
    UTerminalPartsMapper terminalPartsMapper;
    
    @Autowired
    UPartsMapper partsMapper;
    
    @Autowired
    UPartsStatusMapper partsStatusMapper;

	@Override
	public int addTerminal(UTerminal terminal) {
		return terminalMapper.insert(terminal);
	}

	@Override
	public List<UTerminal> list(UTerminal terminal) {
		return null;
	}

	@Override
	public int updateTerminal(UTerminal terminal) {
		return terminalMapper.updateByPrimaryKeySelective(terminal);
	}

	@Override
	public Integer insertOrUpdateTerminal(List<UTerminal> terminalList) throws Exception{
    	for (UTerminal terminal : terminalList) 
    	{
    		terminal.setAreacode(terminal.getCompanycode());
    		terminal.setCompanycode(terminal.getAreacode().substring(0, terminal.getAreacode().indexOf("_")));
    		if (terminal.getTabtime() == null || terminal.getUploadtime() == null) {
    			terminal.setNative_createDate(new Date());
    			terminal.setNative_updateDate(new Date());
				try {
					terminalMapper.insertSelective(terminal);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = terminalMapper.updateByCompanyCodeAndId(terminal);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(terminal));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(terminal));
					}

				}
 
			} else if (terminal.getTabtime().getTime() != terminal.getUploadtime().getTime()) {
				terminal.setNative_updateDate(new Date());
				terminalMapper.updateByCompanyCodeAndId(terminal);
			}
		}
    	return 1;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Pagination<UTerminal> findTerminal(ModelMap modelMap, Integer pageNo, Integer pageSize)
	{
		return super.findPage("terminalList", "findCount", modelMap, pageNo, pageSize);
	}

	@Override
	public int findCount(Map param) {
		return terminalMapper.findCount(param);
	}

	@Override
	public List<UTerminal> list(Map param) {
		return terminalMapper.list(param);
	}

	@Override
	public List failureSumByMonth(Map param) {
		return terminalMapper.failureSumByMonth(param);
	}

	@Override
	public List failureSumByYear(Map param) {
		return terminalMapper.failureSumByYear(param);
	}

}

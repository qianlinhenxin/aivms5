package com.qlhx.service.wechat.realize.service.impl;
import com.qlhx.service.wechat.realize.dao.UWxyystepMapper;
import com.qlhx.service.wechat.realize.model.UWxyystep;
import com.qlhx.service.wechat.realize.service.WxyystepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WxyystepServiceImpl implements WxyystepService {
	
	@Autowired
	private UWxyystepMapper wxyystepMapper;

	@Override
	public Integer insertOrUpdateWxyystep(List<UWxyystep> wxyystepList) throws Exception {
		for (UWxyystep wxyystep : wxyystepList) 
		{
			wxyystep.setAreacode(wxyystep.getCompanycode());
			wxyystep.setCompanycode(wxyystep.getAreacode().substring(0, wxyystep.getAreacode().indexOf("_")));
			if(wxyystepMapper.selectIdAndAreaCode(wxyystep) == null)
			{
				wxyystepMapper.insertSelective(wxyystep);
			}
			else
			{
				wxyystepMapper.updateByIdAndAreaCode(wxyystep);
			}
		}
		return 1;
	}

	@Override
	public int insertSelective(UWxyystep record) {
		return wxyystepMapper.insert(record);
	}

	@Override
	public List<UWxyystep> list(String syncId,String applyId,String areaCode) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("syncId", syncId);
		param.put("applyId", applyId);
		param.put("areaCode", areaCode);
		return wxyystepMapper.list(param);
	}

	@Override
	public int insert(String syncId,String applyId,String wxyyremark,Integer wxyystatus,Integer yytype,String areacode){
		UWxyystep wxyystep = new UWxyystep();
		wxyystep.setSyncid(syncId);
		wxyystep.setXxyyitemid(applyId);
		wxyystep.setWxyyremark(wxyyremark);
		wxyystep.setWxyystatus(wxyystatus);
		wxyystep.setYytype(yytype);
		wxyystep.setAreacode(areacode);
		return wxyystepMapper.insertSelective(wxyystep);
	}
	
	

}

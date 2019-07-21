package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.UAreaMapper;
import com.qlhx.service.wechat.realize.model.UArea;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.UAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UAreaServiceImpl extends BaseMybatisDao<UAreaMapper> implements UAreaService {

	@Autowired
    UAreaMapper uAreaMapper;
	
	@Override
	public Integer insertOrUpdateArea(List<UArea> areaList) throws Exception  {

    	for (UArea area : areaList) {
    		if (area.getTabtime() == null || area.getUploadtime() == null) {
    			area.setNative_createDate(new Date());
    			area.setNative_updateDate(new Date());
				try {
					uAreaMapper.insertSelective(area);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = uAreaMapper.updateByCompanyCodeAndId(area);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(area));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(area));
					}

				}

			} else if (area.getTabtime().getTime() != area.getUploadtime().getTime()) {
				area.setNative_updateDate(new Date());
				uAreaMapper.updateByCompanyCodeAndId(area);
			}
		}
    	return 1;
    
	}

	@Override
	public List<UArea> list(UArea area) {
		return uAreaMapper.list(area);
	}

	@Override
	public List<Map> listTree(UArea area) {
		return uAreaMapper.listTree(area);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<UArea> findArea(ModelMap modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage("arealist", "findCount", modelMap, pageNo, pageSize);
	}

	@Override
	public List<UArea> listByCompanyCode(Map<String , Object> param) {
		return uAreaMapper.listByCompanyCode(param);
	}

	@Override
	public List<String> selectAreasByUserId(Long userId) {
		return uAreaMapper.selectAreasByUserId(userId);
	}

	@Override
	public int updateByPrimaryKeySelective(UArea record) {
		return uAreaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public String findMaxAreaCode(Map param) {
		return uAreaMapper.findMaxAreaCode(param);
	}

	@Override
	public int insertSelective(UArea record) {
		return uAreaMapper.insertSelective(record);
	}

	@Override
	public UArea selectByPrimaryKey(Long id) {
		return uAreaMapper.selectByPrimaryKey(id);
	}

	@Override
	public UArea selectByAreaCode(String areaCode) {
		return uAreaMapper.selectByAreaCode(areaCode);
	}
	
	
	
	
}

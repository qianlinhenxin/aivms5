package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.UArea;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface UAreaService {
	
	Integer insertOrUpdateArea(List<UArea> areaList) throws Exception;
	
	List<UArea> list(UArea area);
	
	List<Map> listTree(UArea area); 
	
	List<UArea> listByCompanyCode(Map<String, Object> param);

	Pagination<UArea> findArea(ModelMap modelMap,
                               Integer pageNo, Integer pageSize);

	List<String> selectAreasByUserId(Long userId);
	
	int updateByPrimaryKeySelective(UArea record);
	
	String findMaxAreaCode(Map param);
	
	int insertSelective(UArea record);
	
	UArea selectByPrimaryKey(Long id);
	
	UArea selectByAreaCode(String areaCode);


}

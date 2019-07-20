package com.qlhx.service.base.realize.service;

import com.qlhx.service.base.realize.model.BaseVisitor;
import com.qlhx.service.base.realize.mybatis.page.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VisitorService {
	
	Pagination<BaseVisitor> selectVisitorRecord(
            Map<String, Object> modelMap, Integer pageNo, Integer pageSize);
	
	List<BaseVisitor> findAll();
	
	BaseVisitor selectByPrimaryKey(Integer id);

	BaseVisitor findVisitorByIdNum(@Param("idNum") String idNum) throws Exception;
	BaseVisitor findVisitorByIdPhone(@Param("phone") String phone) throws Exception;
	int updateByPrimaryKey(BaseVisitor record);
	int insertSelective(BaseVisitor record);

}

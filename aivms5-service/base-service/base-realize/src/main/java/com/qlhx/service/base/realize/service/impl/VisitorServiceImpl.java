package com.qlhx.service.base.realize.service.impl;

import com.qlhx.service.base.realize.mapper.BaseVisitorMapper;
import com.qlhx.service.base.realize.model.BaseVisitor;
import com.qlhx.service.base.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.base.realize.mybatis.page.Pagination;
import com.qlhx.service.base.realize.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VisitorServiceImpl extends BaseMybatisDao<BaseVisitorMapper> implements VisitorService {

	@Autowired
	BaseVisitorMapper baseVisitorMapper;
	
	@Override
	public List<BaseVisitor> findAll() {
		return baseVisitorMapper.findAll();
	}

	@Override
	public Pagination<BaseVisitor> selectVisitorRecord(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage(modelMap, pageNo, pageSize);
	}

	@Override
	public BaseVisitor selectByPrimaryKey(Integer id) {
		return baseVisitorMapper.selectByPrimaryKey(id);
	}

	@Override
	public BaseVisitor findVisitorByIdNum(String idNum) throws Exception {
		return baseVisitorMapper.findVisitorByIdNum(idNum);
	}

	@Override
	public BaseVisitor findVisitorByIdPhone(String phone) throws Exception {
		return baseVisitorMapper.findVisitorByIdPhone(phone);
	}

	@Override
	public int updateByPrimaryKey(BaseVisitor record) {
		return baseVisitorMapper.updateByPrimaryKey(record);
	}

	@Override
	public int insertSelective(BaseVisitor record) {
		return baseVisitorMapper.insertSelective(record);
	}

}

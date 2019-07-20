package com.qlhx.service.base.realize.service.impl;

import com.qlhx.service.base.realize.mapper.BaseVisitReasonsMapper;
import com.qlhx.service.base.realize.model.BaseVisitReasons;
import com.qlhx.service.base.realize.service.VisitorReasonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorReasonsServiceImpl implements VisitorReasonsService {
	
	@Autowired
    BaseVisitReasonsMapper baseVisitReasonsMapper;

	@Override
	public List<BaseVisitReasons> list() {
		return baseVisitReasonsMapper.list();
	}
	
	

}

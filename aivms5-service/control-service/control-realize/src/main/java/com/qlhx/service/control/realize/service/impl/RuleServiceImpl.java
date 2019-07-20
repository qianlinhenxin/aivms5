package com.qlhx.service.control.realize.service.impl;

import java.util.List;

import com.qlhx.service.control.realize.dao.RuleMapper;
import com.qlhx.service.control.realize.model.Rule;
import com.qlhx.service.control.realize.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RuleServiceImpl implements RuleService {

	@Autowired
    RuleMapper ruleMapper;
	
	@Override
	public List<Rule> list() {
		return ruleMapper.list();
	}

	@Override
	public int insertSelective(Rule record) {
		return ruleMapper.insertSelective(record);
	}

}

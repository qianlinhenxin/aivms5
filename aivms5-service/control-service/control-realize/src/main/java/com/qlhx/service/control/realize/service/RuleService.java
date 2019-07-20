package com.qlhx.service.control.realize.service;

import java.util.List;

import com.qlhx.service.control.realize.model.Rule;

public interface RuleService {
	
    List<Rule> list();
	
	int insertSelective(Rule record);

}

package com.qlhx.service.control.realize.dao;


import com.qlhx.service.control.realize.model.Rule;

import java.util.List;

public interface RuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rule record);

    int insertSelective(Rule record);

    Rule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rule record);

    int updateByPrimaryKey(Rule record);
    
    List<Rule> list();
}
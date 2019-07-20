package com.qlhx.service.control.realize.dao;

import com.qlhx.service.control.realize.model.PersonGroup;
import com.qlhx.service.control.realize.model.PersonGroupImpl;

import java.util.List;


public interface PersonGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PersonGroup record);

    int insertSelective(PersonGroup record);

    PersonGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PersonGroup record);

    int updateByPrimaryKey(PersonGroup record);
    
    List<PersonGroupImpl> list();
    
    PersonGroupImpl selectByIdentifier(String identifier);
}
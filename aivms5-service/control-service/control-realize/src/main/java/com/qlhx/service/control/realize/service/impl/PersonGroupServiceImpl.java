package com.qlhx.service.control.realize.service.impl;

import java.util.Date;
import java.util.List;

import com.qlhx.service.control.realize.dao.PersonGroupMapper;
import com.qlhx.service.control.realize.model.PersonGroup;
import com.qlhx.service.control.realize.model.PersonGroupImpl;
import com.qlhx.service.control.realize.service.PersonGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonGroupServiceImpl implements PersonGroupService {

	@Autowired
    PersonGroupMapper personGroupMapper;
	
	@Override
	public List<PersonGroupImpl> list() {
		return personGroupMapper.list();
	}

	@Override
	public int insertSelective(PersonGroup record) {
		record.setCreatedate(new Date());
		return personGroupMapper.insertSelective(record);
	}

	@Override
	public PersonGroupImpl selectByIdentifier(String identifier) {
		return personGroupMapper.selectByIdentifier(identifier);
	}

}

package com.qlhx.service.control.realize.service.impl;


import com.qlhx.service.control.realize.dao.KeyMapper;
import com.qlhx.service.control.realize.model.Key;
import com.qlhx.service.control.realize.service.KeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class KeyServiceImpl implements KeyService {

	@Autowired
    KeyMapper keyMapper;
	
	@Override
	public List<Key> list() {
		return keyMapper.list();
	}

	@Override
	public int insertSelective(Key record) {
		return keyMapper.insertSelective(record);
	}

	@Override
	public Key selectByPrimaryKey(Integer id) {
		return keyMapper.selectByPrimaryKey(id);
	}
	
	

}

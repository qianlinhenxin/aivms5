package com.qlhx.service.control.realize.service;

import java.util.List;

import com.qlhx.service.control.realize.model.Key;

public interface KeyService {
	
    List<Key> list();
	
	int insertSelective(Key record);
	
	Key selectByPrimaryKey(Integer id);

}

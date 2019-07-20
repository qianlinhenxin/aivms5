package com.qlhx.service.control.realize.service;

import com.qlhx.service.control.realize.model.Path;

import java.util.List;


public interface PathService {
	
    List<Path> list();
	
	int insertSelective(Path record);

	Path selectByPrimaryKey(Integer id);
}

package com.qlhx.service.control.realize.service.impl;

import java.util.List;

import com.qlhx.service.control.realize.dao.PathMapper;
import com.qlhx.service.control.realize.model.Path;
import com.qlhx.service.control.realize.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PathServiceImpl implements PathService {

	@Autowired
    PathMapper pathMapper;
	
	@Override
	public List<Path> list() {
		return pathMapper.list();
	}

	@Override
	public int insertSelective(Path record) {
		return pathMapper.insertSelective(record);
	}

	@Override
	public Path selectByPrimaryKey(Integer id) {
		return pathMapper.selectByPrimaryKey(id);
	}
	
	

}

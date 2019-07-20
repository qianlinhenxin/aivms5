package com.qlhx.service.control.realize.service.impl;

import java.util.List;

import com.qlhx.service.control.realize.dao.PathDetailMapper;
import com.qlhx.service.control.realize.model.PathDetail;
import com.qlhx.service.control.realize.service.PathDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PathDetailServiceImpl implements PathDetailService {

	@Autowired
    PathDetailMapper pathDetailMapper;
	
	@Override
	public List<PathDetail> list(Integer pathId) {
		return pathDetailMapper.list(pathId);
	}

	@Override
	public int insertSelective(PathDetail record) {
		return pathDetailMapper.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return pathDetailMapper.deleteByPrimaryKey(id);
	}

}

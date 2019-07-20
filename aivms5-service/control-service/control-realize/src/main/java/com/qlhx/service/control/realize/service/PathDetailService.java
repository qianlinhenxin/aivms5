package com.qlhx.service.control.realize.service;


import com.qlhx.service.control.realize.model.PathDetail;

import java.util.List;

public interface PathDetailService {
	
    List<PathDetail> list(Integer pathId);
	
	int insertSelective(PathDetail record);
	
	int deleteByPrimaryKey(Integer id);

}

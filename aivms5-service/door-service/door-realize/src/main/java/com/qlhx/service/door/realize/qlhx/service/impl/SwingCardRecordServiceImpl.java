package com.qlhx.service.door.realize.qlhx.service.impl;

import com.qlhx.service.door.realize.qlhx.mapper.USwingCardRecordMapper;
import com.qlhx.service.door.realize.qlhx.model.USwingCardRecord;
import com.qlhx.service.door.realize.qlhx.service.SwingCardRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SwingCardRecordServiceImpl implements SwingCardRecordService
{
	@Autowired
    USwingCardRecordMapper swingCardRecordMapper;

	@Override
	public int insertSelective(USwingCardRecord record) {
		return swingCardRecordMapper.insertSelective(record);
	}

	@Override
	public List<USwingCardRecord> list(String sn) {
		Map param =new HashMap();
		param.put("sn", sn);
		return swingCardRecordMapper.list(param);
	}
	
	

}

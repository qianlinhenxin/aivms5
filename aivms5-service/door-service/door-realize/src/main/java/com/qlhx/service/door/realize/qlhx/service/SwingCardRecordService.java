package com.qlhx.service.door.realize.qlhx.service;


import com.qlhx.service.door.realize.qlhx.model.USwingCardRecord;

import java.util.List;

/**
 * Created by rongcan on 2017/7/11.
 */
public interface SwingCardRecordService {

    int insertSelective(USwingCardRecord record);
	
	List<USwingCardRecord> list(String sn);
}

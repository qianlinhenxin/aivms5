package com.qlhx.service.door.realize.qlhx.service;


import com.qlhx.service.door.realize.qlhx.model.UEntranceGuard;

import java.util.List;

/**
 * Created by rongcan on 2017/7/11.
 */
public interface EntranceGuardService {

    List<UEntranceGuard> list();
	
	UEntranceGuard selectBySn(String sn);
	
	int insertOrUpdate(String sn, String ip, int runStatus);
	
	int insertSelective(UEntranceGuard record);
	
	int updateByPrimaryKeySelective(UEntranceGuard record);
	
	int deleteByPrimaryKey(Integer id);
}

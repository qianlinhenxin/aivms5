package com.qlhx.service.base.realize.service;

import com.qlhx.base.model.Result;
import com.qlhx.service.base.realize.model.BaseAccessRecord;
import com.qlhx.service.base.realize.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

public interface AccessRecordService {
	
	Pagination<BaseAccessRecord> selectAccessRecord(Map<String, Object> modelMap, Integer pageNo, Integer pageSize);
	

	Result<Map<String, Object>> SaveAccessRecord(BaseAccessRecord record) throws Exception;
	
	Result<Map<String, Object>> addEntourage(BaseAccessRecord record) throws Exception;
	
    Integer findAccessRecordPageCount(BaseAccessRecord record) throws Exception;
    
    List<BaseAccessRecord> findAccessRecord(BaseAccessRecord record) throws Exception;
    
    BaseAccessRecord findAccessRecordByCardNum(String cardNum);

	int updateByPrimaryKeySelective(BaseAccessRecord record);

	int insertSelective(BaseAccessRecord record);
}

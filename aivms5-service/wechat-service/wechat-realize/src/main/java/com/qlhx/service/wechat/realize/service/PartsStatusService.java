package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.UPartsStatus;

import java.util.List;

public interface PartsStatusService
{
    
    Integer insertOrUpdatePartsStatus(List<UPartsStatus> partsStatusList) throws Exception;
    
}

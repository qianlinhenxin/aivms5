package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.UParts;

import java.util.List;
import java.util.Map;

public interface PartsService
{
	List<UParts> list(Map parma);
    
    Integer insertOrUpdateParts(List<UParts> PartsList) throws Exception;
    
}

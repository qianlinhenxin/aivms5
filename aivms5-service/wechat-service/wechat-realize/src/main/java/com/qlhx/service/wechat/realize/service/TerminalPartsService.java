package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.UTerminalParts;

import java.util.List;

public interface TerminalPartsService
{
    
    Integer insertOrUpdateTerminalParts(List<UTerminalParts> terminalPartsList) throws Exception;
    
}

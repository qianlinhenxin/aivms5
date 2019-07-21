package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.UTerminal;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface TerminalService {
	
    int addTerminal(UTerminal terminal);
    
    List<UTerminal> list(UTerminal terminal);
    
    int updateTerminal(UTerminal terminal);
    
    Integer insertOrUpdateTerminal(List<UTerminal> terminalList) throws Exception;
    
    Pagination<UTerminal> findTerminal(ModelMap modelMap,
                                       Integer pageNo, Integer pageSize);

    int findCount(Map param);
    
    List<UTerminal> list(Map param);
    
    List failureSumByMonth(Map param);
    
    List failureSumByYear(Map param);
}

package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.UTerminal;

import java.util.List;
import java.util.Map;

public interface UTerminalMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UTerminal record);

    int insertSelective(UTerminal record);

    UTerminal selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UTerminal record);

    int updateByPrimaryKey(UTerminal record);
    
    int updateByCompanyCodeAndId(UTerminal area);
    
    int findCount(Map param);
    
    List<UTerminal> list(Map param);
    
    List failureSumByMonth(Map param);
    
    List failureSumByYear(Map param);
}

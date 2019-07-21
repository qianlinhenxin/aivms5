package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.SwingCardRecord;
import com.qlhx.service.wechat.realize.model.SwingCardRecordBo;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/7/11.
 */
public interface SwingCardRecordService {

    Pagination<SwingCardRecordBo> findPage(ModelMap modelMap, Integer pageNo, int pageSize);

    Map<String,Object> AddSwingCardRecord(SwingCardRecord swingCardRecord) throws Exception;
    
    Integer insertOrUpdateSwingCardRecord(String companyCode, String areaCode, String terminalCode, List<SwingCardRecord> swingCardRecord) throws Exception;
    
    Integer insertOrUpdateSwingCardRecord(List<SwingCardRecord> swingCardRecordList) throws Exception;
}

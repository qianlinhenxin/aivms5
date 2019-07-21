package com.qlhx.service.wechat.realize.service;
import com.qlhx.service.wechat.realize.model.ComboResult;
import com.qlhx.service.wechat.realize.model.VisitReasons;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/6/29.
 */
public interface VisitReasonsService {
    Pagination<VisitReasons> findVisitReasons(ModelMap modelMap, Integer pageNo, int pageSize);

    Integer addVisitReasons(VisitReasons visitReasons);

    Integer editVisitReasons(VisitReasons visitReasons);

    Integer delVisitReasons(Integer id);

    Map<String,Object> delResonsByids(String ids);

    List<ComboResult> GetVisitorReasonComboResult();
    
    Integer insertOrUpdateVisitReasons(List<VisitReasons> visitReasonsrList) throws Exception;
}

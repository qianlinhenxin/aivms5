package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.Res;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/6/29.
 */
public interface ResService {
    Pagination<Res> findRes(ModelMap modelMap, Integer pageNo, int pageSize);

    Integer addRes(Res res);

    Integer editRes(Res res);

    Integer delRes(Integer id);

    Map<String,Object> delResByids(String ids);
    
    Integer insertOrUpdateRes(List<Res> resList) throws Exception;
}

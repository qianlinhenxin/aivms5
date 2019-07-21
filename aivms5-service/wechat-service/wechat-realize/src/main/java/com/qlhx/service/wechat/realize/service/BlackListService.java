package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.BlackListBo;
import com.qlhx.service.wechat.realize.model.Blacklist;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/6/28.
 */
public interface BlackListService {
	
	Blacklist selectByPrimaryKey(Integer id);

    Pagination<BlackListBo> selectBlacklistBo(Map<String, Object> modelMap, Integer pageNo, Integer pageSize);

    List<BlackListBo> selectBlacklist(Map<String, Object> modelMap);

    Map<String,Object> deleteById(Integer id);

    int deleteBlackByVid(Integer vid, String areaCode);

    Pagination<BlackListBo> selectVisitorAndBlacklist(ModelMap modelMap, Integer pageNo, int pageSize);

    Map<String,Object> addBlackLists(String visitorIds);

    Boolean selectBlackByIdNum(String idNum);

    Integer insertOrUpdateBlacklist(String companyCode, String areaCode, String terminalCode, List<Blacklist> BlacklistList) throws Exception;
    
    Integer insertOrUpdateBlacklist(List<Blacklist> BlacklistList) throws Exception;
    
    BlackListBo selectBlackVisitorByPrimaryKey(Integer id);
    
    BlackListBo selectBlackVisitorByParam(Map param);
    
    int findCount(Map param);
    
    Boolean selectBlackByTel(String tel);
}

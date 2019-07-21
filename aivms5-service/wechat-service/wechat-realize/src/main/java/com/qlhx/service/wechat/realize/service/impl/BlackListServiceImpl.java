package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.BlacklistMapper;
import com.qlhx.service.wechat.realize.model.BlackListBo;
import com.qlhx.service.wechat.realize.model.Blacklist;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.BlackListService;
import com.qlhx.service.wechat.realize.utils.LoggerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/6/28.
 */
@Service
public class BlackListServiceImpl extends BaseMybatisDao<BlacklistMapper> implements BlackListService {

    @Autowired
    private BlacklistMapper blacklistMapper;
    
    

    @Override
	public Blacklist selectByPrimaryKey(Integer id) {
		return blacklistMapper.selectByPrimaryKey(id);
	}
    
    

	@Override
	public int deleteBlackByVid(Integer vid, String areaCode) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("vid", vid);
		param.put("areaCode", areaCode);
		return blacklistMapper.deleteBlackByVid(param);
	}



	@Override
    public Pagination<BlackListBo> selectBlacklistBo(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
        return super.findPage("selectBlacklistBo", "selectBlacklistBoCount", modelMap, pageNo,
                pageSize);
    }

    @Override
    public List<BlackListBo> selectBlacklist(Map<String, Object> modelMap) {
        return blacklistMapper.selectBlacklistBo(modelMap);
    }

    @Override
    public Map<String, Object> deleteById(Integer id) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            int count = blacklistMapper.deleteByPrimaryKey(id);
            resultMap.put("status", 200);
            resultMap.put("successCount", count);
            resultMap.put("message", "删除成功!");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "删除失败！");
            LoggerUtils.fmtError(getClass(), e, "删除黑名单出错。blacklist Id[%s]", id.toString());
        }
        return resultMap;
    }

    @Override
    public Pagination<BlackListBo> selectVisitorAndBlacklist(ModelMap modelMap, Integer pageNo, int pageSize) {
        return super.findPage("findVisitorAndBlack", "findVisitorAndBlackCount", modelMap, pageNo,
                pageSize);
    }

    @Override
    public Map<String, Object> addBlackLists(String visitorIds) {
        Map<String, Object> resultMap = new HashMap<>();
        try {

            int count = 0;
            String[] idArray = new String[]{};
            if (StringUtils.contains(visitorIds, ",")) {
                idArray = visitorIds.split(",");
            } else {
                idArray = new String[]{visitorIds};
            }

            for (String id : idArray) {
                Blacklist blacklist = new Blacklist();
                blacklist.setVid(Integer.parseInt(id));
                if (blacklistMapper.selectBlacklistByVid(id) <= 0)
                    count += blacklistMapper.insertSelective(blacklist);
            }
            resultMap.put("status", 200);
            resultMap.put("successCount", count);
            resultMap.put("message", "添加成功!");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", "添加失败！");
            LoggerUtils.fmtError(getClass(), e, "添加黑名单出错。visitorIds Id[%s]", visitorIds);
        }
        return resultMap;
    }

    @Override
    public Boolean selectBlackByIdNum(String idNum) {
        return blacklistMapper.selectBlackByIdNum(idNum) <= 0;
    }
    
    
    
    @Override
	public Boolean selectBlackByTel(String tel) {
    	int i = blacklistMapper.selectBlackByTel(tel);
    	if(i > 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
	}

	@Override
    @Transactional
    public Integer insertOrUpdateBlacklist(String companyCode , String areaCode,String terminalCode,List<Blacklist> BlacklistList) throws Exception
    {

    	for (Blacklist blacklist : BlacklistList) {
    		blacklist.setCompanyCode(companyCode);
    		blacklist.setAreaCode(areaCode);
    		blacklist.setTerminalCode(terminalCode);
    		blacklist.setPrimaryId(blacklist.getId().longValue());
    		Blacklist tempBlacklist = blacklistMapper.selectBlacklistByPama(blacklist);
    		if(tempBlacklist != null)
    		{  
    			blacklist.setId(tempBlacklist.getId().intValue());
    			blacklist.setNative_updateDate(new Date());
    			blacklistMapper.updateByPrimaryKeySelective(blacklist);
    		}
    		else
    		{
    			blacklist.setId(null);
    			blacklist.setNative_createDate(new Date());
    			blacklist.setNative_updateDate(new Date());
    			blacklistMapper.insertSelective(blacklist);
    		}
		}
    	return 1;
    
    
    }
    @Override
    public Integer insertOrUpdateBlacklist(List<Blacklist> BlacklistList) throws Exception
    {

    	for (Blacklist blacklist : BlacklistList) {
    		blacklist.setAreaCode(blacklist.getCompanyCode());
    		blacklist.setCompanyCode(blacklist.getAreaCode().substring(0, blacklist.getAreaCode().indexOf("_")));
    		if(blacklist.getTabTime() == null || blacklist.getUploadTime() == null)
    		{  
    			blacklist.setNative_createDate(new Date());
    			blacklist.setNative_updateDate(new Date());
    			try {
    				blacklistMapper.insertSelective(blacklist);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = blacklistMapper.updateByCompanyCodeAndId(blacklist);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(blacklist));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(blacklist));
					}

				}
    			
    		}
    		else if (blacklist.getTabTime().getTime() != blacklist.getUploadTime().getTime())
    		{
    			blacklist.setNative_updateDate(new Date());
    			try {
					blacklistMapper.updateByCompanyCodeAndId(blacklist);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = e.getMessage();
					String cause = e.getCause() == null ? "" : e.getCause().getMessage();
					throw new Exception("这条记录 更新失败原因:"+msg+";"+cause+"  数据:" + JSON.toJSONString(blacklist));
				}
    		}
		}
    	return 1;
    }

	@Override
	public BlackListBo selectBlackVisitorByPrimaryKey(Integer id) {
		return blacklistMapper.selectBlackVisitorByPrimaryKey(id);
	}

	@Override
	public int findCount(Map param) {
		return blacklistMapper.selectBlacklistBoCount(param);
	}

	@Override
	public BlackListBo selectBlackVisitorByParam(Map param) {
		return blacklistMapper.selectBlackVisitorByParam(param);
	}
    
	
    
}

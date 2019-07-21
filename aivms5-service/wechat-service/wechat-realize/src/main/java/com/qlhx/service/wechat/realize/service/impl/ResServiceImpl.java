package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.ResMapper;
import com.qlhx.service.wechat.realize.model.Res;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.ResService;
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
 * Created by rongcan on 2017/6/29.
 */
@Service
public class ResServiceImpl extends BaseMybatisDao<ResMapper> implements ResService {

    @Autowired
    private ResMapper resMapper;

    @Override
    public Pagination<Res> findRes(ModelMap modelMap, Integer pageNo, int pageSize) {
        return super.findPage(modelMap, pageNo, pageSize);
    }

    @Override
    public Integer addRes(Res res) {
        return resMapper.insert(res);
    }

    @Override
    public Integer editRes(Res res) {
        return resMapper.updateByPrimaryKeySelective(res);
    }

    @Override
    public Integer delRes(Integer id) {
        return resMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> delResByids(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String resultMsg = "删除成功。";
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            for (String idx : idArray) {
                Long id = new Long(idx);
                count += this.delRes(id.intValue());
            }

            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除带入物品出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }
    
    @Override
    @Transactional
    public Integer insertOrUpdateRes(List<Res> resList) throws Exception
	{
		for (Res res : resList) {
			res.setAreaCode(res.getCompanyCode());
			res.setCompanyCode(res.getAreaCode().substring(0, res.getAreaCode().indexOf("_")));
			if (res.getTabTime() == null || res.getUploadTime() == null) {
				res.setNative_createDate(new Date());
				res.setNative_updateDate(new Date());
				try {
					resMapper.insertSelective(res);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = resMapper.updateByPrimaryKeySelective(res);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(res));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(res));
					}

				}

			} else if (res.getTabTime().getTime() != res.getUploadTime().getTime()) {
				res.setNative_updateDate(new Date());
				try {
					resMapper.updateByPrimaryKeySelective(res);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
    
}

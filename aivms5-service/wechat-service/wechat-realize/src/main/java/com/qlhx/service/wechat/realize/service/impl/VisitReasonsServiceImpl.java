package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.VisitReasonsMapper;
import com.qlhx.service.wechat.realize.model.ComboResult;
import com.qlhx.service.wechat.realize.model.VisitReasons;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.VisitReasonsService;
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
public class VisitReasonsServiceImpl extends BaseMybatisDao<VisitReasonsMapper> implements VisitReasonsService {

    @Autowired
    private VisitReasonsMapper visitReasonsMapper;

    @Override
    public Pagination<VisitReasons> findVisitReasons(ModelMap modelMap, Integer pageNo, int pageSize) {
        return super.findPage(modelMap, pageNo, pageSize);
    }

    @Override
    public Integer addVisitReasons(VisitReasons visitReasons) {

        return visitReasonsMapper.insert(visitReasons);
    }

    @Override
    public Integer editVisitReasons(VisitReasons visitReasons) {
        return visitReasonsMapper.updateByPrimaryKey(visitReasons);
    }

    @Override
    public Integer delVisitReasons(Integer id) {
        return visitReasonsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> delResonsByids(String ids) {
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
                count += this.delVisitReasons(id.intValue());
            }

            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    @Override
    public List<ComboResult> GetVisitorReasonComboResult() {
        return visitReasonsMapper.GetVisitorReasonComboResult();
    }
    
    @Override
	@Transactional
	public Integer insertOrUpdateVisitReasons(List<VisitReasons> visitReasonsList) throws Exception {
		for (VisitReasons visitReasons : visitReasonsList) {
			visitReasons.setAreaCode(visitReasons.getCompanyCode());
			visitReasons.setCompanyCode(visitReasons.getAreaCode().substring(0, visitReasons.getAreaCode().indexOf("_")));
			if (visitReasons.getTabTime() == null || visitReasons.getUploadTime() == null) {
				visitReasons.setNative_createDate(new Date());
				visitReasons.setNative_updateDate(new Date());
				try {
					visitReasonsMapper.insertSelective(visitReasons);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = visitReasonsMapper.updateByPrimaryKeySelective(visitReasons);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(visitReasons));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(visitReasons));
					}

				}

			} else if (visitReasons.getTabTime().getTime() != visitReasons.getUploadTime().getTime()) {
				visitReasons.setNative_updateDate(new Date());
				try {
					visitReasonsMapper.updateByPrimaryKeySelective(visitReasons);
				} catch (Exception e) {
					;
				}
			}
		}
		return 1;
	}
}

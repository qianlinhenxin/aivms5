package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.SwingCardRecordMapper;
import com.qlhx.service.wechat.realize.model.SwingCardRecord;
import com.qlhx.service.wechat.realize.model.SwingCardRecordBo;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.SwingCardRecordService;
import com.qlhx.service.wechat.realize.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/7/11.
 */
@Service
public class SwingCardRecordServiceImpl extends BaseMybatisDao<SwingCardRecordMapper> implements SwingCardRecordService {

    @Autowired
    SwingCardRecordMapper swingCardRecordMapper;

    @Override
    public Pagination<SwingCardRecordBo> findPage(ModelMap modelMap, Integer pageNo, int pageSize) {
        return super.findPage(modelMap, pageNo, pageSize);
    }

    @Override
    public Map<String, Object> AddSwingCardRecord(SwingCardRecord swingCardRecord) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            int count = swingCardRecordMapper.insertSelective(swingCardRecord);
            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("message", "修改成功");

        } catch (Exception ex) {
            LoggerUtils.fmtError(getClass(), ex, "归档出错");
            resultMap.put("status", 500);
            resultMap.put("message", "修改考勤时间错误，请刷新后再试！");
        }
        return resultMap;
    }
    
    @Override
    @Transactional
	public Integer insertOrUpdateSwingCardRecord(String companyCode , String areaCode,String terminalCode,List<SwingCardRecord> swingCardRecordList) throws Exception
    {
    	for (SwingCardRecord swingCardRecord : swingCardRecordList) {
    		swingCardRecord.setCompanyCode(companyCode);
    		swingCardRecord.setAreaCode(areaCode);
    		swingCardRecord.setTerminalCode(terminalCode);
    		swingCardRecord.setPrimaryId(swingCardRecord.getId().longValue());
    		SwingCardRecord tempswingCardRecord = swingCardRecordMapper.selectSwingCardRecordByPama(swingCardRecord);
    		if(tempswingCardRecord != null)
    		{  
    			swingCardRecord.setId(tempswingCardRecord.getId().intValue());
    			swingCardRecordMapper.updateByPrimaryKeySelective(swingCardRecord);
    		}
    		else
    		{
    			swingCardRecord.setId(null);
    			swingCardRecordMapper.insertSelective(swingCardRecord);
    		}
		}
    	return 1;
    }

	@Override
	public Integer insertOrUpdateSwingCardRecord(List<SwingCardRecord> swingCardRecordList) throws Exception {
		for (SwingCardRecord swingCardRecord : swingCardRecordList) {
			swingCardRecord.setAreaCode(swingCardRecord.getCompanyCode());
			swingCardRecord.setCompanyCode(swingCardRecord.getAreaCode().substring(0, swingCardRecord.getAreaCode().indexOf("_")));
			if (swingCardRecord.getTabTime() == null || swingCardRecord.getUploadTime() == null) {
				swingCardRecord.setNative_createDate(new Date());
				swingCardRecord.setNative_updateDate(new Date());
				try {
					swingCardRecordMapper.insertSelective(swingCardRecord);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = swingCardRecordMapper.updateByPrimaryKeySelective(swingCardRecord);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(swingCardRecord));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(swingCardRecord));
					}

				}

			} else if (swingCardRecord.getTabTime().getTime() != swingCardRecord.getUploadTime().getTime()) {
				swingCardRecord.setNative_updateDate(new Date());
				try {
					swingCardRecordMapper.updateByPrimaryKeySelective(swingCardRecord);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
    
    
}

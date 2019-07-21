package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.AccessRecordMapper;
import com.qlhx.service.wechat.realize.model.AccessRecord;
import com.qlhx.service.wechat.realize.model.VisitorRecord;
import com.qlhx.service.wechat.realize.model.VistorSmsInfo;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.service.AccessRecordService;
import com.qlhx.service.wechat.realize.utils.LoggerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/6/30.
 */
@Service
public class AccessRecordServiceImpl extends BaseMybatisDao<AccessRecordMapper> implements AccessRecordService {

    @Autowired
    private AccessRecordMapper accessRecordMapper;

    @Override
    public Integer addAccessRecord(AccessRecord accessRecord) throws Exception {
        if (null == accessRecord.getId()) {
            return accessRecordMapper.insertSelective(accessRecord);
        } else {
            return accessRecordMapper.updateByPrimaryKeySelective(accessRecord);
        }
    }

    @Override
    public Integer editAccessRecord(AccessRecord accessRecord) {
        return null;
    }

    @Override
    public Integer delAccessRecord(Integer id) throws Exception {
        return accessRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Map<String, Object> delAccessRecordByids(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String resultMsg = "删除成功!";
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            for (String idx : idArray) {
                Long id = new Long(idx);
                count += this.delAccessRecord(id.intValue());
            }

            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除预约出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    @Override
    public AccessRecord showVisitorInfo(Integer id) throws Exception {
        return accessRecordMapper.findAccessRecordByid(id);
    }
    
    @Override
    @Transactional
    public Integer insertOrUpdateAccessRecord(String companyCode , String areaCode,String terminalCode,List<AccessRecord> accessRecordList) throws Exception
    {

    	for (AccessRecord AccessRecord : accessRecordList) {
    		AccessRecord.setCompanyCode(companyCode);
    		AccessRecord.setAreaCode(areaCode);
    		AccessRecord.setTerminalCode(terminalCode);
    		AccessRecord.setPrimaryId(AccessRecord.getId().longValue());
    		AccessRecord tempAccessRecord = accessRecordMapper.selectAccessRecordByPama(AccessRecord);
    		if(tempAccessRecord != null)
    		{  
    			AccessRecord.setId(tempAccessRecord.getId().intValue());
    			AccessRecord.setNative_updateDate(new Date());
    			accessRecordMapper.updateByPrimaryKeySelective(AccessRecord);
    		}
    		else
    		{
    			AccessRecord.setId(null);
    			AccessRecord.setNative_createDate(new Date());
    			AccessRecord.setNative_updateDate(new Date());
    			accessRecordMapper.insertSelective(AccessRecord);
    		}
		}
    	return 1;
    }
    
    @Override
    @Transactional
    public Integer insertOrUpdateAccessRecord(List<AccessRecord> accessRecordList) throws Exception
    {

    	for (AccessRecord AccessRecord : accessRecordList) {
    		AccessRecord.setAreaCode(AccessRecord.getCompanyCode());
    		AccessRecord.setCompanyCode(AccessRecord.getAreaCode().substring(0, AccessRecord.getAreaCode().indexOf("_")));
    		if(AccessRecord.getTabTime() == null || AccessRecord.getUploadTime() == null)
    		{  
    			AccessRecord.setNative_createDate(new Date());
    			AccessRecord.setNative_updateDate(new Date());
    			try {
					accessRecordMapper.insertSelective(AccessRecord);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = accessRecordMapper.updateByCompanyCodeAndId(AccessRecord);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(AccessRecord));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(AccessRecord));
					}

				}
    		}
    		else if (AccessRecord.getTabTime().getTime() != AccessRecord.getUploadTime().getTime())
    		{
    			AccessRecord.setNative_updateDate(new Date());
    			try {
					accessRecordMapper.updateByCompanyCodeAndId(AccessRecord);
				} catch (Exception e) {
					;
				}
    		}
		}
    	return 1;
    }

	@Override
	public Integer updateByPrimaryKeySelective(AccessRecord accessRecord) {
		return accessRecordMapper.updateByPrimaryKeySelective(accessRecord);
	}

	@Override
	public List visitorReasonCount(Map param) {
		return accessRecordMapper.visitorReasonCount(param);
	}
    
	
	@Override
	public List newCountReportByMonth(Map map) {
		return accessRecordMapper.newCountReportByMonth(map);
	}

	@Override
	public List newCountReportByYear(Map param) {
		return accessRecordMapper.newCountReportByYear(param);
	}

	@Override
	public List visitedDurationByMonth(Map param) {
		return accessRecordMapper.visitedDurationByMonth(param);
	}

	@Override
	public List visitedDurationByYear(Map param) {
		return accessRecordMapper.visitedDurationByYear(param);
	}

	@Override
	public List policeByMonth(Map param) {
		return accessRecordMapper.policeByMonth(param);
	}

	@Override
	public List policeByYear(Map param) {
		return accessRecordMapper.policeByYear(param);
	}

	@Override
	public List<VisitorRecord> selectByLastCreateTime(String lastCreateTime) {
		return accessRecordMapper.selectByLastCreateTime(lastCreateTime);
	}

	@Override
	public List<VisitorRecord> selectUploadToB() {
		return accessRecordMapper.selectUploadToB();
	}

	@Override
	public VistorSmsInfo vistorSmsInfo(Integer id) {
		return accessRecordMapper.vistorSmsInfo(id);
			
	}
	
    
}

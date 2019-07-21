package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.AccessRecord;
import com.qlhx.service.wechat.realize.model.VisitorRecord;
import com.qlhx.service.wechat.realize.model.VistorSmsInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by rongcan on 2017/6/30.
 */
public interface AccessRecordService {
    Integer addAccessRecord(AccessRecord accessRecord) throws Exception;

    Integer editAccessRecord(AccessRecord accessRecord);

    Integer delAccessRecord(Integer id) throws Exception;

    Map<String, Object> delAccessRecordByids(String ids);

    AccessRecord showVisitorInfo(Integer id) throws Exception;
    
    Integer insertOrUpdateAccessRecord(String companyCode, String areaCode, String terminalCode, List<AccessRecord> accessRecordList) throws Exception;
    
    Integer insertOrUpdateAccessRecord(List<AccessRecord> accessRecordList) throws Exception;
    
    Integer updateByPrimaryKeySelective(AccessRecord accessRecord);
    
    List visitorReasonCount(Map param);
    
    List newCountReportByMonth(Map param);
    
    List newCountReportByYear(Map param);
    
    List visitedDurationByMonth(Map param);
    
    List visitedDurationByYear(Map param);
    
    List policeByMonth(Map param);
    
    List policeByYear(Map param);
    
    List<VisitorRecord> selectByLastCreateTime(String lastCreateTime);
    
    List<VisitorRecord> selectUploadToB();
    
    VistorSmsInfo vistorSmsInfo(Integer id);
}

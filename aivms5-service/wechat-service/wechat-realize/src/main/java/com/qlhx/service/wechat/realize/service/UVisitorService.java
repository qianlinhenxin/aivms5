package com.qlhx.service.wechat.realize.service;

import com.qlhx.service.wechat.realize.model.*;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

/**
 * UVisitor 表数据服务层接口
 */
public interface UVisitorService {

    Pagination<VisitorRecordBo> selectVisitorRecord(Map<String, Object> modelMap, Integer pageNo, Integer pageSize);

    List<VisitorRecordBo> selectVisitorByModelMap(Map<String, Object> resultMap);

    Pagination<Visitor> selectVisitorList(ModelMap modelMap, Integer pageNo, int pageSize);

    List<Visitor> selectVisitors(ModelMap modelMap);

    Visitor findVisitorByIdNum(String idnum) throws Exception;

    Pagination<VisitorRecordBo> selectVisitorOrderList(ModelMap modelMap, Integer pageNo, int pageSize);

    Boolean findOrderVisitorByIdNumCount(String idNum);


    Integer addOrEditVisitor(Visitor visitor) throws Exception;

    VisitorRecordBo selectVisitorRecordByRId(Integer id);

    Map<String, Object> delVisitorOrders(String ids);

    Integer delVisitorOrder(Integer id);

    List<VisitorStatistics> findVisitorStatistics(ModelMap modelMap);

    List<VisitorRecordBo> selectPhotoByVids(Map<String, Object> resultModel);
    
    Integer insertOrUpdateVistors(String companyCode, String areaCode, String terminalCode, List<Visitor> vistorList) throws Exception;
    
    Integer insertOrUpdateVistors(List<Visitor> vistorList) throws Exception;

    Integer insertOrUpdateVistorsCar(List<VisitorCar> vistorCarList) throws Exception;
    
    Integer insertOrUpdateDraginRes(List<DraginRes> draginResList) throws Exception;
    
    Integer insertOrUpdateTakeoutRes(List<TakeoutRes> takeoutResList) throws Exception;
    
    int findCount(Map parama);
    
    int findRecordCount(Map parama);
    
    /**
     * 查询出需要上传公安网的访客
     * @return
     */
    List<Map> selectVisitorToUploadPolice(Map param);
    
    int updateByPrimaryKeySelective(Visitor visitor);
    
    List<Integer> selectVidsByName(String name);
    
}
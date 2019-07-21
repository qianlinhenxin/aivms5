package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.Department;
import com.qlhx.service.wechat.realize.model.UUser;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * <p>
 * Title:信息同步服务接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author 余佳建
 * @date 2017年9月26日 上午8:40:22
 */
public interface SyncInfoService {

    /**
     * 同步部门信息
     * 
     * @param depList
     *            部门信息列表
     * @return
     * @throws Exception
     */
    public Map<String, String> syncDepInfo(List<Department> depList)
	    throws Exception;

    /**
     * 同步员工信息
     * 
     * @param userList
     *            员工信息列表
     * @return
     * @throws Exception
     */
    public Map<String, String> syncEmployeeInfo(List<UUser> userList)
	    throws Exception;

}

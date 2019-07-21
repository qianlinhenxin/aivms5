/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author 余佳建
 * @date 2017年9月26日 上午8:45:06
 */
package com.qlhx.service.wechat.realize.service.impl;


import com.qlhx.service.wechat.realize.dao.DepartmentMapper;
import com.qlhx.service.wechat.realize.dao.UUserMapper;
import com.qlhx.service.wechat.realize.model.Department;
import com.qlhx.service.wechat.realize.model.UUser;
import com.qlhx.service.wechat.realize.service.SyncInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * Title:同步信息具体实现
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author 余佳建
 * @date 2017年9月26日 上午8:45:06
 */
@Service
public class SyncInfoServiceImpl implements SyncInfoService {

    @Autowired
    DepartmentMapper depMapper;

    @Autowired
    UUserMapper userMapper;

    @Override
    public Map<String, String> syncDepInfo(List<Department> depList)
	    throws Exception {
	// TODO Auto-generated method stub
	Map<String, String> resultlist = new HashMap<String, String>();
	for (Department dep : depList) {
	    // 查询部门信息
	    Department d = depMapper.selectDepBySyncId(dep.getSyncId(),
		    dep.getCompanyNum());
	    if (d != null) {// 存在
		if ("3".equals(dep.getSta())) {// 删除
		    resultlist.put(dep.getSyncId().toString(),
			    "" + depMapper.deleteByPrimaryKey(d.getId()));
		} else {// 更新
		    dep.setId(d.getId());
		    resultlist.put(dep.getSyncId().toString(),
			    "" + depMapper.updateByPrimaryKeySelective(dep));
		}
	    } else {// 不存在
		if (!"3".equals(dep.getSta())) {// 新增
		    resultlist.put(dep.getSyncId().toString(),
			    "" + depMapper.insertSelective(dep));
		} else {
		    resultlist.put(dep.getSyncId().toString(), "" + 1);
		}
	    }

	}
	return resultlist;
    }

    @Override
    public Map<String, String> syncEmployeeInfo(List<UUser> userList)
	    throws Exception {
	return null;
    }
}

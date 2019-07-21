package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.service.wechat.realize.dao.DepartmentMapper;
import com.qlhx.service.wechat.realize.model.ComboResult;
import com.qlhx.service.wechat.realize.model.Department;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by rongcan on 2017/7/4.
 */

@Service
public class DepartmentServiceImpl extends BaseMybatisDao<DepartmentMapper>
	implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<ComboResult> selectDepartmentComboResult() {
	return departmentMapper.selectDepartmentComboResult();
    }

    @Override
    public List<Department> findAllDept() {
	return departmentMapper.findAllDept();
    }

    @Override
    public int editDept(Department department) {
	return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    public int addDept(Department department) throws Exception {
	return departmentMapper.insertSelective(department);
    }

    @Override
    public int delDept(Integer id) {
	return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Department selectDeptByName(String trim) {
	return departmentMapper.selectDeptByName(trim);
    }

    /**
     * 新加或更新部门
     */
	@Override
	public Integer insertOrUpdateDepartment(List<Department> departmentList) throws Exception {
		for (Department department : departmentList) {
			department.setAreaCode(department.getCompanyNum());
			department.setCompanyCode(department.getAreaCode().substring(0, department.getAreaCode().indexOf("_")));
    		if (department.getTabTime() == null || department.getUploadTime() == null) {
    			department.setNative_createDate(new Date());
    			department.setNative_updateDate(new Date());
				try {
					departmentMapper.insertSelective(department);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = departmentMapper.updateByCompanyCodeAndId(department);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(department));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(department));
					}

				}

			} else if (department.getTabTime().getTime() != department.getUploadTime().getTime()) {
				department.setNative_updateDate(new Date());
//				department.setCompanyCode(department.getCompanyNum());
				departmentMapper.updateByCompanyCodeAndId(department);
			}
		}
    	return 1;
	}
    
}

package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.ComboResult;
import com.qlhx.service.wechat.realize.model.Department;

import java.util.List;

/**
 * Created by rongcan on 2017/7/4.
 */
public interface DepartmentService {
    List<ComboResult> selectDepartmentComboResult();

    List<Department> findAllDept();

    int editDept(Department department);

    int addDept(Department department) throws Exception;

    int delDept(Integer id);

    Department selectDeptByName(String trim);
    
    Integer insertOrUpdateDepartment(List<Department> Department) throws Exception;
}

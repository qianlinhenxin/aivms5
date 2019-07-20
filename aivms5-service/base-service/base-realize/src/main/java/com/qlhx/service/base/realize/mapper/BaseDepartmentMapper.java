package com.qlhx.service.base.realize.mapper;

import com.qlhx.service.base.realize.model.BaseDepartment;
import com.qlhx.service.base.realize.model.UUserDep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaseDepartmentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseDepartment record);

    int insertSelective(BaseDepartment record);

    UUserDep selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseDepartment record);

    int updateByPrimaryKey(BaseDepartment record);


    List<Integer> findSubdeptidsByPrideptIds(List<Integer> depIds) throws Exception;

    List<UUserDep> findDepartment(String companyNum) throws Exception;

    /**
     *
     * <p>
     * Title:根据拼音查询所有部门信息列表
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    List<UUserDep> findDepartmentByPY(@Param("companyNum") String companyNum, @Param("pinyin") String pinyin)
            throws Exception;



}
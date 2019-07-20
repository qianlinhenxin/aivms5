package com.qlhx.service.base.realize.mapper;

import com.qlhx.service.base.realize.model.BaseMember;
import com.qlhx.service.base.realize.model.UUserDep;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseMember record);

    int insertSelective(BaseMember record);

    BaseMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseMember record);

    int updateByPrimaryKey(BaseMember record);

    List<BaseMember> findUserByPhoneOrName(@Param("param") String param,
                                           @Param("pageindex") Integer pageindex,
                                           @Param("pagesize") Integer pagesize) throws Exception;

    Integer findUserCountByPhoneOrName(@Param("param") String param)
            throws Exception;



    BaseMember login(@Param("email") String email, @Param("pswd") String pswd);


    BaseMember selectByCardNum(String cardNum);


    Integer findEmployeesCountById(@Param("depId") Integer deptid) throws Exception;

    List<BaseMember> findEmployeesById(@Param("depId") Integer depId, @Param("pageindex") Integer pageindex,
                                       @Param("pagesize") Integer pagesize) throws Exception;
    /**
     * 根据部门ID列表，查询员工总数
     * @param deptIds
     * @return
     */
    Integer findEmployeesCountByDeptIds(List<Integer> deptIds);


    Integer findEmployeesCountByIdnum(@Param("idnum") String idnum) throws Exception;

    /**
     * 根据部门ID列表，查询员工列表
     * @param map 部门列表  pageindex 页  pagesize 数量
     * @return
     */
    List<BaseMember> findEmployeesByDeptIdS(Map map);

    /**
     * <p>
     * Title:根据访客身份证号码最近访问人员列表
     * </p>
     * <p>
     * Description:根据访客身份证号码最近访问人员列表
     * </p>
     * @param idnum
     * @param number
     * @return 员工列表
     * @throws Exception
     */
    List<BaseMember> findEmployeesByIdnum(String idnum, Integer pageindex, Integer number) throws Exception;
    List<UUserDep> findDepartment(String companyNum) throws Exception;
    Integer findUserCountByDeptId(@Param("id") Integer id) throws Exception;

    /**
     * <p>
     * Title:根据身份证号码查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param param
     * @return
     * @throws Exception
     */
    BaseMember findUserByIdno(String param) throws Exception;



    BaseMember selectUserInfoByPrimaryKey(Long id) throws Exception;


}
package com.qlhx.service.base.realize.service;

import com.qlhx.service.base.realize.model.*;
import com.qlhx.service.base.realize.mybatis.page.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MemberService {
	
	Pagination<BaseMember> selectMemberRecord(Map<String, Object> modelMap, Integer pageNo, Integer pageSize);
	
	int insertSelective(BaseMember record);
	
	BaseMember selectByCardNum(String cardNum);
	
	List<BaseMember> findUserByPhoneOrName(String param, Integer pageindex,
                                           Integer pagesize) throws Exception;
	
	 Integer findUserCountByPhoneOrName(String param) throws Exception;


    BaseMember login(String email, String pswd);

     Integer findEmployeesCountById(Integer deptid) throws Exception;


    List<BaseMember> findEmployeesById(Integer depId, Integer pageindex,
                                       Integer pagesize) throws Exception;


    /*
     * 查询部门下包含子部门人员总数
     * @param depId
     * */
    Integer findSubDeptEmployeesCountById(Integer depId) throws Exception;


    Integer findEmployeesCountByIdnum(String idnum) throws Exception;
    /*
     * 查询部门下包含子部门人员列表
     * @param depId 部门ID
     * @param pageindex 页序号
     * @param pagesize 员条数
     * */
    List<BaseMember> findSubDeptEmployeesById(Integer depId, Integer pageindex, Integer pagesize) throws Exception;

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


    /*
     * 根据ID查部门
     *
     */
    UUserDep findDepartmentByID(Integer deptId);


    /**
     * <p>
     * Title:根据身份证号码查询访客信息
     * </p>
     * <p>
     * Description:根据身份证号码查询访客信息
     * </p>
     *
     * @param idNum
     *            身份证号码
     * @return
     * @throws Exception
     */
     BaseVisitor findVisitorByIdNum(String idNum) throws Exception;

    /**
     * <p>
     * Title:根据访客id查询访客信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param vID
     * @return
     * @throws Exception
     */
     BaseVisitor findVisitorById(Integer vID) throws Exception;


    /**
     * <p>
     * Title:根据访客凭证二维码或一维码信息查询访客信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param rCode
     * @return
     * @throws Exception
     */
    BaseVisitor findVisitorByRCode(@Param("rCode") String rCode) throws Exception;


    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:根据ic卡号查询当前正在进行访问的访客信息
     * </p>
     *
     * @param icNum
     * @return
     * @throws Exception
     */
    BaseVisitor findVisitorByIcNum(String icNum) throws Exception;

    /**
     * 根据ic卡号查询访客最后一条访问记录没有注销的
     * @param icNum
     * @return
     */
    BaseAccessRecord findVisitorAccessByICNum(String icNum);


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


    /**
     * <p>
     * Title:检查门禁卡是否被占用
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cardNum
     * @return
     * @throws Exception
     */
     List<BaseAccessRecord> checkCardIsActive(String cardNum)
            throws Exception;


    /**
     * <p>
     * Title:卡片注销
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param record
     *            访客记录信息
     * @return
     * @throws Exception
     */
     Integer recoverCard(BaseAccessRecord record) throws Exception;


    /**
     * <p>
     * Title:获取指定日期访客出/入数量
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param date
     * @return
     * @throws Exception
     */
    Map<String, Integer> findTodayIntoAndOutNum(@Param("date") String date)
            throws Exception;


    /**
     * <p>
     * Title:根据访客身份证号查询该访客是否在黑名单中
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param idNum
     * @return
     * @throws Exception
     */
     List<BaseVisitor> findBlackListByIdNum(String idNum) throws Exception;


    /**
     * 在公共黑名单中获取指定证件号码的信息（有效未删除的）
     *
     * @param idNum
     * @return
     */
    List<BaseBlacklistShare> findBlackShare(String idNum);




    /**
     * <p>
     * Title:根据来访人员身份号码判断该访客是否有预约
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
     List<BaseVisitor> isAppointment(String idNum) throws Exception;


    /**
     * <p>
     * Title:根据访客id查询该访客预约端信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
     List<BaseAccessRecord> findAccessRecordByVid(Integer vID)
            throws Exception;







    /**
     * <p>
     * Title:根据员工手机号码查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param phone
     * @return
     * @throws Exception
     */
     BaseMember findUserByPhoneNum(String phone) throws Exception;



    /**
     * <p>
     * Title:查询预约信息是否存在
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param r
     * @return 存在，返回预约信息，不存在返回null
     * @throws Exception
     */
     List<BaseAccessRecord> isAccessRecord(BaseAccessRecord r) throws Exception;



    /**
     * <p>
     * Title:更新访客记录信息（新记录添加，已有记录修改）
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param record
     * @return int 更新状态，更新记录条数
     * @throws Exception
     */
    Integer updateAccessRecord(BaseAccessRecord record) throws Exception;


    /**
     * <p>
     * Title:根据来访人员身份号码判断该访客是否注销访问
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    List<BaseVisitor> isLogOff(String idNum) throws Exception;


    /**
     * <p>
     * Title:更新访客信息（新访客添加，已有访客更新）
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param visitor
     * @return int 更新状态，更新记录条数
     * @throws Exception
     */
     Integer updateVisitorInfo(BaseVisitor visitor) throws Exception;



    /**
     * <p>
     * Title:根据来访人证件号码查询上次来访信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param idNum
     * @return
     * @throws Exception
     */
     BaseAccessRecord findLastAccessRecordByIdNum(String idNum)
            throws Exception;

    /**
     * <p>
     * Title:根据ID查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param id
     * @return
     * @throws Exception
     */
     BaseMember findUserInfoById(long id) throws Exception;


    /**
     *
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:获取带入/带出物品选项列表
     * </p>
     *
     * @return
     * @throws Exception
     */
    List<BaseRes> findDraginAndTakeoutRes() throws Exception;

}

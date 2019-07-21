package com.qlhx.service.wechat.realize.service;


import com.qlhx.base.model.Result;
import com.qlhx.service.wechat.realize.model.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FKTerminalService {

    /**
     * <p>
     * Title:获取指定部门员工列表
     * </p>
     * <p>
     * Description:根据部门ID查询该部门可被访问的员工
     * </p>
     *
     * @param pageindex
     *            部门ID @return 员工列表
     * @throws Exception
     */
    public List<UUser> findEmployeesById(Integer deptid, Integer pageindex,
                                         Integer pagesize) throws Exception;

    public Integer findEmployeesCountById(Integer deptid) throws Exception;

    /**
     * <p>
     * Title:部门列表
     * </p>
     * <p>
     * Description:获取所有部门信息列表
     * </p>
     *
     * @return
     * @throws Exception
     */
    public List<UUserDep> findDepartment(String companyNum) throws Exception;

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
    public Visitor findVisitorByIdNum(String idNum) throws Exception;

    /**
     * <p>
     * Title: 访客登记必要参数检查
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param result
     * @param record
     * @return boolean false:检查不通过 true:检查通过
     * @throws Exception
     */
    public boolean checkccessRecordParam(Result result, AccessRecord record)
	    throws Exception;

    /**
     * <p>
     * Title:访客身份信息检查
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param result
     * @param visitor
     * @return boolean false:检查不通过 true:检查通过
     * @throws Exception
     */
    public boolean checkIDInfo(Result result, Visitor visitor) throws Exception;

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
    public Integer updateVisitorInfo(Visitor visitor) throws Exception;

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
    public Integer updateAccessRecord(AccessRecord record) throws Exception;

    /**
     * <p>
     * Title:更新访客车辆信息（新记录添加，已有记录修改）
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param record
     * @return int 更新状态，更新记录条数
     * @throws Exception
     */
    public Integer updateVisitorCar(AccessRecord record) throws Exception;

    /**
     * <p>
     * Title:更新访客带入物品信息（新记录添加，已有记录修改）
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param record
     * @return int 更新状态，更新记录条数
     * @throws Exception
     */
    public Integer updateDraginRes(AccessRecord record) throws Exception;

    /**
     * <p>
     * Title:根据访客记录id查询访客记录信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param arId
     * @return
     * @throws Exception
     */
    public AccessRecord isAccessRecordByArId(Integer arId) throws Exception;

    /**
     * <p>
     * Title:根据访客记录删除访客记录信息或删除随访信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param arId
     * @return
     * @throws Exception
     */
    public Integer delAccessRecord(Integer arId) throws Exception;

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
    public List<AccessRecord> checkCardIsActive(String cardNum)
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
    public Integer recoverCard(AccessRecord record) throws Exception;

    /**
     * <p>
     * Title:获取指定日期访客出/入数量
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param date
     *            string yyyy-MM-dd
     * @return intoNum:进入未离开人数<br />
     *         outNum:已离开人数
     * @throws Exception
     */
    public Map<String, Integer> findTodayIntoAndOutNum(String date)
	    throws Exception;

    /**
     * <p>
     * Title:根据访客记录id查询访客记录
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param id
     * @return
     * @throws Exception
     */
    public AccessRecord findAccesRecordById(Integer id) throws Exception;

    /**
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:根据访客凭证二维码或一维码信息查询访客信息
     * </p>
     *
     * @param rCode
     * @return
     * @throws Exception
     */
    public Visitor findVisitorByRCode(String rCode) throws Exception;

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
    public Visitor findVisitorByIcNum(String icNum) throws Exception;

    /**
     * <p>
     * Title:获取方可来访事由列表信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    public List<VisitReasons> findReasons() throws Exception;

    /**
     * <p>
     * Title:获取访客带入/带出物品列表
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    public List<Res> findDraginAndTakeoutRes() throws Exception;

    /**
     * <p>
     * Title:根据来访人id获取来访人未注销的来访记录
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param vId
     * @return
     * @throws Exception
     */
    public List<AccessRecord> findRecordingByVisitorId(Integer vId)
	    throws Exception;

    /**
     * <p>
     * Title:更新带出物品（新纪录添加，已有记录修改）
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param tr
     * @return
     * @throws Exception
     */
    public Integer updateTakeOutRes(TakeoutRes tr) throws Exception;

    /**
     * <p>
     * Title:查询访客来访记录
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param record
     * @return
     * @throws Exception
     */
    public List<AccessRecord> findAccessRecord(AccessRecord record)
	    throws Exception;

    /**
     * <p>
     * Title:访客记录查询记录总条数
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    public Integer findAccessRecordPageCount(AccessRecord record)
	    throws Exception;

    /**
     * <p>
     * Title:将指定的来访人员加入黑名单
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    public Integer addBlackListByVisitorId(Blacklist black) throws Exception;

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
    public Visitor findVisitorById(Integer vID) throws Exception;

    /**
     * <p>
     * Title:根据访客id查询黑名单信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param vID
     * @return
     * @throws Exception
     */
    public Blacklist findBlackListByVisitorId(Integer vID) throws Exception;

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
    public List<Visitor> findBlackListByIdNum(String idNum) throws Exception;

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
    public List<Visitor> isLogOff(String idNum) throws Exception;

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
    public List<Visitor> isAppointment(String idNum) throws Exception;

    /**
     * <p>
     * Title:根据卡号查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cardNum
     * @return
     * @throws Exception
     */
    public UUser findUserInfoByCardNum(String cardNum) throws Exception;


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
    public UUser findUserInfoById(long id) throws Exception;

    /**
     * <p>
     * Title:添加员工、访客刷卡记录
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param scr
     * @return
     * @throws Exception
     */
    public Integer addSwingCardRecord(SwingCardRecord scr) throws Exception;

    /**
     * <p>
     * Title:根据门禁编号和员工（被访人）id查询通道权限
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param egNum
     * @param uId
     * @return
     * @throws Exception
     */
    public EntranceGuard findOpenDoorPermission(String egNum, Long uId)
	    throws Exception;

    /**
     * <p>
     * Title:根据卡号查询访客正在进行的访问
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cardNum
     * @return
     * @throws Exception
     */
    public AccessRecord findAccessRecordByCardNum(String cardNum)
	    throws Exception;

    /**
     * <p>
     * Title:获取指定条数的未上传到公安网的使用身份证登记的来访记录信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    public List<AccessRecord> findNotUploadAccessRecord(Integer num)
	    throws Exception;

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
    public List<AccessRecord> findAccessRecordByVid(Integer vID)
	    throws Exception;

    /**
     * <p>
     * Title:根据员工ID获取员工部门信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param uID
     * @return
     * @throws Exception
     */
    public UUserDep findDepartmentByUid(Integer uID) throws Exception;

    /**
     * <p>
     * Title:根据访问记录id获取访客车辆信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    public VisitorCar findVcarByarID(Integer arID) throws Exception;

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
    public AccessRecord findLastAccessRecordByIdNum(String idNum)
	    throws Exception;

    /**
     * <p>
     * Title:根据手机号码后4为或姓名拼音首字母查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param param
     * @param pageindex
     * @param pagesize
     * @return
     * @throws Exception
     */
    public List<UUser> findUserByPhoneOrName(String param, Integer pageindex,
                                             Integer pagesize) throws Exception;

    public Integer findUserCountByPhoneOrName(String param) throws Exception;

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
    public UUser findUserByPhoneNum(String phone) throws Exception;

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
    public List<AccessRecord> isAccessRecord(AccessRecord r) throws Exception;

    /**
     * <p>
     * Title:根据来访记录id列表更改记录状态为已上传公安网
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param listID
     * @return
     * @throws Exception
     */
    public Integer updateAccessRecordByListID(List<UploadStatusInfo> listID)
	    throws Exception;

    /**
     * <p>
     * Title:跟新刷卡记录状态
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param id
     *            记录ID
     * @param flag
     *            标记（1：开门成功 2：开门失败）
     * @return
     * @throws Exception
     */
    public Integer updateOpenDoorStatus(Integer id, Integer flag)
	    throws Exception;

    List<EntranceGuard> findAllUseEntranceGuard() throws Exception;

    AccessRecord findNowAccessRecordByVid(@Param("vID") Integer vID) throws Exception;
    
    
}

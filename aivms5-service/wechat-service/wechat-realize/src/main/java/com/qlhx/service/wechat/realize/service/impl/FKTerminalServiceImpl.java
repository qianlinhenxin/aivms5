package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qlhx.base.model.Result;
import com.qlhx.base.util.IConfig;
import com.qlhx.service.wechat.realize.dao.*;
import com.qlhx.service.wechat.realize.model.*;
import com.qlhx.service.wechat.realize.service.FKTerminalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.DateTime;
import tools.Img;
import tools.MD5;
import tools.PinYinUtil;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Title:访客终端接口服务
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author alaska
 * @date 2017年6月26日 下午1:13:21
 */
@Service
public class FKTerminalServiceImpl implements FKTerminalService {

    // String rmspath = System.getProperty("rms");
    // String imgProPath = System.getProperty("rms").replace(IConfig.get("pro"),
    // IConfig.get("imgpro"));
    protected final static Logger logger = LoggerFactory
	    .getLogger(FKTerminalServiceImpl.class);

    @Autowired
    UUserMapper userMapper;

    @Autowired
    UUserDepMapper depMapper;

    @Autowired
    VisitorMapper visitorMapper;

    @Autowired
    AccessRecordMapper recordMapper;

    @Autowired
    VisitorCarMapper vCarMapper;

    @Autowired
    DraginResMapper drMapper;

    @Autowired
    AccessRecordMapper acMapper;

    @Autowired
    VisitReasonsMapper vrMapper;

    @Autowired
    ResMapper resMapper;

    @Autowired
    TakeoutResMapper trMapper;

    @Autowired
    BlacklistMapper blackMapper;

    @Autowired
    SwingCardRecordMapper scrMapper;

    @Autowired
    EntranceGuardMapper egMapper;

    /**
     * 根据部门id获取指定部门可访问员工列表
     *
     * @param id
     * @param pageindex
     * @param depId
     *            部门
     */
    @Override
    public List<UUser> findEmployeesById(Integer depId, Integer pageindex,
                                         Integer pagesize) throws Exception {
	// TODO Auto-generated method stub
	return userMapper.findEmployeesById(depId, pageindex, pagesize);
    }

    @Override
    public Integer findEmployeesCountById(Integer deptid) throws Exception {
	return userMapper.findEmployeesCountById(deptid);
    }

    /**
     * 获取所有部门信息列表
     */
    @Override
    public List<UUserDep> findDepartment(String companyNum) throws Exception {
	// TODO Auto-generated method stub
	return depMapper.findDepartment(companyNum);
    }

    /**
     * 根据访客身份证号码查询访客信息
     */
    @Override
    public Visitor findVisitorByIdNum(String idNum) throws Exception {
	// TODO Auto-generated method stub
	return visitorMapper.findVisitorByIdNum(idNum);
    }

    /**
     * 访客登记必要参数检查<br />
     *
     * @return boolean false:检查不通过 true:检查通过
     */
    @Override
    public boolean checkccessRecordParam(Result result, AccessRecord record)
	    throws Exception {
	// TODO Auto-generated method stub
	if (record == null || record.getUser().getId() == null
		|| record.getUser().getId() == 0) {
	    result.setCode(1001);
	    result.setMsg("被访人不存在！");
	    return false;
	} else if (record.getStarttime() == null) {
	    result.setCode(1001);
	    result.setMsg("访问开始时间不存在！");
	    return false;
	} else if (record.getEndtime() == null) {
	    result.setCode(1001);
	    result.setMsg("访问结束时间不存在！");
	    return false;
	} else if (record.getReasons() == null) {
	    result.setCode(1001);
	    result.setMsg("来访事由不存在！");
	    return false;
	}
	// else if (record.getSitephoto() == null) {
	// result.setCode(1001);
	// result.setMsg("访客现场照片不存在！");
	// return false;
	// }

	return true;
    }

    /**
     * 更新访客信息（新访客添加，已有访客更新）<br />
     */
    @Override
    public Integer updateVisitorInfo(Visitor visitor) throws Exception {
	// TODO Auto-generated method stub
	logger.debug("更新访客信息（新访客添加，已有访客更新）");
	// 保存图片信息(证件照)
	if (visitor.getPhoto() != null) {
	    String picPaht = System.getProperty("rms").replace(
		    IConfig.get("pro"), IConfig.get("imgpro"))
		    + IConfig.get("idpic")
		    + File.separator
		    + visitor.getIdnum() + ".png";
	    logger.debug("证件照路径：" + picPaht);
	    if (Img.base642img(visitor.getPhoto(), picPaht)) {
		visitor.setPhoto(File.separator + IConfig.get("imgpro")
			+ File.separator + IConfig.get("idpic")
			+ File.separator + visitor.getIdnum() + ".png");
	    }

	}

	// 根据访客身份证号查询访客是否存在
	Visitor vc = visitorMapper.findVisitorByIdNum(visitor.getIdnum());
	if (vc != null) {
	    // 修改访客信息
	    visitor.setId(vc.getId());
	    logger.debug(JSONObject.toJSONString(visitor));
	    // 添加姓名拼音首字母
	    visitor.setPinYin(PinYinUtil.getFirstSpell(visitor.getName()));
	    return visitorMapper.updateByPrimaryKeySelective(visitor);
	} else {
	    // 添加访客信息
	    logger.debug(JSONObject.toJSONString(visitor));
	    // 添加姓名拼音首字母
	    visitor.setPinYin(PinYinUtil.getFirstSpell(visitor.getName()));
	    return visitorMapper.insertSelective(visitor);
	}

    }

    /**
     * 更新访客记录信息（新记录添加，已有记录更新）
     */
    @Override
    public Integer updateAccessRecord(AccessRecord record) throws Exception {
	// TODO Auto-generated method stub
	logger.debug("更新访客记录信息（新记录添加，已有记录更新）");

	// 保存图片信息(访客现场照)
	if (record.getSitephoto() != null) {
	    String picPaht = System.getProperty("rms").replace(
		    IConfig.get("pro"), IConfig.get("imgpro"))
		    + IConfig.get("sitepic")
		    + File.separator
		    + record.getVisitor().getIdnum()
		    + "_"
		    + DateTime.getTime("HHmmss") + ".png";
	    logger.debug("现场照路径：" + picPaht);
	    if (Img.base642img(record.getSitephoto(), picPaht)) {
		record.setSitephoto(File.separator + IConfig.get("imgpro")
			+ File.separator + IConfig.get("sitepic")
			+ File.separator + record.getVisitor().getIdnum() + "_"
			+ DateTime.getTime("HHmmss") + ".png");
	    }

	}

	// 根据访客记录id查询访客记录信息
	if (record.getId() != null
		&& recordMapper.selectByPrimaryKey(record.getId()) != null) {
	    // record.setStatus(0);
	    logger.debug(JSONObject.toJSONString(record));
	    return recordMapper.updateByPrimaryKeySelective(record);
	} else {
	    record.setCreatetime(DateTime.getNow());
	    // record.setStatus(0);
	    if (recordMapper.insertSelective(record) > 0) {
		record.setRcode(MD5.getMD52Num(
			record.getId() + record.getCreatetime(), 8));

		// 添加虚拟卡号
		if (record.getCardnum() == null
			|| "".equals(record.getCardnum())) {
		    record.setCardnum(IConfig.get("vcnp")
			    + MD5.getMD52Num(
				    record.getId() + record.getCreatetime(), 5));
		}
		logger.debug(JSONObject.toJSONString(record));
		return recordMapper.updateByPrimaryKeySelective(record);
	    }
	    return 0;
	}

    }

    /**
     * 更新访客车辆信息（新记录添加，已有记录修改）
     */
    @Override
    public Integer updateVisitorCar(AccessRecord record) throws Exception {
	// TODO Auto-generated method stub
	record.getVCar().setArid(record.getId());
	// 根据访客记录id查询本次访客车辆信息
	VisitorCar vCar = vCarMapper.findCarByArId(record.getId());
	if (vCar != null) {
	    record.getVCar().setId(vCar.getId());
	    return vCarMapper.updateByPrimaryKeySelective(record.getVCar());
	} else {
	    return vCarMapper.insertSelective(record.getVCar());
	}
    }

    /**
     * 更新访客带入物品信息（新记录添加，已有记录修改）
     */
    @Override
    public Integer updateDraginRes(AccessRecord record) throws Exception {
	// TODO Auto-generated method stub

	// 根基访客记录ID查询方可带入物品
	drMapper.deleteByArId(record.getId());
	// 添加访客带入物品
	int flag = 0;
	for (DraginRes dr : record.getDrList()) {
	    dr.setArId(record.getId());
	    flag += drMapper.insertSelective(dr);
	}
	return flag;
    }

    /**
     * 访客身份信息检查
     */
    @Override
    public boolean checkIDInfo(Result result, Visitor visitor) throws Exception {
	// TODO Auto-generated method stub

	if (visitor == null) {
	    result.setCode(1001);
	    result.setMsg("访客信息不存在！");
	    return false;
	} else if (visitor.getIdnum() == null) {
	    result.setCode(1001);
	    result.setMsg("访客身份证号不存在！");
	    return false;
	} else if (visitor.getName() == null) {
	    result.setCode(1001);
	    result.setMsg("访客姓名不存在！");
	    return false;
	} else if (visitor.getSex() == null) {
	    result.setCode(1001);
	    result.setMsg("访客性别不存在！");
	    return false;
	}

	return true;
    }

    /**
     * 根据访客记录id查询访客记录信息
     */
    @Override
    public AccessRecord isAccessRecordByArId(Integer arId) throws Exception {
	// TODO Auto-generated method stub
	return acMapper.selectByPrimaryKey(arId);
    }

    @Override
    public Integer delAccessRecord(Integer arId) throws Exception {
	// TODO Auto-generated method stub
	return acMapper.deleteByPrimaryKey(arId);
    }

    @Override
    public List<AccessRecord> checkCardIsActive(String cardNum)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.selectByCardNum(cardNum);
    }

    @Override
    public Integer recoverCard(AccessRecord record) throws Exception {
	// TODO Auto-generated method stub
	return acMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Map<String, Integer> findTodayIntoAndOutNum(String date)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findTodayIntoAndOutNum(date);
    }

    @Override
    public AccessRecord findAccesRecordById(Integer id) throws Exception {
	// TODO Auto-generated method stub
	return acMapper.selectByPrimaryKey(id);
    }

    @Override
    public Visitor findVisitorByRCode(String rCode) throws Exception {
	// TODO Auto-generated method stub
	return visitorMapper.findVisitorByRCode(rCode);
    }

    @Override
    public List<VisitReasons> findReasons() throws Exception {
	// TODO Auto-generated method stub
	return vrMapper.findReasons();
    }

    @Override
    public List<Res> findDraginAndTakeoutRes() throws Exception {
	// TODO Auto-generated method stub
	return resMapper.findDraginAndTakeoutRes();
    }

    @Override
    public Visitor findVisitorByIcNum(String icNum) throws Exception {
	// TODO Auto-generated method stub
	return visitorMapper.findVisitorByIcNum(icNum);
    }

    @Override
    public List<AccessRecord> findRecordingByVisitorId(Integer vId)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findRecordingByVisitorId(vId);
    }

    @Override
    public Integer updateTakeOutRes(TakeoutRes tr) throws Exception {
	// TODO Auto-generated method stub
	if (tr == null) {
	    return 0;
	} else {
	    if (tr.getId() == null) {// 新增
		return trMapper.insertSelective(tr);
	    } else {// 更新
		return trMapper.updateByPrimaryKeySelective(tr);
	    }
	}
    }

    @Override
    public List<AccessRecord> findAccessRecord(AccessRecord record)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findAccessRecord(record);
    }

    @Override
    public Integer addBlackListByVisitorId(Blacklist black) throws Exception {
	// TODO Auto-generated method stub
	return blackMapper.insertSelective(black);
    }

    @Override
    public Visitor findVisitorById(Integer vID) throws Exception {
	// TODO Auto-generated method stub
	return visitorMapper.selectByPrimaryKey(vID);
    }

    @Override
    public Blacklist findBlackListByVisitorId(Integer vID) throws Exception {
	// TODO Auto-generated method stub
	return blackMapper.findBlackListByVisitorId(vID);
    }

    @Override
    public List<Visitor> findBlackListByIdNum(String idNum) throws Exception {
	// TODO Auto-generated method stub
	return visitorMapper.findBlackListByIdNum(idNum);
    }

    @Override
    public List<Visitor> isLogOff(String idNum) throws Exception {
	// TODO Auto-generated method stub
	return visitorMapper.isLogOff(idNum);
    }

    @Override
    public List<Visitor> isAppointment(String idNum) throws Exception {
	// TODO Auto-generated method stub
	return visitorMapper.isAppointment(idNum);
    }

    @Override
    public UUser findUserInfoByCardNum(String cardNum) throws Exception {
	// TODO Auto-generated method stub
	return userMapper.findUserInfoByCardNum(cardNum);
    }

    @Override
    public Integer addSwingCardRecord(SwingCardRecord scr) throws Exception {
	// TODO Auto-generated method stub
	return scrMapper.insertSelective(scr);
    }

    @Override
    public EntranceGuard findOpenDoorPermission(String egNum, Long uId)
	    throws Exception {
	// TODO Auto-generated method stub
	return egMapper.findOpenDoorPermission(egNum, uId);
    }

    @Override
    public AccessRecord findAccessRecordByCardNum(String cardNum)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findAccessRecordByCardNum(cardNum);
    }

    @Override
    public List<AccessRecord> findNotUploadAccessRecord(Integer num)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findNotUploadAccessRecord(num);
    }

    @Override
    public List<AccessRecord> findAccessRecordByVid(Integer vID)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findAccessRecordByVid(vID);
    }

    @Override
    public UUserDep findDepartmentByUid(Integer uID) throws Exception {
	// TODO Auto-generated method stub
	return depMapper.findDepartmentByUid(uID);
    }

    @Override
    public VisitorCar findVcarByarID(Integer arID) throws Exception {
	// TODO Auto-generated method stub
	return vCarMapper.findCarByArId(arID);
    }

    @Override
    public AccessRecord findLastAccessRecordByIdNum(String idNum)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findLastAccessRecordByIdNum(idNum);
    }

    @Override
    public Integer findAccessRecordPageCount(AccessRecord record)
	    throws Exception {
	// TODO Auto-generated method stub
	return acMapper.findAccessRecordPageCount(record);
    }

    @Override
    public List<UUser> findUserByPhoneOrName(String param, Integer pageindex,
	    Integer pagesize) throws Exception {
	// TODO Auto-generated method stub
	return userMapper.findUserByPhoneOrName(param, pageindex, pagesize);
    }

    @Override
    public Integer findUserCountByPhoneOrName(String param) throws Exception {
	return userMapper.findUserCountByPhoneOrName(param);
    }

    @Override
    public UUser findUserByPhoneNum(String phone) throws Exception {
	// TODO Auto-generated method stub
	return userMapper.findUserByPhoneNum(phone);
    }
	@Override
	public UUser findUserInfoById(long id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectUserInfoByPrimaryKey(id);
	}


    @Override
    public List<AccessRecord> isAccessRecord(AccessRecord r) throws Exception {
	// TODO Auto-generated method stub
	return acMapper.isAccessRecord(r);
    }

    @Override
    public Integer updateAccessRecordByListID(List<UploadStatusInfo> listID)
	    throws Exception {
	// TODO Auto-generated method stub
	int flag = 0;
	for (UploadStatusInfo us : listID) {
	    AccessRecord record = new AccessRecord();
	    record.setId(us.getId());
	    record.setIsUpload(us.getNum());
	    flag += acMapper.updateByPrimaryKeySelective(record);
	}
	return flag;
    }

    @Override
    public Integer updateOpenDoorStatus(Integer id, Integer flag)
	    throws Exception {
	// TODO Auto-generated method stub
	SwingCardRecord record = new SwingCardRecord();
	record.setId(id);
	record.setIsOpenDoor(flag);
	return scrMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<EntranceGuard> findAllUseEntranceGuard() throws Exception {
	return egMapper.findAllUseEntranceGuard();
    }

	@Override
	public AccessRecord findNowAccessRecordByVid(Integer vID) throws Exception {
		return recordMapper.findNowAccessRecordByVid(vID);
	}

}

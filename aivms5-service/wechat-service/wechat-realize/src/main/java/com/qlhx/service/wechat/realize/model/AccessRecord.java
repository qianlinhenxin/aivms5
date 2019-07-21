package com.qlhx.service.wechat.realize.model;

import com.qlhx.service.wechat.realize.utils.StringUtils;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;



/**
 *
 * <p>
 * Title: 访客记录
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 *
 * @author alaska
 * @date 2017年6月27日 下午1:52:29
 */
@SuppressWarnings("serial")
@XmlRootElement
public class AccessRecord extends BaseApiModel implements Serializable {

	
private String vname;
    
    private String uname;
    /**  */
    private Integer id;

    /** 生成二维码的编码（生成规则：MD5(id+creatTime)生成的串，从第一位开始取值，每隔3位取一次，共取8位） */
    private String rcode;

    /** 随行人员父级ID */
    private Integer parentid;

    /** 访客ID */
    private Long vid;
    
    private Visitor visitor;

    /** 被访人ID（u_user） */
    private Integer uid;
    
    
    private UUser user;

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    private Department dept;
    /** 访客信息记录 */
    private String createtime;

    /** 访问开始时间 */
    private String starttime;

    /** 访客结束时间 */
    private String endtime;

    /** 注销访问时间 */
    private String logofftime;

    /** 记录当前状态（0：进行中 1：超时未注销 2：结束 3：未登记） */
    private Integer status;

    /** 来访事由 */
    private String reasons;

    /** 来访单位 */
    private String unit;

    /** 来访人数 */
    private Integer num;

    /** 访客记录类型（0：非预约 1：预约） */
    private Integer type;

    /** 是否打印访客凭条（0：未打印 1：已打印） */
    private Integer isprintvoucher;

    /** 是否发放卡（0：未发放 1：已发放） */
    private Integer ispullcard;

    /** 发放卡片类型（0：IC卡） */
    private Integer cardtype;

    /** 发放卡卡号 */
    private String cardnum;

    /** 访客现场照片 */
    private String sitephoto;

    // 来访车辆
    private VisitorCar vCar;

    // 带入物品
    private List<DraginRes> drList;

    // 带出物品
    private List<TakeoutRes> trList;

    // 上传公安网标识（0：未上传 1：已上传）
    private Integer isUpload;

    private Integer pageNum;

    private Integer yyID;
    
  //上传公安网时间
    private String uploadPoliceTime;
    
    //上传公安网状态 1成功 0 失败
    private Integer uploadPoliceStatus;
    
    //上传公安网结果
    private String uploadPoliceResult;

    /**
     * 
     * This method returns the value of the database column u_access_record.id
     *
     * @return the value of u_access_record.id
     *
     * @mbggenerated
     */
    public Integer getId() {
	return id;
    }

    /**
     * 
     * This method sets the value of the database column u_access_record.id
     *
     * @param id
     *            the value for u_access_record.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * 生成二维码的编码（生成规则：MD5(id+creatTime)生成的串，从第一位开始取值，每隔3位取一次，共取8位） This method
     * returns the value of the database column u_access_record.rCode
     *
     * @return the value of u_access_record.rCode
     *
     * @mbggenerated
     */
    public String getRcode() {
	return rcode;
    }

    /**
     * 生成二维码的编码（生成规则：MD5(id+creatTime)生成的串，从第一位开始取值，每隔3位取一次，共取8位） This method
     * sets the value of the database column u_access_record.rCode
     *
     * @param rcode
     *            the value for u_access_record.rCode
     *
     * @mbggenerated
     */
    public void setRcode(String rcode) {
	this.rcode = rcode == null ? null : rcode.trim();
    }

    /**
     * 随行人员访问记录父级ID This method returns the value of the database column
     * u_access_record.parentID
     *
     * @return the value of u_access_record.parentID
     *
     * @mbggenerated
     */

    /**
     * 访客ID This method returns the value of the database column
     * u_access_record.vID
     *
     * @return the value of u_access_record.vID
     *
     * @mbggenerated
     */
    public Long getVid() {
	return vid;
    }

    /**
     * 访客ID This method sets the value of the database column
     * u_access_record.vID
     *
     * @param vid
     *            the value for u_access_record.vID
     *
     * @mbggenerated
     */
    public void setVid(Long vid) {
	this.vid = vid;
    }

    /**
     * 被访人ID（u_user） This method returns the value of the database column
     * u_access_record.uID
     *
     * @return the value of u_access_record.uID
     *
     * @mbggenerated
     */
    public Integer getUid() {
	return uid;
    }

    /**
     * 被访人ID（u_user） This method sets the value of the database column
     * u_access_record.uID
     *
     * @param uid
     *            the value for u_access_record.uID
     *
     * @mbggenerated
     */
    public void setUid(Integer uid) {
	this.uid = uid;
    }

    /**
     * 访客信息记录 This method returns the value of the database column
     * u_access_record.createTime
     *
     * @return the value of u_access_record.createTime
     *
     * @mbggenerated
     */
    public String getCreatetime() {
	return createtime != null ? createtime.replace(".0", "") : null;
    }

    /**
     * 访客信息记录 This method sets the value of the database column
     * u_access_record.createTime
     *
     * @param createtime
     *            the value for u_access_record.createTime
     *
     * @mbggenerated
     */
    public void setCreatetime(String createtime) {
	this.createtime = createtime;
    }

    /**
     * 访问开始时间 This method returns the value of the database column
     * u_access_record.startTime
     *
     * @return the value of u_access_record.startTime
     *
     * @mbggenerated
     */

    public String getStarttime() {
	return starttime != null ? starttime.replace(".0", "") : null;
    }

    /**
     * 访问开始时间 This method sets the value of the database column
     * u_access_record.startTime
     *
     * @param starttime
     *            the value for u_access_record.startTime
     *
     * @mbggenerated
     */
    public void setStarttime(String starttime) {
	this.starttime = starttime;
    }

    /**
     * 访客结束时间 This method returns the value of the database column
     * u_access_record.endTime
     *
     * @return the value of u_access_record.endTime
     *
     * @mbggenerated
     */

    public String getEndtime() {
	return endtime != null ? endtime.replace(".0", "") : null;
    }

    /**
     * 访客结束时间 This method sets the value of the database column
     * u_access_record.endTime
     *
     * @param endtime
     *            the value for u_access_record.endTime
     *
     * @mbggenerated
     */
    public void setEndtime(String endtime) {
	this.endtime = endtime;
    }

    /**
     * 注销访问时间 This method returns the value of the database column
     * u_access_record.logOffTime
     *
     * @return the value of u_access_record.logOffTime
     *
     * @mbggenerated
     */
    public String getLogofftime() {
	return logofftime != null ? logofftime.replace(".0", "") : null;
    }

    /**
     * 注销访问时间 This method sets the value of the database column
     * u_access_record.logOffTime
     *
     * @param logofftime
     *            the value for u_access_record.logOffTime
     *
     * @mbggenerated
     */
    public void setLogofftime(String logofftime) {
	this.logofftime = logofftime;
    }

    /**
     * 记录当前状态（0：进行中 1：超时未注销 2：结束） This method returns the value of the database
     * column u_access_record.status
     *
     * @return the value of u_access_record.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
	return status;
    }

    /**
     * 记录当前状态（0：进行中 1：超时未注销 2：结束 3:未登记） This method sets the value of the
     * database column u_access_record.status
     *
     * @param status
     *            the value for u_access_record.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
	this.status = status;
    }

    /**
     * 来访事由 This method returns the value of the database column
     * u_access_record.reasons
     *
     * @return the value of u_access_record.reasons
     *
     * @mbggenerated
     */
    public String getReasons() {
	return reasons;
    }

    /**
     * 来访事由 This method sets the value of the database column
     * u_access_record.reasons
     *
     * @param reasons
     *            the value for u_access_record.reasons
     *
     * @mbggenerated
     */
    public void setReasons(String reasons) {
	this.reasons = reasons == null ? null : reasons.trim();
    }

    /**
     * 来访单位 This method returns the value of the database column
     * u_access_record.unit
     *
     * @return the value of u_access_record.unit
     *
     * @mbggenerated
     */
    public String getUnit() {
	return unit;
    }

    /**
     * 来访单位 This method sets the value of the database column
     * u_access_record.unit
     *
     * @param unit
     *            the value for u_access_record.unit
     *
     * @mbggenerated
     */
    public void setUnit(String unit) {
	this.unit = unit == null ? null : unit.trim();
    }

    /**
     * 来访人数 This method returns the value of the database column
     * u_access_record.num
     *
     * @return the value of u_access_record.num
     *
     * @mbggenerated
     */
    public Integer getNum() {
	return num;
    }

    /**
     * 来访人数 This method sets the value of the database column
     * u_access_record.num
     *
     * @param num
     *            the value for u_access_record.num
     *
     * @mbggenerated
     */
    public void setNum(Integer num) {
	this.num = num;
    }

    /**
     * 访客记录类型（0：非预约 1：预约） This method returns the value of the database column
     * u_access_record.type
     *
     * @return the value of u_access_record.type
     *
     * @mbggenerated
     */
    public Integer getType() {
	return type;
    }

    /**
     * 访客记录类型（0：非预约 1：预约） This method sets the value of the database column
     * u_access_record.type
     *
     * @param type
     *            the value for u_access_record.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
	this.type = type;
    }

    /**
     * 是否打印访客凭条（0：未打印 1：已打印） This method returns the value of the database
     * column u_access_record.isPrintVoucher
     *
     * @return the value of u_access_record.isPrintVoucher
     *
     * @mbggenerated
     */
    public Integer getIsprintvoucher() {
	return isprintvoucher;
    }

    /**
     * 是否打印访客凭条（0：未打印 1：已打印） This method sets the value of the database column
     * u_access_record.isPrintVoucher
     *
     * @param isprintvoucher
     *            the value for u_access_record.isPrintVoucher
     *
     * @mbggenerated
     */
    public void setIsprintvoucher(Integer isprintvoucher) {
	this.isprintvoucher = isprintvoucher;
    }

    /**
     * 是否发放卡（0：未发放 1：已发放） This method returns the value of the database column
     * u_access_record.isPullCard
     *
     * @return the value of u_access_record.isPullCard
     *
     * @mbggenerated
     */
    public Integer getIspullcard() {
	return ispullcard;
    }

    /**
     * 是否发放卡（0：未发放 1：已发放） This method sets the value of the database column
     * u_access_record.isPullCard
     *
     * @param ispullcard
     *            the value for u_access_record.isPullCard
     *
     * @mbggenerated
     */
    public void setIspullcard(Integer ispullcard) {
	this.ispullcard = ispullcard;
    }

    /**
     * 发放卡片类型（0：IC卡） This method returns the value of the database column
     * u_access_record.cardType
     *
     * @return the value of u_access_record.cardType
     *
     * @mbggenerated
     */
    public Integer getCardtype() {
	return cardtype;
    }

    /**
     * 发放卡片类型（0：IC卡） This method sets the value of the database column
     * u_access_record.cardType
     *
     * @param cardtype
     *            the value for u_access_record.cardType
     *
     * @mbggenerated
     */
    public void setCardtype(Integer cardtype) {
	this.cardtype = cardtype;
    }

    /**
     * 发放卡卡号 This method returns the value of the database column
     * u_access_record.CardNum
     *
     * @return the value of u_access_record.CardNum
     *
     * @mbggenerated
     */
    public String getCardnum() {
	return cardnum;
    }

    /**
     * 发放卡卡号 This method sets the value of the database column
     * u_access_record.CardNum
     *
     * @param cardnum
     *            the value for u_access_record.CardNum
     *
     * @mbggenerated
     */
    public void setCardnum(String cardnum) {
	this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    /**
     * 访客现场照片 This method returns the value of the database column
     * u_access_record.sitePhoto
     *
     * @return the value of u_access_record.sitePhoto
     *
     * @mbggenerated
     */
    public String getSitephoto() {
	return sitephoto;
    }

    /**
     * 访客现场照片 This method sets the value of the database column
     * u_access_record.sitePhoto
     *
     * @param sitephoto
     *            the value for u_access_record.sitePhoto
     *
     * @mbggenerated
     */
    public void setSitephoto(String sitephoto) {
	this.sitephoto = sitephoto == null ? null : sitephoto.trim();
    }

    /**
     * @return the visitor
     */
    public Visitor getVisitor() {
	return visitor;
    }

    /**
     * @param visitor
     *            the visitor to set
     */
    public void setVisitor(Visitor visitor) {
	this.visitor = visitor;
    }

    /**
     * @return the user
     */
    public UUser getUser() {
	return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UUser user) {
	this.user = user;
    }

    /**
     * @return the vCar
     */
    public VisitorCar getVCar() {
	return vCar;
    }

    /**
     * @param vCar
     *            the vCar to set
     */
    public void setVCar(VisitorCar vCar) {
	this.vCar = vCar;
    }

    /**
     * @return the drList
     */
    public List<DraginRes> getDrList() {
	return drList;
    }

    /**
     * @param drList
     *            the drList to set
     */
    public void setDrList(List<DraginRes> drList) {
	this.drList = drList;
    }

    /**
     * @return the parentid
     */
    public Integer getParentid() {
	return parentid;
    }

    /**
     * @param parentid
     *            the parentid to set
     */
    public void setParentid(Integer parentid) {
	this.parentid = parentid;
    }

    /**
     * @return the trList
     */
    public List<TakeoutRes> getTrList() {
	return trList;
    }

    /**
     * @param trList
     *            the trList to set
     */
    public void setTrList(List<TakeoutRes> trList) {
	this.trList = trList;
    }

    /**
     * @return the isUpload
     */
    public Integer getIsUpload() {
	return isUpload;
    }

    /**
     * @param isUpload
     *            the isUpload to set
     */
    public void setIsUpload(Integer isUpload) {
	this.isUpload = isUpload;
    }

    /**
     * @return the vCar
     */
    public VisitorCar getvCar() {
	return vCar;
    }

    /**
     * @param vCar
     *            the vCar to set
     */
    public void setvCar(VisitorCar vCar) {
	this.vCar = vCar;
    }

    /**
     * @return the pageNum
     */
    public Integer getPageNum() {
	return pageNum;
    }

    /**
     * @param pageNum
     *            the pageNum to set
     */
    public void setPageNum(Integer pageNum) {
	this.pageNum = pageNum;
    }

    /**
     * @return the yyID
     */
    public Integer getYyID() {
	return yyID;
    }

    /**
     * @param yyID
     *            the yyID to set
     */
    public void setYyID(Integer yyID) {
	this.yyID = yyID;
    }

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUploadPoliceTime() {
		return StringUtils.isBlank(uploadPoliceTime)?null:uploadPoliceTime.replace(".0", "") ;
	}

	public void setUploadPoliceTime(String uploadPoliceTime) {
		this.uploadPoliceTime = uploadPoliceTime;
	}

	public Integer getUploadPoliceStatus() {
		return uploadPoliceStatus;
	}

	public void setUploadPoliceStatus(Integer uploadPoliceStatus) {
		this.uploadPoliceStatus = uploadPoliceStatus;
	}

	public String getUploadPoliceResult() {
		return uploadPoliceResult;
	}

	public void setUploadPoliceResult(String uploadPoliceResult) {
		this.uploadPoliceResult = uploadPoliceResult;
	}
    
    

}
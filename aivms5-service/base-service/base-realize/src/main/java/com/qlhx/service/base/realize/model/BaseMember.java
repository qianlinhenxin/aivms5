package com.qlhx.service.base.realize.model;

import java.util.Date;

public class BaseMember {
    private Long id;

    private String nickname;

    private String email;

    private String pswd;

    private Integer terminalpswd;

    private Date createTime;

    private Date lastLoginTime;

    private Long status;

    private Integer sex;

    private Integer nation;

    private Date birthday;

    private String address;

    private Integer isdel;

    private Integer uploadstatus;

    private Integer uploadnum;

    private Date rowtemp;

    private String idnum;

    public BaseNatiom getNationNum() {
        return nationNum;
    }

    public void setNationNum(BaseNatiom nationNum) {
        this.nationNum = nationNum;
    }

    private String photo;

    private String issuing;

    private Date validitydatestart;

    private String validitydateend;

    private String phone;

    private Integer depid;

    private String telephone;

    private String ecardnum;

    private Date ecardendtime;

    private String pinyin;

    private Integer syncid;

    private String companynum;

    private Integer isbindfinger;

    private Integer isbindface;

    private Date tabtime;

    private Date uploadtime;

    private String idphoto;



    private BaseNatiom nations;


    private String memberidentifier;
    private String groupname;

    private String groupidentifier;

    private BaseNatiom baseNatiom;

    private UUserDep dep;

    private BaseNatiom nationNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Integer getTerminalpswd() {
        return terminalpswd;
    }

    public void setTerminalpswd(Integer terminalpswd) {
        this.terminalpswd = terminalpswd;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getNation() {
        return nation;
    }

    public void setNation(Integer nation) {
        this.nation = nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getUploadstatus() {
        return uploadstatus;
    }

    public void setUploadstatus(Integer uploadstatus) {
        this.uploadstatus = uploadstatus;
    }

    public Integer getUploadnum() {
        return uploadnum;
    }

    public void setUploadnum(Integer uploadnum) {
        this.uploadnum = uploadnum;
    }

    public Date getRowtemp() {
        return rowtemp;
    }

    public void setRowtemp(Date rowtemp) {
        this.rowtemp = rowtemp;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getIssuing() {
        return issuing;
    }

    public void setIssuing(String issuing) {
        this.issuing = issuing;
    }

    public Date getValiditydatestart() {
        return validitydatestart;
    }

    public void setValiditydatestart(Date validitydatestart) {
        this.validitydatestart = validitydatestart;
    }

    public String getValiditydateend() {
        return validitydateend;
    }

    public void setValiditydateend(String validitydateend) {
        this.validitydateend = validitydateend;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEcardnum() {
        return ecardnum;
    }

    public void setEcardnum(String ecardnum) {
        this.ecardnum = ecardnum;
    }

    public Date getEcardendtime() {
        return ecardendtime;
    }

    public void setEcardendtime(Date ecardendtime) {
        this.ecardendtime = ecardendtime;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public Integer getSyncid() {
        return syncid;
    }

    public void setSyncid(Integer syncid) {
        this.syncid = syncid;
    }

    public String getCompanynum() {
        return companynum;
    }

    public void setCompanynum(String companynum) {
        this.companynum = companynum;
    }

    public Integer getIsbindfinger() {
        return isbindfinger;
    }

    public void setIsbindfinger(Integer isbindfinger) {
        this.isbindfinger = isbindfinger;
    }

    public Integer getIsbindface() {
        return isbindface;
    }

    public void setIsbindface(Integer isbindface) {
        this.isbindface = isbindface;
    }

    public Date getTabtime() {
        return tabtime;
    }

    public void setTabtime(Date tabtime) {
        this.tabtime = tabtime;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getIdphoto() {
        return idphoto;
    }

    public void setIdphoto(String idphoto) {
        this.idphoto = idphoto;
    }

    public BaseNatiom getNations() {
        return nations;
    }

    public void setNations(BaseNatiom nations) {
        this.nations = nations;
    }

    public String getMemberidentifier() {
        return memberidentifier;
    }

    public void setMemberidentifier(String memberidentifier) {
        this.memberidentifier = memberidentifier;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public String getGroupidentifier() {
        return groupidentifier;
    }

    public void setGroupidentifier(String groupidentifier) {
        this.groupidentifier = groupidentifier;
    }

    public BaseNatiom getBaseNatiom() {
        return baseNatiom;
    }

    public void setBaseNatiom(BaseNatiom baseNatiom) {
        this.baseNatiom = baseNatiom;
    }

    public UUserDep getDep() {
        return dep;
    }

    public void setDep(UUserDep dep) {
        this.dep = dep;
    }
}
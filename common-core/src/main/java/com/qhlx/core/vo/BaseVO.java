package com.qhlx.core.vo;

import com.qhlx.core.annotation.BeanField;
import com.qhlx.core.baseface.BaseInterface;

import java.io.Serializable;
import java.util.Date;

/**
 * Create by xb
 * The file is [ BaseBean] on [ s-file-system ] project
 * The file path is BaseBean
 *
 * @versio 1.0.0
 * @Author he ming xi
 * @date 2019/4/5 23:59
 * @description
 */
public class BaseVO implements Serializable, BaseInterface {

    /**
     * ID
     */
    @BeanField(desc = "ID")
    private Long sid;

    /**
     * 创建用户
     */
    @BeanField(desc = "创建用户")
    private Long createUser;

    /**
     * 创建用户名称
     */
    @BeanField(desc = "创建用户名称")
    private String createName;

    /**
     * 创建日期
     */
    @BeanField(desc = "创建日期")
    private Date createTime;

    /**
     * 创建IP
     */
    @BeanField(desc = "创建IP")
    private String createIp;

    /**
     * 修改用户
     */
    @BeanField(desc = "修改用户")
    private Long updateUser;

    /**
     * 修改用户名称
     */
    @BeanField(desc = "修改用户名称")
    private String updateName;

    /**
     * 修改日期
     */
    @BeanField(desc = "修改日期")
    private Date updateTime;

    /**
     * 修改IP
     */
    @BeanField(desc = "修改IP")
    private String updateIp;

    /**
     * 页数
     */
    @BeanField(desc = "页数")
    private Integer pageNum;

    /**
     * 页数大小
     */
    @BeanField(desc = "页数大小")
    private Integer pageSize;
    /**
     * 版本
     */
    @BeanField(desc = "版本")
    private Integer version;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String insertCheckParams() {
        return "ok";
    }

    @Override
    public String updateCheckParams() {
        return "ok";
    }

    @Override
    public String selectCheckParams() {
        return "ok";
    }

    @Override
    public String deleteCheckParams() {
        return "ok";
    }
}

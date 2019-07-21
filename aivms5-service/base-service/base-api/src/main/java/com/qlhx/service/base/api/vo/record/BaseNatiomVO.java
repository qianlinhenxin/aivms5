package com.qlhx.service.base.api.vo.record;

import java.util.Date;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/21 15:33
 * @Description desc:
 */
public class BaseNatiomVO {
    private Integer nationNum;

    private String value;

    private Date tabtime;

    private Date uploadtime;

    public Integer getNationNum() {
        return nationNum;
    }

    public void setNationNum(Integer nationNum) {
        this.nationNum = nationNum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
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
}

package com.qlhx.service.wechat.realize.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
@SuppressWarnings("serial")
@XmlRootElement
public class FingerPrama {
    public List<String> getFingerids() {
        return fingerids;
    }

    public void setFingerids(List<String> fingerids) {
        this.fingerids = fingerids;
    }

    /**
     * 人员指纹ID列表
     */
    private List<String> fingerids;

    public Integer getUtype() {
        return utype;
    }

    public void setUtype(Integer utype) {
        this.utype = utype;
    }

    /**
     * 人员类型
     */
    private Integer utype;
}

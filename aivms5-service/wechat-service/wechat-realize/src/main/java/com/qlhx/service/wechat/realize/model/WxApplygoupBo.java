package com.qlhx.service.wechat.realize.model;

import java.util.List;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/4 16:55
 * @description desc:
 */
public class WxApplygoupBo extends WechatPreapplygoup {

    public List<WechatPreapplygoupDetail> getPreapplygoupdetail() {
        return preapplygoupdetail;
    }

    public void setPreapplygoupdetail(List<WechatPreapplygoupDetail> preapplygoupdetail) {
        this.preapplygoupdetail = preapplygoupdetail;
    }

    /**
     * 详情
     */
    List<WechatPreapplygoupDetail> preapplygoupdetail;
}

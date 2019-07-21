package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.WxApplygoupBo;

import java.util.List;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/4 16:58
 * @description desc:
 */
public interface WechatPreapplygoupService {


    /**
     * 获取本地同步微信预约后一条记录
     *
     * @return
     */
    String GetMaxWxYuYueTabTime();



    void SyncWxApplyList(List<WxApplygoupBo> wxapplylist);

}

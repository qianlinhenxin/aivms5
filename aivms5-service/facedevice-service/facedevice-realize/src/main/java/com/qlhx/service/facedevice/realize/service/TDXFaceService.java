package com.qlhx.service.facedevice.realize.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.qlhx.service.facedevice.realize.model.Facedevice;
import com.qlhx.service.facedevice.realize.model.VisitorInfo;

/**
 * Created by Paris on 2018/9/11.
 */
public interface TDXFaceService {

    /**
     * 注册
     * @param visitorInfo
     * @return
     */
    public Map<String,String> regCard(Facedevice facedevice, VisitorInfo visitorInfo);

    /**
     * 卡号注册
     * @param visitorInfo
     * @return
     */
    public Map<String,String> delCard(Integer deviceId, VisitorInfo visitorInfo);

    //订阅
    /**
     * 订阅
     * @param device
     * @return
     */
    public Map<String,String> regisTopic(Facedevice device) throws UnsupportedEncodingException;

    //取消订阅

    /**
     * 取消订阅
     * @param device
     * @return
     */
    public Map<String,String> deRegisTopic(Facedevice device) throws UnsupportedEncodingException;

    /**
     * 删除闸机头设备上的记录
     */
    public void deleteMachineRecord(Facedevice device);
}

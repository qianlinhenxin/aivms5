package com.qlhx.service.facedevice.realize.service;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import com.qlhx.service.facedevice.realize.model.Facedevice;
import com.qlhx.service.facedevice.realize.model.VisitorInfo;

/**
 * Created by oldxue on 2019/2/14. 旷视人脸终端
 */
public interface TDX8FaceService {

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

    /**
     * 自动删除卡号注册
     * @param cardnum
     * @return
     */
    public Boolean autoDelCard(Facedevice facedevice, String cardnum);

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
     * 获取设备序列号
     * @param device
     * @return
     */
    public Map<String, String> getDeviceKey(Facedevice device);
}

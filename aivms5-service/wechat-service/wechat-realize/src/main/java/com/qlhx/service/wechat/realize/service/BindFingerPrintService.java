package com.qlhx.service.wechat.realize.service;



import com.qlhx.service.wechat.realize.model.FingerPrint;
import com.qlhx.service.wechat.realize.model.VVisitor;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
public interface BindFingerPrintService {
    /**
     * 绑定用户指纹
     * @param fingerPrint
     * @return
     */
      boolean BindFingerPrint(FingerPrint fingerPrint) throws Exception;

    /**
     * 是否存在指纹
     * @param fingerPrint 人脸模型
     * @return
     */
      boolean ExistVisitorFingerPrint(FingerPrint fingerPrint);

    /**
     * 根据指纹ID获取访客信息
     * @param fingerPrint
     * @return
     */
     VVisitor FindVisitorByFingerPrintId(FingerPrint fingerPrint);

    /**
     * 删除指纹绑定通过人员ID
     * @param fingerPrint
     * @return
     */
     boolean DelBindFingerPrintByUId(FingerPrint fingerPrint);

    /**
     * 通过faceids查找绑定的人员id
     * @param fingerPrintids
     * @return
     */
     FingerPrint GetFingerPrintByfingerId(List<String> fingerPrintids, Integer utype);
}

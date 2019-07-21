package com.qlhx.service.wechat.realize.service;


import com.qlhx.service.wechat.realize.model.FaceModels;
import com.qlhx.service.wechat.realize.model.VVisitor;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
public interface BindVisitorFaceService {
    /**
     * 绑定用户人脸模型
     * @param faceModels
     * @return
     */
      boolean BindVisitorFace(FaceModels faceModels);

    /**
     * 是否存在访客人脸模型
     * @param facemodel 人脸模型
     * @return
     */
      boolean ExistVisitorFace(FaceModels facemodel);

    /**
     * 根据人脸ID获取访客信息
     * @param faceids
     * @return
     */
     VVisitor FindVisitorByFaceid(List<String> faceids);

    /**
     * 删除访客人脸绑定
     * @param faceModels
     * @return
     */
     boolean DelBindFaceByVisiterId(FaceModels faceModels);

    /**
     * 通过faceids查找绑定的人员id
     * @param faceids
     * @return
     */
     FaceModels GetFaceModelByfaceids(List<String> faceids, Integer facetype);

}

package com.qlhx.service.wechat.realize.service.impl;

import com.qlhx.service.wechat.realize.dao.FaceModelsMapper;
import com.qlhx.service.wechat.realize.model.FaceModels;
import com.qlhx.service.wechat.realize.model.VVisitor;
import com.qlhx.service.wechat.realize.service.BindVisitorFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/11.
 */
@Service
public class BindVisitorFaceImpl implements BindVisitorFaceService {
    @Autowired
    FaceModelsMapper faceModelsMapper;
    @Override
    public boolean BindVisitorFace(FaceModels faceModels) {
      int count=  faceModelsMapper.existUserFaceModel(faceModels);
        if(count==0)
        {
            faceModelsMapper.insert(faceModels);
            return  true;
        }
        return false;
    }

    @Override
    public boolean ExistVisitorFace(FaceModels faceModels) {
        int count=  faceModelsMapper.existUserFaceModel(faceModels);
        if(count>0)
        {
            return  true;
        }
        return false;
    }

    @Override
    public VVisitor FindVisitorByFaceid(List<String> faceids) {
        String ids ="";
        if(faceids!=null)
        {

            for (int i =0;i<faceids.size();i++)
            {
                ids+="'"+faceids.get(i)+"'";
                if(i<faceids.size()-1)
                    ids+=",";
            }

        }
       return faceModelsMapper.findVisitorByFaceid(ids);
    }

    @Override
    public boolean DelBindFaceByVisiterId(FaceModels faceModels) {
        faceModelsMapper.deletebindfacebyuid(faceModels);
        return false;
    }

    @Override
    public FaceModels GetFaceModelByfaceids(List<String> faceids,Integer facetype) {
        String ids ="";
        if(faceids!=null)
        {

            for (int i =0;i<faceids.size();i++)
            {
                ids+="'"+faceids.get(i)+"'";
                if(i<faceids.size()-1)
                    ids+=",";
            }
        }
        FaceModels models =new FaceModels();
        models.setFacetype(facetype);
        models.setFacemodelid(ids);
        return faceModelsMapper.GetFaceModelByfaceids(models);
    }
}

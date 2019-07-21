package com.qlhx.service.wechat.realize.service.impl;


import com.qlhx.service.wechat.realize.dao.FingerPrintMapper;
import com.qlhx.service.wechat.realize.dao.VisitorMapper;
import com.qlhx.service.wechat.realize.model.FingerPrint;
import com.qlhx.service.wechat.realize.model.VVisitor;
import com.qlhx.service.wechat.realize.service.BindFingerPrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/10/19.
 */
@Service
public class BindFingerPrintImpl implements BindFingerPrintService {
    @Autowired
    FingerPrintMapper fingerPrintMapper;
    @Autowired
    VisitorMapper visitorMapper;

    @Override
    public boolean BindFingerPrint(FingerPrint fingerPrint) throws Exception {
        int count = fingerPrintMapper.existFingerPrint(fingerPrint);
        if (count == 0) {
            fingerPrintMapper.insert(fingerPrint);
            visitorMapper.UpateIsBindFinger(fingerPrint.getUid());
            return true;
        }
        visitorMapper.UpateIsBindFinger(fingerPrint.getUid());

        return false;
    }

    @Override
    public boolean ExistVisitorFingerPrint(FingerPrint fingerPrint) {
        int count = fingerPrintMapper.existFingerPrint(fingerPrint);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public VVisitor FindVisitorByFingerPrintId(FingerPrint fingerPrint) {
        return fingerPrintMapper.findVisitorByfingerids(fingerPrint);
    }

    @Override
    public boolean DelBindFingerPrintByUId(FingerPrint fingerPrint) {
        fingerPrintMapper.deletebindfingerbyuid(fingerPrint);
        return false;
    }

    private String GetIds(List<String> list) {
        String ids = "";
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                ids += "'" + list.get(i) + "'";
                if (i < list.size() - 1)
                    ids += ",";
            }
        }
        return ids;

    }

    @Override
    public FingerPrint GetFingerPrintByfingerId(List<String> fingerPrintids, Integer utype) {
        String ids = GetIds(fingerPrintids);
        FingerPrint fingerPrint = new FingerPrint();
        fingerPrint.setFingerprintid(ids);
        fingerPrint.setUtype(utype);
        return fingerPrintMapper.GetFingerPrintByfingerids(fingerPrint);
    }
}

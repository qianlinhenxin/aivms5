package com.qlhx.service.facedevice.realize.service.impl;

import java.util.List;

import com.qlhx.service.facedevice.realize.dao.FacedeviceMapper;
import com.qlhx.service.facedevice.realize.model.Facedevice;
import com.qlhx.service.facedevice.realize.service.FaceDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FaceDeviceServiceImpl implements FaceDeviceService {


    @Autowired
    FacedeviceMapper facedeviceMapper;
    
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return facedeviceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertSelective(Facedevice record) {
        return facedeviceMapper.insertSelective(record);
    }

    @Override
    public Facedevice selectByPrimaryKey(Integer id) {
        return facedeviceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Facedevice record) {
        return facedeviceMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Facedevice selectByIp(String ip) {
        return facedeviceMapper.selectByIp(ip);
    }

    @Override
    public Facedevice selectByIpandId(String ip, String id) {
        return facedeviceMapper.selectByIpandId(ip, id);
    }

    @Override
    public List<Facedevice> list(String model) {
        return facedeviceMapper.getalldevicebymodel(model);
    }

    @Override
    public List<Facedevice> getalldevicebymodel(String model) {
        return facedeviceMapper.getalldevicebymodel(model);
    }

    @Override
    public List<Facedevice> listAll() {
        return facedeviceMapper.findAll();
    }


}

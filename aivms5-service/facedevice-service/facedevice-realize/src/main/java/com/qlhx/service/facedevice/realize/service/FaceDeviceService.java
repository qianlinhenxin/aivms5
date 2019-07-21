package com.qlhx.service.facedevice.realize.service;

import java.util.List;

import com.qlhx.service.facedevice.realize.model.Facedevice;

public interface FaceDeviceService {
	
	/**
     * 删除人脸设备
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Integer id);

    /**
     * 添加人脸设备
     * @param record
     * @return
     */
    public int insertSelective(Facedevice record);

    /**
     * 查看人脸设备
     * @param id
     * @return
     */
    public Facedevice selectByPrimaryKey(Integer id);

    /**
     * 修改人脸设备
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Facedevice record);
    
    
    public Facedevice selectByIp(String ip);

    /**
     * 查询除某个id外是否有ip重复
     * @param ip
     * @param id
     * @return
     */
    public Facedevice selectByIpandId(String ip, String id);

    public List<Facedevice> list(String model);
    public List<Facedevice> getalldevicebymodel(String model);

    public List<Facedevice> listAll();

}

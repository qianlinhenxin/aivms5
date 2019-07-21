package com.qlhx.service.facedevice.realize.dao;


import com.qlhx.service.facedevice.realize.model.Facedevice;
import org.apache.ibatis.annotations.Param;

import java.util.List;



public interface FacedeviceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Facedevice record);

    int insertSelective(Facedevice record);

    Facedevice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Facedevice record);

    int updateByPrimaryKey(Facedevice record);

    List<Facedevice> getalldevicebymodel(@Param("model") String model);

    int updateDeviceOnlineStatus(Facedevice record);
    
    Facedevice selectByIp(String ip);
    Facedevice selectByIpandId(@Param("ip") String ip, @Param("id") String id);

    List<Facedevice> findAll();
   }
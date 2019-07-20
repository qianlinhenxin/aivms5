package com.qlhx.service.door.realize.qlhx.service.impl;

import com.qlhx.service.door.realize.qlhx.mapper.UEntranceGuardMapper;
import com.qlhx.service.door.realize.qlhx.model.UEntranceGuard;
import com.qlhx.service.door.realize.qlhx.service.EntranceGuardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EntranceGuardServiceImpl implements EntranceGuardService {

	@Autowired
    UEntranceGuardMapper entranceGuardMapper;
	
	@Override
	public List<UEntranceGuard> list()
	{
		return entranceGuardMapper.list();
	}

	@Override
	public UEntranceGuard selectBySn(String sn) {
		return entranceGuardMapper.selectBySn(sn);
	}

	@Override
	public int insertSelective(UEntranceGuard record) {
		return entranceGuardMapper.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(UEntranceGuard record) {
		return entranceGuardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return entranceGuardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertOrUpdate(String sn, String ip,int runStatus) {
		UEntranceGuard entranceGuard = entranceGuardMapper.selectBySn(sn);
		if(entranceGuard == null)
		{
			entranceGuard = new UEntranceGuard();
			entranceGuard.setIp(ip);
			entranceGuard.setSn(sn);
			entranceGuard.setOnlineStatus(1);
			entranceGuard.setRunStatus(runStatus);
			entranceGuard.setLastOnlineTime(new Date());
			entranceGuard.setCreatedate(new Date());
			entranceGuardMapper.insertSelective(entranceGuard);
			System.out.println("---------发现新设备 SN= "+sn+" ="+ip+" 已自动将信息保存到数据库---------");
		}
		else
		{
			if (entranceGuard.getIp() != null && !entranceGuard.getIp().equals(ip)) 
			{
				entranceGuard.setIp(ip);
				System.out.println("---------设备 SN= " + sn + " 的IP地址发生改变，已自动修改为最新的IP " + ip + " ---------");
			}

			entranceGuard.setOnlineStatus(1);
			entranceGuard.setRunStatus(runStatus);
			entranceGuard.setLastOnlineTime(new Date());
			entranceGuardMapper.updateByPrimaryKeySelective(entranceGuard);
		}
		return 0;
	}
	
	

}

package com.qlhx.service.wechat.realize.service.impl;
import com.qlhx.service.wechat.realize.dao.USysLogsMapper;
import com.qlhx.service.wechat.realize.model.USysLogs;
import com.qlhx.service.wechat.realize.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
    USysLogsMapper sysLogsMapper;
	
	@Override
	public int add(USysLogs sysLog)  throws Exception {
		return sysLogsMapper.insert(sysLog);
	}

	@Override
	public int add(String title, String content, Long userId) {
		USysLogs logs = new USysLogs();
		logs.setTitle(title);
		logs.setContent(content);
		logs.setUid(userId);
		logs.setRecordingtime(new Date());
		return sysLogsMapper.insert(logs);
	}
	
	

}

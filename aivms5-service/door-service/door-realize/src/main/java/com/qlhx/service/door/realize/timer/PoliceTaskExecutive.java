package com.qlhx.service.door.realize.timer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;


@Component
public class PoliceTaskExecutive {


    @Scheduled(cron = "0 0/1 * * * ?")
    private synchronized void upload() 
    {
    	
    }
    
    
    private String mapToString(Map map)
	{
		StringBuffer stringBuffer = new StringBuffer();
		Set<Map.Entry<String, Object>> entryseSet = map.entrySet();
		for (Map.Entry<String, Object> entry : entryseSet) {
			stringBuffer.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		stringBuffer.deleteCharAt(stringBuffer.length() - 1);
		return stringBuffer.toString();
	}
}

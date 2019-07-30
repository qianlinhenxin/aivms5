package com.qlhx.service.door.realize.timer;

import com.qhlx.core.util.IConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

@Component
@Order(value = 1)
public class CustomerRun implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		System.out.println("==========门禁服务启动成功!==========");
		InetAddress addr = InetAddress.getLocalHost();  
        String ip=addr.getHostAddress().toString();
        int port = Integer.valueOf(IConfig.getInstance().get("weigengPord"));
		System.out.println("======请将门禁的服务器接收IP设置为:"+ip+" 端口设为:"+port+" =======");
	}
	

}

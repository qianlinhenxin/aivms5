package com.qlhx.service.door.realize.qlhx.Listener;

import com.qlhx.base.util.IConfig;
import com.qlhx.service.door.realize.qlhx.service.EntranceGuardService;
import com.qlhx.service.door.realize.qlhx.service.SwingCardRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.InetAddress;
import java.net.UnknownHostException;


@WebListener
public class AT8000Listener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("---------------关闭AT8000监听----------------");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
	    boolean weigengDoorEnable = (Boolean.valueOf(IConfig.getInstance().get("weigengDoorEnable")));
		if(weigengDoorEnable)
		{
			System.out.println("---------------启动AT8000监听----------------");
			ServletContext servletContext = arg0.getServletContext();  
	        WebApplicationContext context =  WebApplicationContextUtils.getWebApplicationContext(servletContext);  
	        SwingCardRecordService service = (SwingCardRecordService) context.getBean("swingCardRecordServiceImpl");
	        EntranceGuardService entranceGuardService = (EntranceGuardService) context.getBean("entranceGuardServiceImpl");
	        
			new Thread(new Runnable() {
				public void run() {
					String ip = IConfig.getInstance().get("weigengIp");
					if (StringUtils.isBlank(ip)) {
						InetAddress addr = null;
						try {
							addr = InetAddress.getLocalHost();
							ip = addr.getHostAddress().toString();
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}

					int port = Integer.valueOf(IConfig.getInstance().get("weigengPord"));
					System.out.println("---------------IP:" + ip + " PORT:" + port + "----------------");
					Tools.WatchingServerRuning(ip, port, service, entranceGuardService);
				}
			}).start();
		}
	}

}

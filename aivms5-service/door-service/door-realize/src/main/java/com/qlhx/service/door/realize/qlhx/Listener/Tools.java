package com.qlhx.service.door.realize.qlhx.Listener;

import com.alibaba.fastjson.JSON;
import com.qlhx.base.util.IConfig;
import com.qlhx.service.door.realize.qlhx.model.USwingCardRecord;
import com.qlhx.service.door.realize.qlhx.service.EntranceGuardService;
import com.qlhx.service.door.realize.qlhx.service.SwingCardRecordService;
import com.qlhx.service.door.realize.qlhx.util.SpringUtil;
import com.qlhx.service.door.realize.qlhx.wgDriver.WatchingShortHandler;
import com.qlhx.service.door.realize.qlhx.wgDriver.WgUdpCommShort;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Tools {
	
    static Logger logger = LoggerFactory.getLogger(Tools.class);
    static Map<Long,Long > recordIndexMap = new HashMap<Long,Long>();
    
    static boolean openDoor =(Boolean.valueOf(IConfig.getInstance().get("openDoor")));
    
    static String aiGuardUrl = IConfig.getInstance().get("aiGuardUrl");
    
	
	/**
	 * 
	 * @param controllerIP
	 * @param controllerSN
	 * @param doorNo
	 * @return
	 */
	public static int openDoor(String controllerIP, long controllerSN,Integer doorNo)  //基本功能测试
	{
		byte[] recvBuff;
		int success =0;
		WgUdpCommShort pkt = new WgUdpCommShort();
		pkt.iDevSn = controllerSN;
		
		//打开udp连接
		pkt.CommOpen(controllerIP);
		
		pkt.Reset();
		pkt.functionID = (byte) 0x40;
		pkt.iDevSn = controllerSN; 
		pkt.data[0] =(byte) (doorNo & 0xff); //2013-11-03 20:56:33
		recvBuff = pkt.run();
		success =0;
		if (recvBuff != null)
		{
			if (WgUdpCommShort.getIntByByte(recvBuff[8]) == 1)
			{
				//有效开门.....
				logger.info("1.10 远程开门	 成功...");
				success =1;
			}
		} 
		pkt.CommClose();
		return success;
	}
	
	/**
	 * 
	 * @param controllerIP
	 * @param controllerSN
	 * @param
	 * @return
	 */
	public static Map<String,String> status(String controllerIP, long controllerSN)  //基本功能测试
	{
		Map<String,String> resultMap = new HashMap<String,String>();
		resultMap.put("tip", "online_status:1 在线、0 离线  ; online_status:1 设备正常、0 设备故障");
		byte[] recvBuff;
		int success =0;
		WgUdpCommShort pkt = new WgUdpCommShort();
		pkt.iDevSn = controllerSN;
		
		//打开udp连接
		pkt.CommOpen(controllerIP);
		
		pkt.Reset();
		pkt.functionID = (byte) 0x20;
		pkt.iDevSn = controllerSN; 
		recvBuff = pkt.run();
		if (recvBuff != null)
		{
			if (WgUdpCommShort.getIntByByte(recvBuff[13]) == 1)//有效性(0 表示不通过, 1表示通过)
			{
				resultMap.put("online_status", "1");
			}
			else
			{
				resultMap.put("online_status", "0");
			}
			
			if (WgUdpCommShort.getIntByByte(recvBuff[36]) == 0)//故障号 等于0 无故障  不等于0, 有故障
			{
				resultMap.put("run_status", "1");
			}
			else
			{
				resultMap.put("run_status", "0");
			}
		}
		else
		{
			resultMap.put("online_status", "0");
		}
		return resultMap;
	}
	
	/**
	 * 门禁实时监听
	 * @param watchServerIP
	 * @param watchServerPort
	 * @param service
	 * @param entranceGuardService 
	 * @return
	 */
	public static int WatchingServerRuning(String watchServerIP, int watchServerPort, SwingCardRecordService service, EntranceGuardService entranceGuardService) // 进入服务器监控状态
	{
		Queue<byte[]> queue = new LinkedList<byte[]>();
		
		 // 创建UDP数据包NIO
        NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
        // NIO设置底层IOHandler
        acceptor.setHandler(new WatchingShortHandler(queue));

        // 设置是否重用地址？ 也就是每个发过来的udp信息都是一个地址？
        DatagramSessionConfig dcfg = acceptor.getSessionConfig();
        dcfg.setReuseAddress(true);
        // 绑定端口地址
        try {
			acceptor.bind(new InetSocketAddress(watchServerIP, watchServerPort));
		} catch (IOException e) {
			logger.info("绑定接收服务器失败....");
			e.printStackTrace();
			return 0;
		}
        logger.info("进入接收服务器监控状态....[请注意防火墙设置]");
        
      long recordIndex = 0;
	  while(true)
	  {
		  if (!queue.isEmpty())
		    {
		         byte[] recvBuff;
		         synchronized (queue)
		         {
		        	 recvBuff= queue.poll();
		         }
		         if (recvBuff[1]== 0x20)
						{
							long sn = WgUdpCommShort.getLongByByte(recvBuff, 4, 4);
							long recordIndexGet = WgUdpCommShort.getLongByByte(recvBuff, 8, 4);
							int run_status = WgUdpCommShort.getIntByByte(recvBuff[36]);
							byte[] ip_byte = new byte[recvBuff.length - WgUdpCommShort.WGPacketSize];  
	     			        System.arraycopy(recvBuff, WgUdpCommShort.WGPacketSize, ip_byte,0,ip_byte.length); 
	     			        String clientIp = new String(ip_byte);
	     			        
	     			        logger.info(String.format("接收到来自控制器SN = %d 的心跳..", sn));
	     			        
	     			       RestTemplate rest = SpringUtil.getBean(RestTemplate.class);
	     				   rest.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//	     			        try {
//	     			    	    ApiResult<String> res = rest.postForObject("http://service-control/contr/putlog/", String.format("接收到来自控制器SN = %d 的心跳..", sn), ApiResult.class);
//						    } catch (Exception e) {
//						    	logger.error("连接基础微服务发生错误");
//						    }
	     			      
	     			       
	     			      
//							System.out.println(String.format("接收到来自控制器IP = %s SN = %d 的数据包..",clientIp, sn));
	     			        int recordType =WgUdpCommShort.getIntByByte(recvBuff[12]);
	     			        recordIndex = recordIndexMap.get(sn) == null ? 0: recordIndexMap.get(sn) ;
							entranceGuardService.insertOrUpdate(sn+"", clientIp,run_status);
							if (recordIndex < recordIndexGet)
							{
								long  recordCardNO =WgUdpCommShort.getLongByByte(recvBuff, 16, 4);
								//14	门号(1,2,3,4)	1	
								int recordDoorNO = WgUdpCommShort.getIntByByte(recvBuff[14]);

								//15	进门/出门(1表示进门, 2表示出门)	1	0x01
								int recordInOrOut = WgUdpCommShort.getIntByByte(recvBuff[15]);
								logger.info(String.format("类型=%d,接收到来自IP = %s 门禁SN = %d 的数据包,刷卡的卡号CardNo=%d,门号=%d,进出门=%d",recordType,clientIp, sn,recordCardNO,recordDoorNO,recordInOrOut));
								if(recordType ==1 )//只有刷卡的方式，才会请求ai
								{
									String result = null;
									synchronized (sn+"") 
									{
//											result = sendPost(recordCardNO+"", sn+"", recordDoorNO);
//										
//										logger.info("=============验证卡号权限结果："+result+"===============");
										 try {
					     			    	    ApiResult<String> res = rest.getForObject("http://service-control/service/receiveCard/"+sn+"/"+recordCardNO+"/"+recordDoorNO+"/"+recordInOrOut, ApiResult.class);
//						     			        if(res.getCode() == 1000)
//						     			        {
//						     			        	int r = openDoor(clientIp, sn, recordDoorNO);
//						     			        	rest.postForObject("http://service-control/contr/putlog/","正在刷卡,卡号 =" + recordCardNO + "  有权限", ApiResult.class);
//						     			        	logger.info("正在刷卡,卡号 = " + recordCardNO + "  有权限,开门结果:"+r);
//						     			        }
//						     			        else
//						     			        {
//						     			        	rest.postForObject("http://service-control/contr/putlog/","正在刷卡,卡号 = " + recordCardNO + "  无权限", ApiResult.class);
//						     			        	logger.info("正在刷卡n卡号 = " + recordCardNO + "  无权限");
//						     			        }
										    } catch (Exception e) {
						
										    	logger.error("连接基础微服务发生错误",e);
										    }
									}
									
									
									if(true) 
									{
//										if(result != null)
//										{
//											Map resultMap = JSON.parseObject(result, Map.class);
//											if((int)resultMap.get("code") == 0 )//有权限开门
//											{
//												int r = openDoor(clientIp, sn, recordDoorNO);
//												logger.info("========开门："+r+"=========");
//											}
//										}
//										else
//										{
//											logger.info("=============验证卡号权限：无结果，网络可能发生故障===============");
//										}
									}
								}
								USwingCardRecord swingCardRecord = new USwingCardRecord();
								swingCardRecord.setSn(sn+"");
								swingCardRecord.setCardno(recordCardNO+"");
								swingCardRecord.setDoorno(recordDoorNO);
								swingCardRecord.setDirection(recordInOrOut);
								service.insertSelective(swingCardRecord);
								
								recordIndex = recordIndexGet;
								recordIndexMap.put(sn, recordIndexGet);
								displayRecordInformation(recvBuff); 
							}
						}
		    }
		  try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	     
	  }
	} 
	
	private static synchronized String sendPost(String cardNum,String egNum,Integer flag ) 
	{
		Map json = new HashMap();
        
        try {
        	json.put("cardNum", cardNum);
            json.put("egNum", egNum);
			json.put("flag", flag);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String jsonString = JSON.toJSONString(json);
        logger.info("=======url:"+aiGuardUrl+"====body:"+jsonString+"==========");
        return null;
//		return HTTP.post(aiGuardUrl, jsonString);
	}
	
	 public static String bytesToHexFun3(byte[] bytes) {
	        StringBuilder buf = new StringBuilder(bytes.length * 2);
	        for(byte b : bytes) { // 使用String的format方法进行转换
	        	
	            buf.append(String.format("%02x", new Integer(b & 0xff)));
	        }

	        return buf.toString();
	    }
	
	public static void displayRecordInformation(byte[] recvBuff)
    {

      //8-11	最后一条记录的索引号
		//(=0表示没有记录)	4	0x00000000
		long recordIndex = WgUdpCommShort.getLongByByte(recvBuff, 8, 4);


		//12	记录类型
		//0=无记录
		//1=刷卡记录
		//2=门磁,按钮, 设备启动, 远程开门记录
		//3=报警记录	1	
		int recordType =WgUdpCommShort.getIntByByte(recvBuff[12]);

		//13	有效性(0 表示不通过, 1表示通过)	1	
		int recordValid = WgUdpCommShort.getIntByByte(recvBuff[13]);

		//14	门号(1,2,3,4)	1	
		int recordDoorNO = WgUdpCommShort.getIntByByte(recvBuff[14]);

		//15	进门/出门(1表示进门, 2表示出门)	1	0x01
		int recordInOrOut = WgUdpCommShort.getIntByByte(recvBuff[15]);

		//16-19	卡号(类型是刷卡记录时)
		//或编号(其他类型记录)	4	
		long  recordCardNO =WgUdpCommShort.getLongByByte(recvBuff, 16, 4);

		
		//20-26	刷卡时间:
		//年月日时分秒 (采用BCD码)见设置时间部分的说明
		String recordTime=  String.format("%02X%02X-%02X-%02X %02X:%02X:%02X", 
			WgUdpCommShort.getIntByByte(recvBuff[20]),
			WgUdpCommShort.getIntByByte(recvBuff[21]),
			WgUdpCommShort.getIntByByte(recvBuff[22]),
			WgUdpCommShort.getIntByByte(recvBuff[23]),
			WgUdpCommShort.getIntByByte(recvBuff[24]),
			WgUdpCommShort.getIntByByte(recvBuff[25]),
			WgUdpCommShort.getIntByByte(recvBuff[26]));

		//2012.12.11 10:49:59	7	
		//27	记录原因代码(可以查 "刷卡记录说明.xls"文件的ReasonNO)
		//处理复杂信息才用	1	
		int reason = WgUdpCommShort.getIntByByte(recvBuff[27]);
		
        //0=无记录
        //1=刷卡记录
        //2=门磁,按钮, 设备启动, 远程开门记录
        //3=报警记录	1	
        //0xFF=表示指定索引位的记录已被覆盖掉了.  请使用索引0, 取回最早一条记录的索引值
        if (recordType == 0)
        {
        	logger.info(String.format("索引位=%u  无记录", recordIndex));
        }
        else if (recordType == 0xff)
        {
        	logger.info(" 指定索引位的记录已被覆盖掉了,请使用索引0, 取回最早一条记录的索引值");
        }
        else if (recordType == 1) //2015-06-10 08:49:31 显示记录类型为卡号的数据
        {
            //卡号
        	logger.info(String.format("索引位=%d  ", recordIndex));
        	logger.info(String.format("  卡号 = %d", recordCardNO));
        	logger.info(String.format("  门号 = %d", recordDoorNO));
        	logger.info(String.format("  进出 = %s", recordInOrOut == 1 ? "进门" : "出门"));
        	logger.info(String.format("  有效 = %s", recordValid == 1 ? "通过" : "禁止"));
        	logger.info(String.format("  时间 = %s", recordTime));
        	logger.info(String.format("  描述 = %s", getReasonDetailChinese(reason)));
        }
        else if (recordType == 2)
        {
            //其他处理
            //门磁,按钮, 设备启动, 远程开门记录
        	logger.info(String.format("索引位=%d  非刷卡记录", recordIndex));
        	logger.info(String.format("  编号 = %d", recordCardNO));
        	logger.info(String.format("  门号 = %d", recordDoorNO));
        	logger.info(String.format("  时间 = %s", recordTime));
        	logger.info(String.format("  描述 = %s", getReasonDetailChinese(reason)));
        }
        else if (recordType == 3)
        {
            //其他处理
            //报警记录
        	logger.info(String.format("索引位=%d  报警记录", recordIndex));
            logger.info(String.format("  编号 = %d", recordCardNO));
            logger.info(String.format("  门号 = %d", recordDoorNO));
            logger.info(String.format("  时间 = %s", recordTime));
            logger.info(String.format("  描述 = %s", getReasonDetailChinese(reason)));
        }
    }
	public static   String getReasonDetailChinese(int Reason) //中文
    {
        if (Reason > 45)
        {
            return "";
        }
        if (Reason <= 0)
        {
            return "";
        }
        return RecordDetails[(Reason - 1) * 4 + 3]; //中文信息
    }
	
	public static  String RecordDetails[] =
	    {
	//记录原因 (类型中 SwipePass 表示通过; SwipeNOPass表示禁止通过; ValidEvent 有效事件(如按钮 门磁 超级密码开门); Warn 报警事件)
	//代码  类型   英文描述  中文描述
	"1","SwipePass","Swipe","刷卡开门",
	"2","SwipePass","Swipe Close","刷卡关",
	"3","SwipePass","Swipe Open","刷卡开",
	"4","SwipePass","Swipe Limited Times","刷卡开门(带限次)",
	"5","SwipeNOPass","Denied Access: PC Control","刷卡禁止通过: 电脑控制",
	"6","SwipeNOPass","Denied Access: No PRIVILEGE","刷卡禁止通过: 没有权限",
	"7","SwipeNOPass","Denied Access: Wrong PASSWORD","刷卡禁止通过: 密码不对",
	"8","SwipeNOPass","Denied Access: AntiBack","刷卡禁止通过: 反潜回",
	"9","SwipeNOPass","Denied Access: More Cards","刷卡禁止通过: 多卡",
	"10","SwipeNOPass","Denied Access: First Card Open","刷卡禁止通过: 首卡",
	"11","SwipeNOPass","Denied Access: Door Set NC","刷卡禁止通过: 门为常闭",
	"12","SwipeNOPass","Denied Access: InterLock","刷卡禁止通过: 互锁",
	"13","SwipeNOPass","Denied Access: Limited Times","刷卡禁止通过: 受刷卡次数限制",
	"14","SwipeNOPass","Denied Access: Limited Person Indoor","刷卡禁止通过: 门内人数限制",
	"15","SwipeNOPass","Denied Access: Invalid Timezone","刷卡禁止通过: 卡过期或不在有效时段",
	"16","SwipeNOPass","Denied Access: In Order","刷卡禁止通过: 按顺序进出限制",
	"17","SwipeNOPass","Denied Access: SWIPE GAP LIMIT","刷卡禁止通过: 刷卡间隔约束",
	"18","SwipeNOPass","Denied Access","刷卡禁止通过: 原因不明",
	"19","SwipeNOPass","Denied Access: Limited Times","刷卡禁止通过: 刷卡次数限制",
	"20","ValidEvent","Push Button","按钮开门",
	"21","ValidEvent","Push Button Open","按钮开",
	"22","ValidEvent","Push Button Close","按钮关",
	"23","ValidEvent","Door Open","门打开[门磁信号]",
	"24","ValidEvent","Door Closed","门关闭[门磁信号]",
	"25","ValidEvent","Super Password Open Door","超级密码开门",
	"26","ValidEvent","Super Password Open","超级密码开",
	"27","ValidEvent","Super Password Close","超级密码关",
	"28","Warn","Controller Power On","控制器上电",
	"29","Warn","Controller Reset","控制器复位",
	"30","Warn","Push Button Invalid: Disable","按钮不开门: 按钮禁用",
	"31","Warn","Push Button Invalid: Forced Lock","按钮不开门: 强制关门",
	"32","Warn","Push Button Invalid: Not On Line","按钮不开门: 门不在线",
	"33","Warn","Push Button Invalid: InterLock","按钮不开门: 互锁",
	"34","Warn","Threat","胁迫报警",
	"35","Warn","Threat Open","胁迫报警开",
	"36","Warn","Threat Close","胁迫报警关",
	"37","Warn","Open too long","门长时间未关报警[合法开门后]",
	"38","Warn","Forced Open","强行闯入报警",
	"39","Warn","Fire","火警",
	"40","Warn","Forced Close","强制关门",
	"41","Warn","Guard Against Theft","防盗报警",
	"42","Warn","7*24Hour Zone","烟雾煤气温度报警",
	"43","Warn","Emergency Call","紧急呼救报警",
	"44","RemoteOpen","Remote Open Door","操作员远程开门",
	"45","RemoteOpen","Remote Open Door By USB Reader","发卡器确定发出的远程开门"
	    };
}

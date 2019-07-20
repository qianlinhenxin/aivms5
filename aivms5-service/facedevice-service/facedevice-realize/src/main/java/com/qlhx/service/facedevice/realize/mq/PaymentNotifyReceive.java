package com.qlhx.service.facedevice.realize.mq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import com.qlhx.base.model.ApiResult;
import com.qlhx.base.model.MQRegFace;
import com.qlhx.service.facedevice.realize.model.Facedevice;
import com.qlhx.service.facedevice.realize.model.VisitorInfo;
import com.qlhx.service.facedevice.realize.service.TDX8FaceService;
import com.qlhx.service.facedevice.realize.service.TDXFaceService;
import com.qlhx.service.facedevice.realize.service.impl.FaceDeviceServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.qlhx.common.model.ApiResult;
import com.qlhx.common.model.MQLogger;
import com.qlhx.common.model.MQRegFace;
import com.qlhx.model.Facedevice;
import com.qlhx.model.VisitorInfo;
import com.qlhx.service.TDX8FaceService;
import com.qlhx.service.TDXFaceService;
import com.qlhx.service.impl.FaceDeviceServiceImpl;

@Component
public class PaymentNotifyReceive {
	
	private  final Logger logger = LoggerFactory.getLogger(PaymentNotifyReceive.class);
	
	@Autowired
    FaceDeviceServiceImpl faceDeviceServiceImpl;

    @Autowired
    TDXFaceService tdxFaceService;

    @Autowired
    TDX8FaceService tdx8FaceService;
	
//	@RabbitListener(queues = MQLogger.ROUTING_KEY)
//    @RabbitHandler
//    public void receiveLogger(String log) {
//		logger.info("=========="+log+"==========");
//		try {
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        
//    }
	
	
	@RabbitListener(queues = MQRegFace.ROUTING_KEY)
    @RabbitHandler
    public void regFace(String log) {
		
		try {
			logger.info("=========="+log+"==========");
			VisitorInfo visitorInfo = JSON.parseObject(log, VisitorInfo.class);
			ApiResult<String> result = reguserface(visitorInfo);
			logger.info("==========result:"+JSON.toJSONString(result)+"==========");
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
	
	
	
	private  boolean  isFail = false;
    private  ApiResult<String> reguserface(VisitorInfo visitorInfo) {
        ApiResult<String> result = new ApiResult<String>();
        Map resultMap = null;
        try {
            isFail = false;
            String deviceIds = visitorInfo.getDeviceIds();
            if (StringUtils.isBlank(deviceIds)) {
                result.setCode(ApiResult.PARAM_ERROR);
                result.setMsg("设备id不能为空");
            } else {
                String[] deviceIdArr = deviceIds.split(",");
//                boolean isFail = false;
                String errMsg = "";//失败信息合集
                List<Integer> successTDXList = new ArrayList<Integer>();//成功的腾达迅设备
                List<Integer> successTDX8List = new ArrayList<>();


                ExecutorService exec = Executors.newCachedThreadPool();
                List<ApiResult<String>> failResult= new ArrayList<>();

                for (String devicdId : deviceIdArr) {
                    if (!isFail) {
                        Integer devicdId_int = 0;
                        try {
                            devicdId_int = Integer.parseInt(devicdId);
                        }catch (Exception ex)
                        {
                            continue;
                        }
                        Facedevice faceDevice = faceDeviceServiceImpl.selectByPrimaryKey(devicdId_int);
                        if(faceDevice != null)
                        {

                        	switch (faceDevice.getModel()) {
                                case "TDX":
                                {

                                    Runnable task = new Runnable() {
                                        @Override
                                        public void run() {
                                            Map<String, String> tempResult = tdxFaceService.regCard(faceDevice, visitorInfo);
                                            if (tempResult == null || tempResult.get("code") == null || (!tempResult.get("code").equals("1000"))) {

                                                isFail = true;
                                            } else {
                                                successTDXList.add(faceDevice.getId());
                                            }
                                        }
                                    };
                                    exec.submit(task);
                                    break;
                                }
                                case "TDX8":
                                {

                                    Runnable task = new Runnable() {
                                        @Override
                                        public void run() {
                                            Map<String, String> tempResult = tdx8FaceService.regCard(faceDevice, visitorInfo);
                                            if (tempResult == null || tempResult.get("code") == null || (!tempResult.get("code").equals("1000"))) {
                                                isFail = true;
                                            } else {
                                                successTDX8List.add(faceDevice.getId());
                                            }
                                        }
                                    };
                                    exec.submit(task);

                                    break;
                                }
                            default:
                                break;
                        }
                       }
                        
                    }
                }
                exec.shutdown();
                while(true)
                {
                    if(exec.isTerminated())
                    {
                        logger.info("=====所有的子线程都结束了！=========");
                        break;
                    }
                    Thread.sleep(200);
                }

                if (deviceIdArr.length != successTDXList.size()+successTDX8List.size())//有失败的设备，需要回滚
                {
                    result.setCode(ApiResult.OPERATION_ERROR);
                    result.setMsg("人脸注册失败,请重新注册");
                    result.setCause(errMsg);

                    for (Integer hcDeviceId : successTDXList) {
                        tdxFaceService.delCard(hcDeviceId, visitorInfo);
                    }

                    for (Integer hcDeviceId : successTDX8List) {
                        tdx8FaceService.delCard(hcDeviceId, visitorInfo);
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.getErrorResult(e);
            logger.error("执行注册人脸接口异常", e);

        }
        return result;
    }
    
    
    
}
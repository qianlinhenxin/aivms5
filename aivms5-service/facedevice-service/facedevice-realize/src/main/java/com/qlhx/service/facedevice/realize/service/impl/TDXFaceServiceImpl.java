package com.qlhx.service.facedevice.realize.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qhlx.core.util.web.HttpClient;
import com.qlhx.service.facedevice.realize.dao.FacedeviceMapper;
import com.qlhx.service.facedevice.realize.model.Facedevice;
import com.qlhx.service.facedevice.realize.model.VisitorInfo;
import com.qlhx.service.facedevice.realize.service.TDXFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Paris on 2018/9/11.
 */
@Service
public class TDXFaceServiceImpl implements TDXFaceService {

    /**
     * 注册人的string
     * person json
     */
    private String strCreatePerson= "person=%s&pass=%s";

    /**
     * 注册人的地接口地址/person/create
     */
    private String urlCreatePerson=":8090/person/create";

    /**
     * 查询人
     */
    private String strFindPerson="id=%s&pass=%s";

    /**
     * 查询人的接口地址
     */
    private String urlFindPerson=":8090/person/find";

    /**
     * 注册人脸string
     */
    private String strCreateFace="pass=%s&personId=%s&faceId=%s&imgBase64=%s&isEasyWay=true";

    /**
     * 注册人脸URL
     */
    private String urlCreateFace=":8090/face/create";

    /**
     * 删除人员
     */
    private String strDeleteUser="id=%s&pass=%s";

    /**
     * 删除人员接口
     */
    private String urlDeleteUser=":8090/person/delete";

    /**
     * 注册人脸订阅
     */
    private String urlRegisTopic = ":8090/setIdentifyCallBack";
    /**
     * 订阅string
     */
    private String strRegisTopic = "callbackUrl=%s&pass=%s";

    Logger logger = LoggerFactory.getLogger(TDXFaceServiceImpl.class);

    /**
     * 删除记录
     */
    private String urlDeleteRecord=":8090/deleteRecords";
    private String strDeleteRecord="pass=%&time=2030-12-31 23:59:59";

    @Autowired
    FacedeviceMapper facedeviceMapper;

    @Override
    public Map<String, String> regCard(Facedevice facedevice, VisitorInfo visitorInfo) {
        logger.info("=====开始注册人员:"+visitorInfo.getCardNum()+"\t"+facedevice.getIp()+" =====");
        Map<String, String> resultMap = new HashMap<String, String>();
        try {

        if(visitorInfo.getCardNum()==null && visitorInfo.getCardNum().length()==0)
        {
            logger.info("卡号不能为空！");
            resultMap.put("msg","卡号不能为空！");
            resultMap.put("code","9000");
            return resultMap;
        }

        List<Map> infos = new ArrayList<>();
        Map map = null;
        boolean dresult=FindUser(facedevice , visitorInfo);
        //查询人
        if(dresult)
        {
            //删除人员
            dresult = DeleteUser(facedevice , visitorInfo);
            if(!dresult)
            {
                logger.info("删除人员失败:"+visitorInfo.getCardNum());
                resultMap.put("msg","删除人员失败:"+visitorInfo.getCardNum());
                resultMap.put("code","9000");
                return resultMap;
            }
        }

        //重新注册人
        dresult = CreateUser(facedevice , visitorInfo);
        if(!dresult)
        {
            logger.info("添加人员失败:"+visitorInfo.getCardNum());
            resultMap.put("msg","添加人员失败:"+visitorInfo.getCardNum());
            resultMap.put("code","9000");
            return resultMap;
        }

        //注册人脸
        dresult = CreateFace(facedevice , visitorInfo);
        if(!dresult)
        {
            logger.info("添加人脸失败:"+visitorInfo.getCardNum()+"\t"+facedevice.getIp());
            resultMap.put("msg","添加人脸失败:"+visitorInfo.getCardNum()+"\t"+facedevice.getIp());
            resultMap.put("code","9000");
            //添加人员失败，则删除人员，避免人和照片不匹配
            DeleteUser(facedevice , visitorInfo);

        }else {

            logger.info("添加人脸成功：" + visitorInfo.getCardNum()+"\t"+facedevice.getIp());
            resultMap.put("msg", "添加人脸成功:" + visitorInfo.getCardNum()+"\t"+facedevice.getIp());
            resultMap.put("code", "1000");
        }
        } catch (Exception ex)
        {
            ex.printStackTrace();
            logger.info("添加人脸失败:"+visitorInfo.getCardNum()+"\t"+facedevice.getIp()+"\t"+ex.getMessage());
            resultMap.put("msg","添加人脸失败:"+visitorInfo.getCardNum()+"\t"+facedevice.getIp());
            resultMap.put("code","9000");
        }
        return resultMap;
    }

    private boolean CreateFace(Facedevice facedevice, VisitorInfo visitorInfo) {
        String facestr= "";//
        try {
            facestr = URLEncoder.encode(visitorInfo.getPhoto(), "utf-8");
        }catch (Exception ex)
        {}
        String body = String.format(strCreateFace ,facedevice.getPwd(),visitorInfo.getCardNum(),visitorInfo.getCardNum(),facestr);
        return PostForm(facedevice , visitorInfo, urlCreateFace , body);
    }

    private boolean CreateUser(Facedevice facedevice, VisitorInfo visitorInfo) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",visitorInfo.getCardNum());
        jsonObject.put("idcardNum",visitorInfo.getCardNum());
        jsonObject.put("name",visitorInfo.getName());
        String json = jsonObject.toJSONString();
        try
        {
            json = URLEncoder.encode(json, "utf-8");
        }
        catch (Exception ex)
        {

        }
        String body = String.format(strCreatePerson ,json,facedevice.getPwd());
        return PostForm(facedevice , visitorInfo, urlCreatePerson , body);
    }


    /**
     * 删除人员
     * @param facedevice
     * @param visitorInfo
     * @return
     */
    private boolean DeleteUser(Facedevice facedevice, VisitorInfo visitorInfo) {
        String body = String.format(strDeleteUser ,visitorInfo.getCardNum(),facedevice.getPwd());
        return PostForm(facedevice , visitorInfo, urlDeleteUser , body);
    }

    private boolean PostForm(Facedevice facedevice , VisitorInfo visitorInfo , String url , String body)
    {
        logger.info("=====开始tdx HttpClient request:"+visitorInfo.getCardNum()+" =====");
        String urlTemp= "HttpClient://"+ facedevice.getIp()+url;
        String result = HttpClient.postForm(urlTemp , body);

        logger.info("=====tdx HttpClient response:"+result+"");
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.get("success").toString().equals("true");
    }

    /**
     * 查询人员
     * @param facedevice
     * @param visitorInfo
     * @return
     */
    private boolean FindUser(Facedevice facedevice , VisitorInfo visitorInfo)
    {
        String body = String.format(strFindPerson ,visitorInfo.getCardNum(),facedevice.getPwd());
        //{"msg":"不存在该人","success":false,"result":1} 失败
        //{"data":[{"createTime":1533711664200,"id":"10003","idcardNum":"","name":"6546"}],"result":1,"success":true} 成功
        return PostForm(facedevice , visitorInfo, urlFindPerson , body);
    }


    @Override
    public Map<String, String> delCard(Integer deviceId, VisitorInfo visitorInfo) {

        //删除人，自动删除人脸
        logger.info("=====开始删除人员:"+visitorInfo.getCardNum()+" =====");
        Map<String, String> resultMap = new HashMap<String, String>();
            if (visitorInfo.getCardNum() == null && visitorInfo.getCardNum().length() == 0) {
                logger.info("卡号不能为空！");
                resultMap.put("msg", "卡号不能为空！");
                resultMap.put("code", "9000");
                return resultMap;
            }
            Facedevice facedevice = facedeviceMapper.selectByPrimaryKey(deviceId);
            String body = String.format(strDeleteUser, visitorInfo.getCardNum(), facedevice.getPwd());
            //{"msg":"不存在该人","success":false,"result":1} 失败
            //{"data":[{"createTime":1533711664200,"id":"10003","idcardNum":"","name":"6546"}],"result":1,"success":true} 成功
            boolean bResult = PostForm(facedevice, visitorInfo, urlDeleteUser, body);
            if (bResult) {

                logger.info("删除人员成功！");
                resultMap.put("msg", "删除人员成功！");
                resultMap.put("code", "1000");
            } else {

                logger.info("删除人员失败！");
                resultMap.put("msg", "删除人员失败！");

                resultMap.put("code", "9000");
            }

        return resultMap;
    }

    @Override
    public Map<String, String> regisTopic(Facedevice device) throws UnsupportedEncodingException {
        //tdxCallBack  HttpClient://192.168.99.250:8081/device/aiface/hcOpenAlarm
        String tdxCallBack = "/aiface/tdxCallBack";

        Map<String, String> resultMap = new HashMap<String, String>();
        if(device.getCallurl().trim().length() == 0)
        {

            logger.info("启动订阅失败，地址为空！");
            resultMap.put("msg","启动订阅失败！");
            resultMap.put("code","9000");
        }else
        {
            tdxCallBack = device.getCallurl()+tdxCallBack;
            logger.info("订阅地址："+tdxCallBack);
            boolean result = PostFormDeviceREG(device , tdxCallBack);
            if(result)
            {
                device.setIsAlarm(1);
                facedeviceMapper.updateByPrimaryKeySelective(device);
                resultMap.put("msg","启动订阅成功！");
                resultMap.put("code","1000");

            }else
            {
                resultMap.put("msg","启动订阅失败！");
                resultMap.put("code","9000");
            }
        }
        return resultMap;
    }

    private boolean PostFormDeviceREG(Facedevice device, String tdxCallBack) throws UnsupportedEncodingException {
        String urlTemp= "HttpClient://"+ device.getIp()+urlRegisTopic;
        String body = String.format(strRegisTopic ,URLEncoder.encode(tdxCallBack, "utf-8") , device.getPwd() );

        String result = HttpClient.postForm(urlTemp , body);

        logger.info("=====tdx PostFormDevice HttpClient response:"+result+"");
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.get("success").toString().equals("true");
    }

    @Override
    public Map<String, String> deRegisTopic(Facedevice device) throws UnsupportedEncodingException {
        //取消订单，相当于给一个错误的地址
        String tdxCallBack = "HttpClient://www.baidu.com";

        Map<String, String> resultMap = new HashMap<String, String>();

            boolean result = PostFormDeviceREG(device , tdxCallBack);
            if(result)
            {

                device.setIsAlarm(0);
                facedeviceMapper.updateByPrimaryKeySelective(device);
                resultMap.put("msg","取消订阅成功！");
                resultMap.put("code","1000");

            }else
            {
                resultMap.put("msg","取消订阅失败！");
                resultMap.put("code","9000");
            }

        return resultMap;
    }

    @Override
    public void deleteMachineRecord(Facedevice device) {

        String urlTemp= "HttpClient://"+ device.getIp()+urlDeleteRecord;
        String body = strDeleteRecord;

        String result = HttpClient.postForm(urlTemp , body);

        logger.info("=====tdx 删除识别记录 HttpClient response:"+result+"");
        JSONObject jsonObject = JSONObject.parseObject(result);
    }


}

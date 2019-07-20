package com.qlhx.service.facedevice.realize.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qlhx.base.util.http.HTTP;
import com.qlhx.service.facedevice.realize.dao.FacedeviceMapper;
import com.qlhx.service.facedevice.realize.model.Facedevice;
import com.qlhx.service.facedevice.realize.model.VisitorInfo;
import com.qlhx.service.facedevice.realize.service.TDX8FaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by oldxue on 2019/2/14. TDX8人脸终端
 */
@Service
public class TDX8FaceServiceImpl implements TDX8FaceService {

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

    /**
     * 获取人脸设备序列号
     */
    private String urlgetDeviceKey = ":8090/getDeviceKey";

    Logger logger = LoggerFactory.getLogger(TDX8FaceServiceImpl.class);

    @Autowired
    FacedeviceMapper facedeviceMapper;


    @Override
    public Map<String, String> regCard(Facedevice facedevice, VisitorInfo visitorInfo) {
        logger.info("=====TDX8开始注册人员:"+visitorInfo.getCardNum()+"\t"+facedevice.getIp()+" =====");
        Map<String, String> resultMap = new HashMap<String, String>();
        if(visitorInfo.getCardNum()==null && visitorInfo.getCardNum().length()==0)
        {
            logger.info("TDX8卡号不能为空！");
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
                logger.info("TDX8删除人员失败:"+visitorInfo.getCardNum());
                resultMap.put("msg","删除人员失败:"+visitorInfo.getCardNum());
                resultMap.put("code","9000");
                return resultMap;
            }
        }

        //重新注册人
        dresult = CreateUser(facedevice , visitorInfo);
        if(!dresult)
        {
            logger.info("TDX8添加人员失败:"+visitorInfo.getCardNum());
            resultMap.put("msg","TDX8添加人员失败:"+visitorInfo.getCardNum());
            resultMap.put("code","9000");
            return resultMap;
        }

        //注册人脸
        dresult = CreateFace(facedevice , visitorInfo);
        if(!dresult)
        {
            logger.info("TDX8添加人脸失败:"+visitorInfo.getCardNum()+":"+facedevice.getIp());
            resultMap.put("msg","TDX8添加人脸失败:"+visitorInfo.getCardNum()+":"+facedevice.getIp());
            resultMap.put("code","9000");
            //注册人脸失败需要删除人员
            DeleteUser(facedevice , visitorInfo);

        }else {
            logger.info("TDX8添加人脸成功：" + visitorInfo.getCardNum()+":"+facedevice.getIp());
            resultMap.put("msg", "添加人脸成功:" + visitorInfo.getCardNum()+":"+facedevice.getIp());
            resultMap.put("code", "1000");
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
        String result = PostForm(facedevice , visitorInfo.getCardNum(), urlCreateFace , body);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.get("success").toString().equals("true");
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
            ex.printStackTrace();
            return false;
        }
        String body = String.format(strCreatePerson ,json,facedevice.getPwd());
        String result = PostForm(facedevice , visitorInfo.getCardNum(), urlCreatePerson , body);
        JSONObject jsonObject1 = JSONObject.parseObject(result);
        return jsonObject1.get("success").toString().equals("true");
    }


    /**
     * 删除人员
     * @param facedevice
     * @param visitorInfo
     * @return
     */
    private boolean DeleteUser(Facedevice facedevice, VisitorInfo visitorInfo) {
        String body = String.format(strDeleteUser ,visitorInfo.getCardNum(),facedevice.getPwd());
        String result = PostForm(facedevice , visitorInfo.getCardNum(), urlDeleteUser , body);
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.get("success").toString().equals("true");
    }

    private String PostForm(Facedevice facedevice , String cardnum , String url , String body)
    {
        logger.info("=====开始ks http request:"+cardnum+" =====");
        String urlTemp= "http://"+ facedevice.getIp()+url;
        String result = HTTP.postForm(urlTemp+"?"+body , body);
        logger.info("=====ks http response:"+result+"");
        //JSONObject jsonObject = JSONObject.parseObject(result);
        //return jsonObject.get("success").toString().equals("true");
        return  result;
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
        String result = PostForm(facedevice , visitorInfo.getCardNum(), urlFindPerson , body);
        JSONObject jsonObject = JSONObject.parseObject(result);
        Boolean bl = jsonObject.get("success").toString().equals("true");
        if(bl){
            bl = !jsonObject.get("data").toString().equals("[]");
            //bl = user.length()>3;
        }
        return bl;
    }


    @Override
    public Map<String, String> delCard(Integer deviceId, VisitorInfo visitorInfo) {
        //删除人，自动删除人脸
        logger.info("=====TDX8开始删除人员:"+visitorInfo.getCardNum()+" =====");
        Map<String, String> resultMap = new HashMap<String, String>();
        if(visitorInfo.getCardNum()==null && visitorInfo.getCardNum().length()==0)
        {
            logger.info("TDX8卡号不能为空！");
            resultMap.put("msg","卡号不能为空！");
            resultMap.put("code","9000");
            return resultMap;
        }
        Facedevice facedevice = facedeviceMapper.selectByPrimaryKey(deviceId);
        if (facedevice == null) {
            logger.info("TDX8设备不存在！" + deviceId.toString());
            resultMap.put("msg", "TDX8设备不存在！" + deviceId.toString());
            resultMap.put("code", "9000");
            return resultMap;
        }
        try {
            String body = String.format(strDeleteUser, visitorInfo.getCardNum(), facedevice.getPwd());
            //{"msg":"不存在该人","success":false,"result":1} 失败
            //{"data":[{"createTime":1533711664200,"id":"10003","idcardNum":"","name":"6546"}],"result":1,"success":true} 成功
            String result = PostForm(facedevice, visitorInfo.getCardNum(), urlDeleteUser, body);
            JSONObject jsonObject = JSONObject.parseObject(result);
            boolean bResult = jsonObject.get("success").toString().equals("true");
            if (bResult) {
                logger.info("TDX8删除人员成功！");
                resultMap.put("msg", "删除人员成功！");
                resultMap.put("code", "1000");
            } else {
                logger.info("TDX8删除人员失败！");
                resultMap.put("msg", "删除人员失败！");
                resultMap.put("code", "9000");
            }
        }catch (Exception ex){

            logger.info("TDX8删除人员异常！"+ex.getMessage());
            resultMap.put("msg", "TDX8删除人员异常！"+ex.getMessage());
            resultMap.put("code", "9000");
        }
        return resultMap;
    }

    @Override
    public Boolean autoDelCard(Facedevice facedevice, String cardnum) {
        //删除人，自动删除人脸
        logger.info("=====TDX8开始自动删除人员:"+cardnum+" =====");
        Map<String, String> resultMap = new HashMap<String, String>();
        if(cardnum==null && cardnum.length()==0)
        {
            logger.info("TDX8卡号不能为空！");
            return false;
        }
        if (facedevice == null) {
            logger.info("TDX8设备不存在！");
            return false;
        }
        try {
            String body = String.format(strDeleteUser, cardnum, facedevice.getPwd());
            String result = PostForm(facedevice, cardnum, urlDeleteUser, body);
            JSONObject jsonObject = JSONObject.parseObject(result);
            boolean bResult = jsonObject.get("success").toString().equals("true");
            if (bResult) {
                logger.info("TDX8删除人员成功！");
               return true;
            } else {
                logger.info("TDX8删除人员失败！");
                return false;
            }
        }catch (Exception ex){
            logger.info("TDX8删除人员异常！"+ex.getMessage());
            return false;
        }
    }

    //获取设备序列号，判断是否在线
    @Override
    public Map<String, String> getDeviceKey(Facedevice device) {
        //获取设备状态
        Map<String, String> resultMap = new HashMap<String, String>();
        if(device==null)
        {
            logger.info("设备信息不能为空！");
            resultMap.put("msg","设备信息不能为空！");
            resultMap.put("code","9000");
            return resultMap;
        }
        logger.info("=====TDX8获取设备状态:"+device.getIp()+" =====");
        String urlTemp= "http://"+ device.getIp()+urlgetDeviceKey;
        String result = HTTP.postForm(urlTemp , "");
        logger.info("=====TDX8获取设备状态 response:"+result+"");
        JSONObject jsonObject = JSONObject.parseObject(result);
        Boolean bl = jsonObject.get("success").toString().equals("true");
        if(bl)
        {
            resultMap.put("msg",jsonObject.get("data").toString());
            resultMap.put("code","1000");
        }else
        {
            resultMap.put("msg","TDX8获取设备状态失败！");
            resultMap.put("code","9000");
        }
        return resultMap;
    }

    //订阅识别成功回调
    @Override
    public Map<String, String> regisTopic(Facedevice device) throws UnsupportedEncodingException {
        //tdxCallBack  http://192.168.99.250:8081/device/aiface/hcOpenAlarm
        String tdxCallBack = "/aiface/TDX8CallBack";
        Map<String, String> resultMap = new HashMap<String, String>();
        if(device.getCallurl().trim().length() == 0)
        {
            logger.info("启动订阅失败，地址为空！");
            resultMap.put("msg","启动订阅失败，地址不能为空！");
            resultMap.put("code","9000");
        }else
        {
            tdxCallBack = device.getCallurl()+tdxCallBack;
            logger.info("订阅地址："+tdxCallBack);
            boolean result = PostFormDevice(device , tdxCallBack);
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

    //订阅回调请求方法
    private boolean PostFormDevice(Facedevice device, String tdxCallBack) throws UnsupportedEncodingException {
        String urlTemp= "http://"+ device.getIp()+urlRegisTopic;
        String body = String.format(strRegisTopic ,URLEncoder.encode(tdxCallBack, "utf-8") , device.getPwd() );
        String result = HTTP.postForm(urlTemp+"?"+body , body);

        logger.info("=====ks PostFormDevice http response:"+result+"");
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.get("success").toString().equals("true");
    }

    //取消订阅识别成功回调
    @Override
    public Map<String, String> deRegisTopic(Facedevice device) throws UnsupportedEncodingException {
        //取消订单，相当于给一个错误的地址
        String tdxCallBack = "http://www.baidu.com";
        Map<String, String> resultMap = new HashMap<String, String>();
            boolean result = PostFormDevice(device , tdxCallBack);
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
}

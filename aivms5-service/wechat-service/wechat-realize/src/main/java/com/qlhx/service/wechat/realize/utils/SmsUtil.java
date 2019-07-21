//package com.qlhx.service.wechat.realize.utils;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.qlhx.common.util.IConfig;
//import com.qlhx.model.SendsmsParam;
//import tools.HTTP;
//import tools.Security;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by yxn on 2018-04-21.
// */
//public class SmsUtil {
//
//
//	private final static String newSmsUrl = IConfig.getInstance().get("newSmsUrl");
//	private final static String smsAppkey = IConfig.getInstance().get("smsAppkey");
////    public static  void  main(String[] args){
////
////        List<Map<String,String>> list =new ArrayList<>();
////        Map<String,String> mp=new HashedMap();
////        mp.put("code","1024");
////        list.add(mp);
////        try {
////            SendMsg("13020092004","2018418",list);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
//    /**
//     * 发送短息
//     *
//     * @param phoneNum 电话号码
//     * @param code     模板编码
//     * @param value    内容
//     * @return
//     */
//    public static Map SendMsg(String phoneNum, String code, List<Map<String, String>> value ,String appkey ,String publicKey) throws Exception {
//        SendsmsParam sp = new SendsmsParam();
//        //加密明文数据
////        String appkey = "acbd8e0b4257cf5b1680ce5f6d54ab28";
////        String publicKey ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCM9iFERMpq48sOTCqj1dlGtOZGxTs51lYve8SotLMQKplKw+ieqrWQTbNLTj6jE3WWBpG+91YehYdLI2AMuFF8AnVSBS5z4E2Mfg5zIHOj0+hmTtp642ceGJgBz3cw/T8xaYMNlvtjT1drpXvVcm9avu7CSVxOedMH/Lel3ydleQIDAQAB";
//        sp.setAppkey(appkey);
//        sp.setMd5("");
//        sp.setSafe("");
//        sp.setCiphertext("");
//        sp.setPhone(phoneNum);
//        sp.setNum(code);
//        //设置消息内容
//        sp.setList(value);
//        //获取明文字符串用于加密
//        String source = JSONObject.toJSONString(sp);
//        // des加密密钥
//        String ss = Security.getSecurityString();
//        String sj = Security.getSecurityString();
//        // 加密
//        String before = source.replaceAll(" ", "").replaceAll("\n", "")
//                .replaceAll("	", "");
//
//        System.out.println("加密前的串：" + before);
//
//        String st = Security.desEncode(Security.desEncode(before, ss), sj);
//        sp.setCiphertext(st);
//        // des密钥进行rsa加密
//        String rsast = Security.encrypt(ss + "" + sj, publicKey);
//        sp.setSafe(rsast);
//        String md5 = Security.md5_16(st);
//        sp.setMd5(md5);
//        String sensmsurl = String.format("%s/smshandle/sendsms", IConfig.getInstance().get("smsUrl"));
//        String res = HTTP.post(sensmsurl, JSONObject.toJSONString(sp));
//        System.out.print(res);
//        Map resultMap = JSON.parseObject(res, Map.class);
//
//
//        return resultMap;
//    }
//
//    public static String SendMsg2(String content ,String phone)
//    {
//    	Map<String,String> paraMap = new HashMap<String,String>();
//    	paraMap.put("appkey", smsAppkey);
//    	paraMap.put("content", content);
//    	paraMap.put("num", "tongyong");
//    	paraMap.put("phone", phone);
//    	String res = HTTP.post(newSmsUrl, JSONObject.toJSONString(paraMap));
////    	String res = HTTP.post("http://xjucan.com/sms/smshandle/sendsmsnew", JSONObject.toJSONString(paraMap));
//    	return res;
//    }
//
//    public static void main(String arg[])
//    {
//    	SmsUtil.SendMsg2("【钱林恒兴】林XX您好，您的朋友王XX（150********）邀请您于****年**月**日**时**分（时间）访问北京钱林恒兴。到访时您可以在访客机上用二维码打印访问凭条，二维码详见", "18511893051");
//    }
//}

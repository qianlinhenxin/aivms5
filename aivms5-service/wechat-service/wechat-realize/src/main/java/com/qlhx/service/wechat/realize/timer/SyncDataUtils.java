///**
// * <p>
// * Title:
// * </p>
// * <p>
// * Description:
// * </p>
// * <p>
// * Company:北京钱林恒兴科技股份有限公司
// * </p>
// *
// * @author 余佳建
// * @date 2017年12月1日 下午3:22:45
// */
//package com.qlhx.service.wechat.realize.timer;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.qlhx.base.util.IConfig;
//import com.qlhx.common.VideoCallBackCache;
//import com.qlhx.common.util.IConfig;
//import com.qlhx.dao.SyncDataMethod;
//import com.qlhx.service.wechat.realize.dao.SyncDataMethod;
//import com.qlhx.service.wechat.realize.utils.SpringContextUtil;
//import com.qlhx.service.wechat.realize.utils.SyncDataManager;
//import com.qlhx.utils.SyncDataManager;
//import com.qlhx.wechat.common.util.HardWareUtils;
//import com.qlhx.wechat.common.util.SpringContextUtil;
//import org.apache.ibatis.session.SqlSession;
//import org.mybatis.spring.SqlSessionTemplate;
//import tools.*;
//
//import java.io.File;
//import java.lang.reflect.Field;
//import java.util.List;
//import java.util.Map;
//
///**
// * <p>
// * Title:
// * </p>
// * <p>
// * Description:
// * </p>
// * <p>
// * Company:北京钱林恒兴科技股份有限公司
// * </p>
// *
// * @author 余佳建
// * @date 2017年12月1日 下午3:22:45
// */
////@Component
//public class SyncDataUtils {
//
//    //private final SyncDataManager syncdataConfig = SpringContextUtil.getBean("syncdataManager", SyncDataManager.class);
//    private final SyncDataManager syncdataConfig = new SyncDataManager();
//
//    // 图片上传url
//    private String imguploadurl = IConfig.get("imguploadurl");
//
//    // 数据上传url
//    private String dataUploadUrl = IConfig.get("data.upload.url");
//    /**
//     * 实体包名
//     */
//    //private String modelName = "com.qlhx.model.";
//
//    /**
//     * 数据持久层包名
//     */
//    private String daoName = "com.qlhx.dao.";
//
//    String areaCode=IConfig.getInstance().get("companycode");
//    /**
//     * 数据同步
//     *
//     * @return int 大于0成功
//     * @throws Exception
//     */
//    @SuppressWarnings({"static-access", "unchecked"})
//    public void syncData() throws Exception {
//
//       // System.out.println("数据上传配制：" + JSONObject.toJSONString(syncdataConfig));
//        Map<String, Map<String, Map<String, String>>> map = syncdataConfig.getConfigitem();
//        for (Map.Entry<String, Map<String, Map<String, String>>> entry : map
//                .entrySet()) {
//            String className = entry.getKey();// 实体类名
//            String beanName = daoName + className + "Mapper";// dao类完整路径名称
//            SqlSessionTemplate sdm = SpringContextUtil.getApplicationContext()
//                    .getBean("sqlSessionTemplate", SqlSessionTemplate.class);
//            SqlSession sqlsession = sdm.getSqlSessionFactory().openSession();
//            Class<?> c = Class.forName(beanName);
//            SyncDataMethod method = (SyncDataMethod) sqlsession.getMapper(c);
//            // 获取同步数据
//            List<Object> list = method.syncData(syncdataConfig.getNum());
//            if (list == null || (list != null && list.size() == 0)) {
//                System.out.println("未获取到数据" + className);
//                sqlsession.close();
//                continue;
//            }
//            String picredieskey ="";
//            // 处理同步数据
//            for (Object object : list) {
//                Class cl = (Class) object.getClass();
//                Field[] fs = cl.getDeclaredFields();
//                for (int i = 0; i < fs.length; i++) {
//                    Field f = fs[i];
//                    f.setAccessible(true); // 设置属性可以访问
//                    Map<String, Map<String, String>> map2 = entry.getValue();
//                    for (Map.Entry<String, Map<String, String>> entry2 : map2
//                            .entrySet()) {
//                        if (entry2.getKey().equals(f.getName())) {
//                            Map<String, String> map3 = entry2.getValue();
//                            for (Map.Entry<String, String> entry3 : map3
//                                    .entrySet()) {
//                                if (entry3.getKey().equals("p")) {// 处理图片
//                                    // 上传图片得到图片标识，然后将标识更新到该字段
//                                    // 获取图片
//                                    String pic = null;
//                                    try {
//                                        //redeis 图片key
//                                        picredieskey = "" + f.get(object);
//
//                                        if (!VideoCallBackCache.exists(picredieskey)) {
//                                            //如果本地缓存不存在图片说明没有上传过
//                                            pic = System.getProperty("rms")
//                                                    .replace("rms", "")
//                                                    + picredieskey;
//
//                                            //System.out.println("图片绝对路径：" + pic);
//                                            //System.out.println("图片上传url："  + imguploadurl);
//                                            if (new File(pic).exists()) {// 图片存在
//                                                System.out.println("图片存在");
//                                                String[] piclist = pic.split("\\.");
//                                                String body = "data:image/"
//                                                        + piclist[piclist.length - 1]
//                                                        + "," + Img.img2base64(pic);
//                                                String result = HTTP.post(
//                                                        imguploadurl+"?token="+ HardWareUtils.GetLocalAuthCode(), body);
//                                                if (result != null) {
//                                                    JSONObject json = JSONObject
//                                                            .parseObject(result);
//                                                    if (json.getIntValue("code") == 0) {// 成功
//                                                        String picflag = json
//                                                                .getString("content");
//                                                        f.set(object, picflag);
//                                                        //再判断一次本地Redies中是否有此张图片的记录
//                                                        if (!VideoCallBackCache.exists(picredieskey)) {
//                                                            VideoCallBackCache.set(picredieskey, picflag);
//                                                        }
//                                                    }
//                                                }
//                                            } else {
//                                                System.out.println("图片不存在");
//                                                f.set(object, null);
//                                            }
//
//                                        } else {
////                                            已经上传图片并返回平台图片地址
//                                            String picurl = VCache.get(picredieskey);
//                                            f.set(object, picurl);
//                                        }
//                                    } catch (Exception e) {
//                                        // TODO Auto-generated catch block
//                                        System.out.println("图片不存在");
//                                        f.set(object, null);
//                                        e.printStackTrace();
//                                    }
//                                } else if (entry3.getKey().equals("r")) {// 替换内容
//                                    f.set(object, entry3.getValue());
//                                }
//                            }
//                        }
//                    }
//
//                }
//            }
//            // 数据上传
//            String liststr = JSONArray
//                    .toJSONString(list, SerializerFeature.WriteMapNullValue)
//                    .replace("\t", "").replace("\n", "").replace("\r", "");
//            String data = Security.getBase64String(liststr);// 数据
//            // String data = "123";// 数据
//            //System.out.println("base64_json:" + data);
//            //System.out.println("base64_decode_json:"   + Security.getBase64Decode(data));
//
//            //System.out.println("jsonSize:" + data.length());
//            long timestamp = System.currentTimeMillis();// 时间戳
//            String sign = MD5.getMD5_32(data + className + timestamp);
//
//            JSONObject jsonData = new JSONObject();
//            jsonData.put("data", data);
//            jsonData.put("timestamp", timestamp);
//            jsonData.put("className", className);
//            jsonData.put("sign", sign);
//           // System.out.println("jsonData:" + jsonData.toJSONString());
//            // 上传数据
//            String result = HTTP.post(dataUploadUrl+"/dataSync?token="+ HardWareUtils.GetLocalAuthCode()+"&areaCode="+areaCode, jsonData.toJSONString());
//            JSONObject rejson = JSONObject.parseObject(result);
//            //System.out.println(className + "--同步返回:" + rejson);
//            if (result != null && !"".equals(result) && rejson != null
//                    && rejson.getIntValue("code") == 1000) {// 同步成功
//
//                    //如果上传成功则删除之前图片的记录
//                    if(picredieskey!=""){
//                        if(VideoCallBackCache.exists(picredieskey)){
//                            VideoCallBackCache.delSetByKey(picredieskey,null);
//                        }
//                    }
//                String idlist = "";
//                // 修改同步数据时间
//                for (Object object : list) {
//                    Class cl = (Class) object.getClass();
//                    Field[] fs = cl.getDeclaredFields();
//                    Long id = 0l;
//                    String time = DateTime.getNow();
//                    for (int i = 0; i < fs.length; i++) {
//                        Field f = fs[i];
//                        f.setAccessible(true); // 设置属性可以访问
//                        // if ("id".equals(f.getName())) {
//                        // idlist += f.get(object).toString() + ",";
//                        // }
//                        if ("tabTime".equals(f.getName())
//                                || "tabtime".equals(f.getName())
//                                || "uploadTime".equals(f.getName())
//                                || "uploadtime".equals(f.getName())) {
//                            f.set(object, time);
//                        }
//                    }
//                    if (method.updateUploadStatus(object) > 0) {// 修改状态成功
//                        System.out.println("同步数据状态修改成功！");
//                    } else {
//                        System.out.println("同步数据状态修改失败！");
//                    }
//                }
//            }
//            sqlsession.close();// 关闭sqlsession
//        }
//
//    }
//
//}

package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qlhx.service.wechat.realize.dao.WechatPreapplygoupDetailMapper;
import com.qlhx.service.wechat.realize.dao.WechatPreapplygoupMapper;
import com.qlhx.service.wechat.realize.dao.WechatWxuserMapper;
import com.qlhx.service.wechat.realize.service.WechatPreapplygoupService;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import tools.Guid;
import tools.PinYinUtil;

import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/4 17:35
 * @description desc: ServiceiMPL
 */
@Service
@PropertySource("classpath:config.properties")
public class WechatPreapplygoupServiceImpl  implements WechatPreapplygoupService {



    @Autowired
    private RestTemplate rest;

    @Autowired
    private WechatPreapplygoupMapper wechatPreapplygoupMapper;

    @Autowired
    private WechatWxuserMapper wechatWxuserMapper;


    @Autowired
    private WechatPreapplygoupDetailMapper wechatPreapplygoupDetailMapper;



    @Value("${companycode}")
    private String companycode;

    @Value("${hddeviceurl}")
    public String hddeviceurl;// 设备接口地址

    @Value("${wxyytype}")
    public Integer wxyytype;  //type


    Logger logger = LoggerFactory.getLogger(WechatPreapplygoupService.class);
    public String GetMaxWxYuYueTabTime() {
        return null;
    }

    public void SyncWxApplyList(List<WxApplygoupBo> wxapplylist) {
        try {
            if (wxapplylist != null && wxapplylist.size() > 0) {
                for (WxApplygoupBo item : wxapplylist) {
                    try {
                        SaveWxApply(item, 1, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("没有微信预约记录！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("同步微信预约记录异常" + ex.getMessage());
        }
    }

    public Integer SaveWxApply(WxApplygoupBo wxApplygoupBo) throws Exception {
        SaveWxApply(wxApplygoupBo, 0, 1);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer SaveWxApply(WxApplygoupBo wxApplygoupBo, Integer type, Integer status) throws Exception {
        WechatPreapplygoup preapplygoup =  wxApplygoupBo;
        if (preapplygoup != null) {
            String syncid = preapplygoup.getSyncid();
            if (!StringUtils.isBlank(syncid)) {
                //查询系统中是否存在
                WechatPreapplygoup preapplygoup1 = wechatPreapplygoupMapper.selectBySyncId(syncid);
                if (preapplygoup1 != null) {
                    //已经同步过了
                    logger.info("系统中已经存在此条记录" + syncid);
                    return 1;
                }
            }
            preapplygoup.setCompanycode(companycode);
            String guid = Guid.CreateGUID();
            //设置唯一id
            if (StringUtils.isBlank(preapplygoup.getSyncid())) {
                preapplygoup.setSyncid(guid);
            } else {
                guid = preapplygoup.getSyncid();
            }
            if (type == 0) {
                preapplygoup.setStatus(2);
            }
            preapplygoup.setType(type);
//            preapplygoup.setIsteam(1);
            preapplygoup.setCreatetime(DateUtil.stringToDate(new Date().toString(), "yyyy-MM-dd HH:mm:ss"));
            if (preapplygoup.getTabtime() != null) {
                Date timestr = preapplygoup.getTabtime();
                if (timestr.toString().contains("-")) {
                    preapplygoup.setUploadtime(timestr);
                    preapplygoup.setTabtime(timestr);
                } /*else {
                    Long tt = Long.parseLong(preapplygoup.getTabtime());
                    String time = transferLongToDate("yyyy-MM-dd HH:mm:ss", tt);
                    preapplygoup.setUploadtime(time);
                    preapplygoup.setTabtime(time);
                }*/

            }
            preapplygoup.setId(null);
            //主键自动生成
            wechatPreapplygoupMapper.insertSelective(preapplygoup);
            if (wxApplygoupBo.getPreapplygoupdetail() != null && wxApplygoupBo.getPreapplygoupdetail().size() > 0) {
                for (WechatPreapplygoupDetail item : wxApplygoupBo.getPreapplygoupdetail()) {
                    WechatWxuser localuser = wechatWxuserMapper.GetWxUserByTel(item.getUtel());
                    if (localuser == null) {
                        WechatWxuser wxuser=item.getWxuser();
                        if(wxuser==null){
                            //没有微信用户，从详情里取
                            wxuser=new WechatWxuser();
                            wxuser.setUname(item.getUname());
                            wxuser.setUtel(item.getUtel());
                        }else{
                            WechatWxuser wechatWxuser = wechatWxuserMapper.selectByPrimaryKey(wxuser.getId());
                            if(wechatWxuser != null){
                                wxuser.setId(null);
                            }
                        }
                        wechatWxuserMapper.insertSelective(wxuser);

                    }else {
                        //更新微信用户
                        WechatWxuser wxuser=item.getWxuser();
                        if(wxuser==null){
                            wxuser=new WechatWxuser();
                            wxuser.setUname(item.getUname());
                            wxuser.setUtel(item.getUtel());
                        }
                        wxuser.setId(localuser.getId());
                        wechatWxuserMapper.updateByPrimaryKeySelective(wxuser);
                        logger.info(String.format("更新微信用户 手机号 %s 姓名 %s ", item.getUtel(),item.getUname()));
                    }
                    WechatWxuser user = wechatWxuserMapper.GetWxUserByTel(item.getUtel());
                    //生成一条预约流水
                    if (StringUtils.isBlank(item.getOpenid())) {

                        if (user != null) {
                            item.setOpenid(user.getOpenid());
                            item.setJointime(new Date());
                        }
                    }
                    if (StringUtils.isBlank(item.getSyncid())) {
                        item.setSyncid(guid);
                    }

                    if (StringUtils.isBlank(item.getApplyid())) {
                        //本表标识
                        String applyid = Guid.CreateGUID();
                        item.setApplyid(applyid);
                    }
                    item.setId(null);
                    item.setType(type);
                    item.setRstatus(status);
                    item.setCompanycode(companycode);
                    BaseAccessRecord access = SaveVisistorAcess(preapplygoup, item, user);
                    item.setAccessrecordid(access.getId());
                    //生成预约二维码
                    item.setCardnum(access.getRcode());
                    //生成一条访客记录
                    wechatPreapplygoupDetailMapper.insertSelective(item);
                    if (user != null) {
                        String carnum = user.getCarnum();
                        if (StringUtils.isNotBlank(carnum)) {
                            //下发车牌
                            Map<String, String> info = new HashMap<>();
                            info.put("carNo", carnum);
                            info.put("startDate", preapplygoup.getStarttime().toString());
                            info.put("endDate", preapplygoup.getEndtime().toString());
                            SendCarNo(info);
                        }
                    }
//                    try {
//                        visitorServer.AddWxyyStatus(access, 3);
//                    } catch (Exception ex) {
//                        logger.error("插入预约状态异常" + ex.getMessage());
//                    }

                }
            } else {
                throw new Exception("参数错误");
            }

        }
        return 1;
    }


    public Result SendCarNo(Map<String, String> carinfo) {
        Result result = null;
        String serverurl = String.format("%s/carDevice/sendCarNo", hddeviceurl);
        try {
            result = SendCmd(serverurl,carinfo);
            if (result != null) {
                logger.info("下发车牌返回" + JSON.toJSONString(result));
            }
        } catch (IOException e) {
            e.printStackTrace();
            result = new Result();
            result.setCode(9000);
            result.setMsg("下发车牌异常");
        }
        return result;
    }

    private BaseAccessRecord SaveVisistorAcess(WechatPreapplygoup preapplygoup, WechatPreapplygoupDetail detail, WechatWxuser wxuser) throws Exception {
        //查询访客
        BaseVisitor visitor = null;
        String idnum = null;
        if (wxuser != null) {
            idnum = wxuser.getIdnum();
            try {
                // 根据访客身份证号查询访客是否存在
                if (!StringUtils.isBlank(idnum)) {
                    visitor = rest.getForObject("http://service-base/visitor/findVisitorByIdNum/" + wxuser.getIdnum(), BaseVisitor.class);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (visitor == null) {
            //通过手机号查询
            visitor = rest.getForObject("http://service-base/visitor/findVisitorByIdPhone/" + detail.getUtel(), BaseVisitor.class);
            if (visitor != null) {
                if (!StringUtils.isBlank(idnum)) {
                    //身份证不为空手机号能查到访客更新访客身份证信息
                    visitor.setIdnum(wxuser.getIdnum());
                    rest.postForObject("http://service-base/visitor/updateByPrimaryKey/" ,visitor, Integer.class);
                    //baseVisitorMapper.updateByPrimaryKey(visitor);
                }
            }
        }
        if (visitor == null) {
            //系统中不存在此手机号的访客
            visitor = new BaseVisitor();
            if (wxuser != null) {
                if (wxuser.getUsex() != null) {
                    visitor.setSex(wxuser.getUsex() == 0 ? "女" : "男");
                }
                if (wxuser.getUtel() != null)
                    visitor.setPhone(wxuser.getUtel());
                if (wxuser.getUname() != null) {
                    visitor.setName(wxuser.getUname());
                } else {
                    visitor.setName(detail.getUname());
                }
                if (StringUtils.isBlank(wxuser.getIdnum())) {
                    visitor.setIdnum(wxuser.getUtel());
                } else {
                    visitor.setIdnum(wxuser.getIdnum());
                }

            } else {
                visitor.setPhone(detail.getUtel());
                visitor.setName(detail.getUname());
                visitor.setIdnum(detail.getUtel());
            }
            try {
                visitor.setPinYin(PinYinUtil.getFirstSpell(visitor.getName()));
            } catch (Exception ex) {
                logger.error("保存访客信息名字转拼音失败" + visitor.getName() + "____" + ex.getMessage());
            }
            rest.postForObject("http://service-base/visitor/insertSelective",visitor,Integer.class);
            visitor.setId(visitor.getId());
        }


        BaseAccessRecord access = new BaseAccessRecord();


        access.setReasons(preapplygoup.getReson());
        access.setStarttime(preapplygoup.getStarttime().toString());
        access.setEndtime(preapplygoup.getEndtime().toString());
        access.setVid(visitor.getId());
        access.setUid(preapplygoup.getUid());
        if (StringUtils.isNotBlank(preapplygoup.getUnit())) {
            //单位
            access.setUnit(preapplygoup.getUnit());
        }

        if (wxyytype == 1 || wxyytype == 2) {
            //需要在访客机上验证，状态改为未登记状态
            access.setStatus(3);
        } else {
            //状态改为已登记状态
            access.setStatus(0);
        }
        access.setType(1);//预约
        access.setVisitor(visitor);

        BaseAccessRecord record = updateAccessRecord(access);

        //如果携带车牌号同时做记录
        if (wxuser != null) {
            if (StringUtils.isNotBlank(wxuser.getCarnum())) {
                //车牌号不为空 将更新访客车牌号
                BaseVisitorCar visitorCar = new BaseVisitorCar();
                visitorCar.setArid(record.getId());
                visitorCar.setCardnum(wxuser.getCarnum());
                Integer id = record.getId();
                // 根据访客记录id查询本次访客车辆信息
                BaseVisitorCar vCar = rest.getForObject("http://service-base/baseVisitorCar/findCarByArId/"+id,BaseVisitorCar.class);
                //BaseVisitorCar vCar = baseVisitorCarMapper.findCarByArId(record.getId());
                if (vCar != null) {
                    record.getvCar().setId(vCar.getId());
                    rest.postForObject("http://service-base/baseVisitorCar/updateByPrimaryKeySelective",visitorCar,Integer.class);
                    //baseVisitorCarMapper.updateByPrimaryKeySelective(visitorCar);
                } else {
                    rest.postForObject("http://service-base/baseVisitorCar/insertSelective",visitorCar,Integer.class);
                    // baseVisitorCarMapper.insertSelective(visitorCar);
                }
            }
        }
        return record;
    }

    public BaseAccessRecord updateAccessRecord(BaseAccessRecord record) throws Exception {
        Date time =  DateUtil.stringToDate(new Date().toString(), "yyyy-MM-dd HH:mm:ss");
        record.setCreatetime(time);
        if(record  != null && record.getStarttime() != null || !record.getStarttime().equals("null")) {//处理时间格式

            record.setStarttime(DateUtil.convertDate(record.getStarttime()));
            record.setEndtime(DateUtil.convertDate(record.getEndtime()));
        }
        Integer rowid = -1;
        Integer i = rest.postForObject("http://service-base/accessRecord/insertSelective", record, Integer.class);
        if (/*baseAccessRecordMapper.insertSelective(record) */i > 0) {
            rowid = i;
            record.setId(i);
            String vcardnum = IConfig.get("vcardnum");
            int num = 5;
            if ("1".equals(vcardnum)) {
                //十位卡号
                num = 7;
            }
            String code = GenericCodeUtils.GetRandomPre() + GenericCodeUtils.autoGenericCode(rowid, num);
            record.setRcode(code);
            // 添加虚拟卡号
            if (record.getCardnum() == null
                    || "".equals(record.getCardnum())) {
                record.setCardnum(code);
            }
            logger.debug(JSONObject.toJSONString(record));
            rest.postForObject("http://service-base/accessRecord/updateByPrimaryKeySelective", record, Integer.class);
            // baseAccessRecordMapper.updateByPrimaryKeySelective(record);
            return record;
        } else {
            throw new IllegalArgumentException();
        }
    }


    /**
     * 发送指令
     *
     * @param url
     * @param info
     * @return
     */
    public Result SendCmd(String url, Object info) throws IOException {
        Result resultmap = new Result();
        logger.debug(String.format("执行设备层指令 url:%s 参数:%s", url, JSONObject.toJSON(info)));

        //1.创建客户端访问服务器的httpclient对象   打开浏览器
        HttpClient httpclient = new DefaultHttpClient();
        //2.以请求的连接地址创建get请求对象     浏览器中输入网址
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("cache-control", "no-cache");
        // 构建消息实体

        HttpParams params = httpclient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 1000 * 15);
        HttpConnectionParams.setSoTimeout(params, 1000 * 15);
        if (info == null) {
            info = new Object();
        }

        StringEntity entity = new StringEntity(JSONObject.toJSONString(info), Charset.forName("UTF-8"));
        /*String strJson=JSONObject.toJSONString(info);*/
        entity.setContentEncoding("UTF-8");
        // 发送Json格式的数据请求
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        //3.向服务器端发送请求 并且获取响应对象  浏览器中输入网址点击回车
        HttpResponse response = null;

        response = httpclient.execute(httpPost);

        //4.获取响应对象中的响应码
        StatusLine statusLine = response.getStatusLine();//获取请求对象中的响应行对象
        int responseCode = statusLine.getStatusCode();//从状态行中获取状态码

        //  System.out.println(responseCode);
        String json = null;

        json = EntityUtils.toString(response.getEntity(), "utf-8");
        logger.debug(String.format("执行设备层指令返回 :%s", json));

        if (json != null) {
            JSONObject jsonresult = JSON.parseObject(json);
            if (null != jsonresult) {
                String status = jsonresult.get("code").toString();
                if ("1000".equals(status.toLowerCase())) {
                    resultmap.setCode(0);
                    resultmap.setMsg("成功");
                    resultmap.setContent(jsonresult.get("content"));
                } else {
                    resultmap.setCode(9000);
                    resultmap.setMsg("" + jsonresult.get("msg"));
                    if (jsonresult.get("content") != null) {
                        resultmap.setContent(jsonresult.get("content"));
                    }
                }
            }
        }
        return resultmap;
    }


    public static void main(String[] args) {
        Date time =new Date("Wed Jul 03 23:59:00 CST 2019");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String timeFormat = sdf.format(time);
        System.out.println(timeFormat);
    }

}

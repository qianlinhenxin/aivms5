package com.qlhx.service.wechat.realize.timer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qlhx.common.util.HTTP;
import com.qlhx.model.WxApplygoupBo;
import com.qlhx.service.WechatPreapplygoupService;
import com.qlhx.service.wechat.realize.model.WxApplygoupBo;
import com.qlhx.service.wechat.realize.service.WechatPreapplygoupService;
import com.qlhx.wechat.common.util.HardWareUtils;
import io.micrometer.core.instrument.util.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tools.HTTP;

import java.util.List;


/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/4 15:54
 * @description desc: 微信预约同步接口
 */
@Lazy(false)
@Component
@EnableScheduling
@PropertySource("classpath:config.properties")
public class TimerTask {

    @Autowired
    private WechatPreapplygoupService preapplygoupService;

    Logger logger = LoggerFactory.getLogger(TimerTask.class);

    @Value("${syncwxyy.url}")
    private String syncwxyyurl;
    @Value("${syncwxyy.switch}")
    private boolean syncwxyyswitch ;
    @Value("${companycode}")
    public String companycode;// 公司标识编码

    @Value("${isopensenddata}")
    boolean issend;

    /**
     * 自动同步微信预约记录
     */
    @Scheduled(cron ="${syncapply.time}")
    private void syncwxyuyue() {
        if (syncwxyyswitch) {
            try {
                // 获取微信预约记录
                String maxWxYuYueTabTime = preapplygoupService.GetMaxWxYuYueTabTime();
                String time = "2001-01-01 00:00:00";
                if (!StringUtils.isBlank(maxWxYuYueTabTime)) {
                    time = maxWxYuYueTabTime;
                }
                String syncwxapplystr = HTTP.post(String.format("%s/sync/downWxApply?token="+ HardWareUtils.GetLocalAuthCode()+"&areaCode="+companycode, syncwxyyurl), "{\"companyCode\":\"" + companycode + "\",\"downTime\":\"" + time + "\"}");
                System.out.println("maxwxtime:" + time);
                System.out.println("获取微信预约记录：" + syncwxapplystr);
                if (syncwxapplystr != null) {// 获取未同步的微信用户列表
                    JSONObject json = JSONObject.parseObject(syncwxapplystr);
                    if (json.getIntValue("code") == 1000) {// 成功
                        // 将字符串转换成实体对象
                        List<WxApplygoupBo> bls = JSONArray.parseArray(
                                json.getString("content"), WxApplygoupBo.class);
                        try {
                            preapplygoupService.SyncWxApplyList(bls);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("获取微信用户同步数据失败！");
                    }

                } else {
                    System.out.println("没有可同步的微信用户！");
                }

            } catch (Exception e) {
                logger.error("同步微信预约记录发生错误", e);
                e.printStackTrace();
            }
            finally {

            }
        } else {
            logger.info("没有开启同步微信用户开关 ！");
        }

    }

    @Scheduled(cron = "${datatoplatformDataTime}")
    private void senddata() {
        if (issend) {
            logger.info("开始执行数据同步任务！");
            try {
                new SyncDataUtils().syncData();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info("同步任务执行完成！");
        } else {
            logger.info("没有开启数据同步开关 ！");
        }
    }



}

package com.qlhx.service.wechat.realize.service.impl;

import com.qlhx.service.wechat.realize.dao.USysLogsMapper;
import com.qlhx.service.wechat.realize.service.TimerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * <p>
 * Title:定时任务相关服务接口
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: 北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author alaskayu
 * @date 2016年12月7日 上午10:48:26
 */
@Service
public class TimerServiceImpl implements TimerService {

    private static final Logger LOGGER = LoggerFactory
	    .getLogger(TimerServiceImpl.class);

    @Autowired
    USysLogsMapper sysMapper;

    @Override
    public Integer updateDB(String updateSql) throws Exception {
	// TODO Auto-generated method stub
	return 0;
    }

}

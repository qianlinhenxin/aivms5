package com.qlhx.service.base.realize.service.impl;

import com.qlhx.service.base.realize.mapper.TestMapper;
import com.qlhx.service.base.realize.model.Test;
import com.qlhx.service.base.realize.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/21 12:10
 * @Description desc:
 */
@Service
public class TestServiceImpl implements TestService {

    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

    @Autowired
    private TestMapper testMapper;

    public List<Test> findByParams(Map<String, Object> params) {
        List<Test> testList = testMapper.findByParams(params);
        /**
         * 日志记录
         */
        logger.info("findByParams -------》 params:{}",params);
        return testList;
    }
}

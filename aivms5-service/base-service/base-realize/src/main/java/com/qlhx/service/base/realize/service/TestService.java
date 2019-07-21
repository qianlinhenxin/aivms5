package com.qlhx.service.base.realize.service;

import com.qlhx.service.base.realize.model.Test;

import java.util.List;
import java.util.Map;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/21 12:09
 * @Description desc:
 */
public interface TestService {

    /**
     * 根据参数查询
     * @param params
     * @return
     */
    List<Test> findByParams(Map<String,Object> params);

}

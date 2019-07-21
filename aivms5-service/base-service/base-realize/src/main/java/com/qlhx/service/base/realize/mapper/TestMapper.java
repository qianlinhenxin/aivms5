package com.qlhx.service.base.realize.mapper;

import com.qlhx.service.base.realize.model.Test;

import java.util.List;
import java.util.Map;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/21 12:12
 * @Description desc:
 */
public interface TestMapper {

    List<Test> findByParams(Map<String,Object> params);

}

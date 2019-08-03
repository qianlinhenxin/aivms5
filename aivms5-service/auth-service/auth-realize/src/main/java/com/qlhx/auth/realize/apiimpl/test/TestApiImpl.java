package com.qlhx.auth.realize.apiimpl.test;

import com.qlhx.auth.api.api.test.TestApi;
import com.qlhx.auth.api.vo.test.TestVO;
import com.qlhx.auth.realize.bean.Test;
import com.qlhx.base.apiimpl.BaseApiImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/8/3 15:10
 * @Description desc:
 */
@RestController
@RequestMapping("/test")
public class TestApiImpl extends BaseApiImpl<Test, TestVO> implements TestApi {


}

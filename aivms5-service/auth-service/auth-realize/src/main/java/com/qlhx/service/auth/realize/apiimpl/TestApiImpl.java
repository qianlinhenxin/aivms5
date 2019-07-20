package com.qlhx.service.auth.realize.apiimpl;

import com.qlhx.base.api.impl.BaseApiImpl;
import com.qlhx.service.auth.api.api.TestApi;
import com.qlhx.service.auth.api.vo.TestVO;
import com.qlhx.base.util.web.ApiResponse;
import com.qlhx.service.auth.realize.bean.Test;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/17 21:09
 * @Description desc:
 */
@RestController
@RequestMapping(value = "/url-auth")
public class TestApiImpl extends BaseApiImpl<Test,TestVO> implements TestApi {

    @RequestMapping(value ="/test",method = RequestMethod.POST)
    public ApiResponse<TestVO> test(@RequestBody TestVO vo) {
        return null;
    }
}

package com.qlhx.web.auth.controller;

import com.qlhx.base.util.json.RequestData;
import com.qlhx.base.util.web.ApiResponse;
import com.qlhx.base.util.web.WebResponse;
import com.qlhx.service.auth.api.api.TestApi;
import com.qlhx.service.auth.api.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/17 21:23
 * @Description desc:
 */
@RestController
@RequestMapping("/url-auth")
public class TestController extends BaseController {

    @Autowired
    private TestApi testApi;

    @RequestMapping(value = "/find-page",method = RequestMethod.POST)
    public WebResponse<TestVO> findByPage(@RequestBody RequestData<TestVO> voRequestData) {
        WebResponse<TestVO> webResponse = new  WebResponse<>();
        TestVO vo = voRequestData.getReqData();
        ApiResponse<TestVO> apiResponse = testApi.test(vo);
        this.setResponse(apiResponse,webResponse);
        return webResponse;
    }

}

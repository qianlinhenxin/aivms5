package com.qlhx.web.auth.controller.test;

import com.qhlx.core.bean.PageBean;
import com.qhlx.core.util.json.RequestData;
import com.qhlx.core.util.web.ApiResponse;
import com.qhlx.core.util.web.WebResponse;
import com.qlhx.auth.api.api.test.TestApi;
import com.qlhx.auth.api.vo.test.TestVO;
import com.qlhx.web.auth.controller.BaseController;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
public class TestController extends BaseController {

    @Autowired
    private TestApi testApi;

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * 分页查询
     * @param data
     * @return
     */
    public WebResponse<PageBean<TestVO>> findByPage(@RequestBody RequestData<Map<String,Object>> data){
        WebResponse<PageBean<TestVO>> webResponse = new WebResponse<>();
        try {
            Map<String,Object> params = data.getReqData();
            ApiResponse<PageBean<TestVO>> apiResponse = testApi.findByPage(params);
            this.setResponse(apiResponse,webResponse);
        }catch (FeignException e){
            logger.error("异常：{}",e.getMessage());
        }catch (Exception e){
            logger.error("异常：{}",e.getMessage());
        }
        return webResponse;
    }

}

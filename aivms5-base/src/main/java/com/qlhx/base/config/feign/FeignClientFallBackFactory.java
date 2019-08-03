package com.qlhx.base.config.feign;

import com.qhlx.core.bean.Code;
import com.qhlx.core.util.web.ApiResponse;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/27 11:14
 * @Description
 *      desc:FeignClient 统一回调
 */
public class FeignClientFallBackFactory implements FallbackFactory<ApiResponse> {

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(FeignClientFallBackFactory.class);


    /**
     * 统一调用失败回写方法
     * @param throwable
     * @return
     */
    @Override
    public ApiResponse create(Throwable throwable) {
        ApiResponse<Object> response = new ApiResponse<>();
        logger.error("FeignClientFallBackFactory调用异常---->{}",throwable.getMessage());
        response.setCode(Code.SYS_ERROR);
        response.setDesc(throwable.getMessage());
        return response;
    }
}

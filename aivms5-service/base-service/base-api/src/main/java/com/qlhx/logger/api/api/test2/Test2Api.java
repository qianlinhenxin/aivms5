package com.qlhx.logger.api.api.test2;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/8/2 9:05
 * @Description desc:
 */
@FeignClient(value = "${server.name.base")
public interface Test2Api {
}

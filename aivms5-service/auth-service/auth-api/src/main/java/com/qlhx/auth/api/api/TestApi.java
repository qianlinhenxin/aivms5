package com.qlhx.auth.api.api;

import com.qhlx.core.page.PageDTO;
import com.qhlx.core.util.web.ApiResponse;
import com.qlhx.auth.api.vo.TestVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/17 21:04
 * @Description desc:
 */
@FeignClient(value = "${server.name.auth}")
public interface TestApi {

    @RequestMapping(value="/save", method = RequestMethod.POST)
    ApiResponse<Integer> save(@RequestBody TestVO TestVO);

    @RequestMapping(value="/update", method = RequestMethod.POST)
    ApiResponse<Integer> update(@RequestBody TestVO TestVO);

    @RequestMapping(value="/updateIgnoreNull", method = RequestMethod.POST)
    ApiResponse<Integer> updateBySelective(@RequestBody TestVO TestVO);

    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    ApiResponse<PageDTO<TestVO>> findByPage(@RequestBody Map<String, Object> params);

    @RequestMapping(value = "/findAll")
    ApiResponse<List<TestVO>> findAll();

    @RequestMapping(value = "/findByPrimaryKey")
    ApiResponse<TestVO> findByPrimaryKey(@RequestParam("id")  Long id);

    @RequestMapping(value = "/deleteByPrimaryKey")
    ApiResponse<Integer> deleteByPrimaryKey(@RequestParam("id")  Long id);

    @RequestMapping(value = "/findByParams")
    ApiResponse<List<TestVO>> findByParams(@RequestBody Map<String, Object> params);


    String PREFIX_URL = "/url-auth";

    @RequestMapping(value = PREFIX_URL+"/test",method = RequestMethod.POST)
    ApiResponse<TestVO> test(@RequestBody TestVO TestVO);
}
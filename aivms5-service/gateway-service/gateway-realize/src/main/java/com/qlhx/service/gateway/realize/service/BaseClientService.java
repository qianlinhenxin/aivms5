package com.qlhx.service.gateway.realize.service;


import com.qlhx.base.model.ApiResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "service-base")
public interface BaseClientService {
	
	@RequestMapping(value ="/user/islogin/{token}",method = {RequestMethod.GET})
    ApiResult<String> islogin(@PathVariable("token") String token);

}

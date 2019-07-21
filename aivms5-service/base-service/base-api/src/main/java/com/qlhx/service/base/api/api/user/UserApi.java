package com.qlhx.service.base.api.api.user;

import com.qlhx.base.model.ApiResult;
import com.qlhx.service.base.api.vo.user.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/21 14:44
 * @description desc:
 */
@FeignClient(value = "${server.name.base}")
public interface UserApi {

    String PATH = "/user-logon";


    @RequestMapping(value = PATH+"/login",method = RequestMethod.POST)
    ApiResult<UserVO> login(UserVO vo);

    @RequestMapping(value = PATH+"/islogin/{token}",method = RequestMethod.GET)
    ApiResult<String> isLogin(String token);


}

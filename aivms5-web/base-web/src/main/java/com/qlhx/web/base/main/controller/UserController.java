package com.qlhx.web.base.main.controller;

import com.qlhx.base.model.ApiResult;
import com.qlhx.service.base.api.api.user.UserApi;
import com.qlhx.service.base.api.vo.user.UserVO;
import com.qlhx.service.base.realize.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(maxAge = 3600)
@RestController
@Scope(value = "prototype")
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserApi userApi;

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<UserVO> login(@RequestBody UserVO vo) {
        ApiResult<UserVO> result = null;
        try {
            result = userApi.login(vo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("用户登录发生错误", e);
        }
        return result;

    }

    @RequestMapping(value = "/islogin/{token}", method = RequestMethod.GET)
    public ApiResult<String> islogin(@PathVariable String token) {
        ApiResult<String> result = null ;
        try{
            result = userApi.isLogin(token);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }


//    private String saveToken(String username) throws Exception {
//        String token = UUID.randomUUID().toString().replace("-", "");
//        myRedis.opsForValue().set(token, username, 60000);
//        logger.info("用户:" + username + " 登录成功,生成token:" + token);
//        logger.info("========" + (String) myRedis.opsForValue().get(token));
//        return token;
//    }


}

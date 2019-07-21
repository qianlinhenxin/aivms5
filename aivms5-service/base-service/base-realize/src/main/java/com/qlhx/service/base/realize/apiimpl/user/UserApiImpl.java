package com.qlhx.service.base.realize.apiimpl.user;

import com.qlhx.base.api.impl.BaseApiImpl;
import com.qlhx.base.model.ApiResult;
import com.qlhx.base.util.bean.ObjectUtil;
import com.qlhx.service.base.api.api.user.UserApi;
import com.qlhx.service.base.api.vo.user.UserVO;
import com.qlhx.service.base.realize.model.User;
import com.qlhx.service.base.realize.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/21 14:53
 * @description desc:
 */
@RequestMapping("/user-logon")
public class UserApiImpl extends BaseApiImpl<User, UserVO> implements UserApi {

    private  static Logger logger = LoggerFactory.getLogger(UserApiImpl.class);

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ApiResult<UserVO> login(@RequestBody UserVO vo) {
        ApiResult<UserVO> apiResult = new ApiResult<>();
        try {
            User user = userService.selectByUserNameAndPassword(vo.getUsername(), vo.getPassword());
            if (user != null) {
                String token = this.saveToken(user.getUsername());
                user.setToken(token);
                UserVO userVO = ObjectUtil.copy(user, UserVO.class);
                apiResult.setContent(userVO);
            } else {
                apiResult.setCode(ApiResult.PARAM_ERROR);
                apiResult.setMsg("账号或密码错误");
            }
        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return apiResult;
    }

    @RequestMapping(value = "/islogin/{token}",method = RequestMethod.GET)
    public ApiResult<String> isLogin(@PathVariable String token) {
        ApiResult<String> apiResult = new ApiResult<>();
        String username = "token";
        if (StringUtils.isBlank(username)) {
            logger.info("token=" + token + "  不存在，验证失败");
            apiResult.setCode(ApiResult.NO_LOGIN);
            apiResult.setMsg("未登录");
        } else {
            logger.info("token=" + token + " 验证成功,用户为:" + username);
            apiResult.setContent(username);
        }
        return apiResult;
    }

    private String saveToken(String username) throws Exception
    {
//        String token = UUID.randomUUID().toString().replace("-", "");
//        myRedis.opsForValue().set(token, username, 60000);
//        logger.info("用户:"+username+" 登录成功,生成token:"+token);
//        logger.info("========"+(String)myRedis.opsForValue().get(token));
        return null;
    }
}

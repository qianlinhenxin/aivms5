package com.qlhx.service.base.api.vo.user;

import com.qlhx.base.annotation.BeanField;
import com.qlhx.base.util.bean.ObjectUtil;
import com.qlhx.base.vo.BaseVO;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/21 14:46
 * @description desc:
 */
public class UserVO extends BaseVO {

    /**
     * 用户名
     */
    @BeanField(desc = "用户名")
    private String username;

    /**
     * 密码
     */
    @BeanField(desc = "密码")
    private String password;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return ObjectUtil.toString(this);
    }
}

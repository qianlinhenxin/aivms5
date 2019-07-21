package com.qlhx.service.auth.api.vo;

import com.qlhx.base.annotation.BeanField;
import com.qlhx.base.util.bean.ObjectUtil;
import com.qlhx.base.vo.BaseVO;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/17 21:04
 * @Description desc:
 */
public class TestVO extends BaseVO {

    /**
     * 姓名
     */
    @BeanField(desc = "姓名")
    private String name;

    /**
     * 年龄
     */
    @BeanField(desc = "年龄")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public String toString() {
        return ObjectUtil.toString(this);
    }
}

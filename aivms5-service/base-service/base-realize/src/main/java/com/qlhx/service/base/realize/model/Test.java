package com.qlhx.service.base.realize.model;

import com.qlhx.base.annotation.BeanField;
import com.qlhx.base.bean.BaseBean;
import com.qlhx.base.util.bean.ObjectUtil;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/18 9:05
 * @Description desc:
 */
public class Test extends BaseBean {
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

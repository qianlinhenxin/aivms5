package com.qlhx.auth.realize.bean;


import com.qhlx.core.bean.BaseBean;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/18 9:05
 * @Description desc:
 */
public class Test extends BaseBean {
    private String name;

    private Long age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }
}

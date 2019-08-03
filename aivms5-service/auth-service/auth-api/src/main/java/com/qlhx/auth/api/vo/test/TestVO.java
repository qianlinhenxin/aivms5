package com.qlhx.auth.api.vo.test;


import com.qhlx.core.vo.BaseVO;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/8/3 15:10
 * @Description desc:
 */
public class TestVO extends BaseVO {

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

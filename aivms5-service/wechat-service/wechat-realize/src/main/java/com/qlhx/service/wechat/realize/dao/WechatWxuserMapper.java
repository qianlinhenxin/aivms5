package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.WechatWxuser;

public interface WechatWxuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatWxuser record);

    int insertSelective(WechatWxuser record);

    WechatWxuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatWxuser record);

    int updateByPrimaryKey(WechatWxuser record);

    WechatWxuser GetWxUserByTel(String tel);

}
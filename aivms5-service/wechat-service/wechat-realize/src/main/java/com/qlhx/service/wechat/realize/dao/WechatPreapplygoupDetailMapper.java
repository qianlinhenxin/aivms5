package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.WechatPreapplygoupDetail;

public interface WechatPreapplygoupDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatPreapplygoupDetail record);

    int insertSelective(WechatPreapplygoupDetail record);

    WechatPreapplygoupDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatPreapplygoupDetail record);

    int updateByPrimaryKey(WechatPreapplygoupDetail record);
}
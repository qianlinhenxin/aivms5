package com.qlhx.service.wechat.realize.dao;

import com.qlhx.service.wechat.realize.model.WechatPreapplygoup;
import org.apache.ibatis.annotations.Param;

public interface WechatPreapplygoupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatPreapplygoup record);

    int insertSelective(WechatPreapplygoup record);

    WechatPreapplygoup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatPreapplygoup record);

    int updateByPrimaryKey(WechatPreapplygoup record);

    WechatPreapplygoup selectBySyncId(@Param("syncid") String syncid);
}
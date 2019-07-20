package com.qlhx.service.base.realize.mapper;


import com.qlhx.service.base.realize.model.Card;

public interface CardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Card record);

    int insertSelective(Card record);

    Card selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKey(Card record);
    
    Card selectByCardNum(String cardNum);
}
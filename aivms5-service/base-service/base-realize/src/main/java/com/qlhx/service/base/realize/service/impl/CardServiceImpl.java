package com.qlhx.service.base.realize.service.impl;

import com.qlhx.service.base.realize.mapper.CardMapper;
import com.qlhx.service.base.realize.model.Card;
import com.qlhx.service.base.realize.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
    CardMapper cardMapper;

	@Override
	public int insertSelective(Card record) {
		return cardMapper.insertSelective(record);
	}

	@Override
	public Card selectByCardNum(String cardNum) {
		return cardMapper.selectByCardNum(cardNum);
	}
	
	

}

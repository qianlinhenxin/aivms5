package com.qlhx.service.base.realize.service;


import com.qlhx.service.base.realize.model.Card;

public interface CardService {
	int insertSelective(Card record);
	
	Card selectByCardNum(String cardNum);

}

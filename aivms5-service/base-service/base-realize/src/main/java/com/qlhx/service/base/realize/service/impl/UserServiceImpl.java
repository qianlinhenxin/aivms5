package com.qlhx.service.base.realize.service.impl;

import com.qlhx.service.base.realize.mapper.UserMapper;
import com.qlhx.service.base.realize.model.User;
import com.qlhx.service.base.realize.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    UserMapper userMapper;

	@Override
	public User selectByUserNameAndPassword(String userName, String password) {
		return userMapper.selectByUserNameAndPassword(userName, password);
	}

}

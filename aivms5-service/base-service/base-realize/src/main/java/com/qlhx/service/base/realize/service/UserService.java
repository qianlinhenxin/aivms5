package com.qlhx.service.base.realize.service;


import com.qlhx.service.base.realize.model.User;

public interface UserService {
	

    User selectByUserNameAndPassword(String userName, String password);


}

package com.qlhx.service.base.realize.mapper;

import com.qlhx.service.base.realize.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserNameAndPassword(@Param("username") String userName, @Param("password") String password);
}
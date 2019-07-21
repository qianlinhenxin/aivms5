package com.qlhx.service.wechat.realize.dao;
import com.qlhx.service.wechat.realize.model.UUser;
import com.qlhx.service.wechat.realize.model.UUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UUserRoleMapper {
    int insert(UUserRole record);

    int insertSelective(UUserRole record);

    int deleteByUserId(Long id);

    int deleteRoleByUserIds(Map<String, Object> resultMap);

    List<Long> findUserIdByRoleId(Long id);

    UUser checkUserRoleById(@Param("uid") int uid);

}
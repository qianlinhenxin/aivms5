package com.qlhx.service.base.realize.mapper;


import com.qlhx.service.base.realize.model.BaseRes;

import java.util.List;

public interface BaseResMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseRes record);

    int insertSelective(BaseRes record);

    BaseRes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseRes record);

    int updateByPrimaryKey(BaseRes record);

    /**
     *
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:获取带入/带出物品选项列表
     * </p>
     *
     * @return
     * @throws Exception
     */
    List<BaseRes> findDraginAndTakeoutRes() throws Exception;
}
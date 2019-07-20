package com.qlhx.service.base.realize.service.impl;

import com.qlhx.service.base.realize.mapper.BaseVisitorCarMapper;
import com.qlhx.service.base.realize.model.BaseVisitorCar;
import com.qlhx.service.base.realize.service.BaseVisitorCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/6 10:01
 * @description desc:

 */

@Service
public class BaseVisitorCarServiceImpl implements BaseVisitorCarService {

    @Autowired
    private BaseVisitorCarMapper baseVisitorCarMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return baseVisitorCarMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BaseVisitorCar record) {
        return baseVisitorCarMapper.insert(record);
    }

    @Override
    public int insertSelective(BaseVisitorCar record) {
        return baseVisitorCarMapper.insertSelective(record);
    }

    @Override
    public BaseVisitorCar selectByPrimaryKey(Integer id) {
        return baseVisitorCarMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BaseVisitorCar record) {
        return baseVisitorCarMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BaseVisitorCar record) {
        return baseVisitorCarMapper.updateByPrimaryKey(record);
    }

    @Override
    public BaseVisitorCar findCarByArId(Integer arId) throws Exception {
        return findCarByArId(arId);
    }
}

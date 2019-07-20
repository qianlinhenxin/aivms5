package com.qlhx.service.base.realize.service;

import com.qlhx.service.base.realize.model.BaseVisitorCar;
import org.apache.ibatis.annotations.Param;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/6 10:01
 * @description desc:
 */
public interface BaseVisitorCarService {

    int deleteByPrimaryKey(Integer id);

    int insert(BaseVisitorCar record);

    int insertSelective(BaseVisitorCar record);

    BaseVisitorCar selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseVisitorCar record);

    int updateByPrimaryKey(BaseVisitorCar record);

    /**
     *
     * <p>
     * Title:根据访客记录id查询访客车辆信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param arId
     * @return
     * @throws Exception
     */
    BaseVisitorCar findCarByArId(@Param("arId") Integer arId) throws Exception;
}

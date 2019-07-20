package com.qlhx.service.base.realize.mapper;

import com.qlhx.service.base.realize.model.BaseVisitorCar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseVisitorCarMapper {
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
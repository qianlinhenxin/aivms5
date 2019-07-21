package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.VisitorCar;
import org.apache.ibatis.annotations.Param;

public interface VisitorCarMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor_car
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor_car
     *
     * @mbggenerated
     */
    int insert(VisitorCar record) throws Exception;

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor_car
     *
     * @mbggenerated
     */
    int insertSelective(VisitorCar record) throws Exception;

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor_car
     *
     * @mbggenerated
     */
    VisitorCar selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor_car
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(VisitorCar record) throws Exception;

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor_car
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(VisitorCar record) throws Exception;

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
    VisitorCar findCarByArId(@Param("arId") Integer arId)
	    throws Exception;

}
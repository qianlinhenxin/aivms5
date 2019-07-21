package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.TakeoutRes;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TakeoutResMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_takeout_res
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_takeout_res
     *
     * @mbggenerated
     */
    int insert(TakeoutRes record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_takeout_res
     *
     * @mbggenerated
     */
    int insertSelective(TakeoutRes record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_takeout_res
     *
     * @mbggenerated
     */
    TakeoutRes selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_takeout_res
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TakeoutRes record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_takeout_res
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TakeoutRes record);

    /**
     * 
     * <p>
     * Title:获取访客某次来访带出物品列表
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param arID
     * @return
     * @throws Exception
     */
    List<TakeoutRes> findTakeOutResByarId(@Param("arID") Integer arID)
	    throws Exception;
}
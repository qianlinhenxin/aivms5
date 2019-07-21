package com.qlhx.service.wechat.realize.dao;



import com.qlhx.service.wechat.realize.model.Res;

import java.util.List;

public interface ResMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_res
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_res
     *
     * @mbggenerated
     */
    int insert(Res record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_res
     *
     * @mbggenerated
     */
    int insertSelective(Res record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_res
     *
     * @mbggenerated
     */
    Res selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_res
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Res record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_res
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Res record);

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
    List<Res> findDraginAndTakeoutRes() throws Exception;

}
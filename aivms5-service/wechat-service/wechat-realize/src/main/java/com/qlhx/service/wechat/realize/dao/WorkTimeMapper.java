package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.WorkTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkTimeMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_work_time
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer wordid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_work_time
     *
     * @mbggenerated
     */
    int insert(WorkTime record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_work_time
     *
     * @mbggenerated
     */
    int insertSelective(WorkTime record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_work_time
     *
     * @mbggenerated
     */
    WorkTime selectByPrimaryKey(Integer wordid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_work_time
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(WorkTime record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_work_time
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(WorkTime record);

    /**
     * 根据企业编号获取该企业所有部门的所有上下班时间列表
     * 
     * @param companyNum
     *            非必须，为空时查询所有部门的上下班时间列表
     * @return
     * @throws Exception
     */
    List<WorkTime> findWorkTimeList(@Param("companyNum") String companyNum,
                                    @Param("depID") String depID, @Param("year") String year,
                                    @Param("num") Integer num, @Param("recordNum") Integer recordNum)
	    throws Exception;

    /**
     * 根据条件查询上下班时间设置列表记录条数
     * 
     * @param companyNum
     * @param depID
     * @param year
     * @return
     * @throws Exception
     */
    Integer workCountNum(@Param("companyNum") String companyNum,
                         @Param("depID") String depID, @Param("year") String year)
	    throws Exception;

    /**
     * 根据id和年月查询上下班规则信息
     * 
     * @param depID
     *            部门id
     * @param yt
     *            年月
     * @return
     * @throws Exception
     */
    WorkTime findWorkTimeByDepidAndYT(@Param("depID") Integer depID,
                                      @Param("yt") String yt) throws Exception;

    WorkTime findWorkTimeByYearMonth(@Param("companyNum") String companyNum,
                                     @Param("depID") String deptId, @Param("date") String date)
	    throws Exception;;

}
package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.Archives;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArchivesMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_archives
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer archivesid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_archives
     *
     * @mbggenerated
     */
    int insert(Archives record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_archives
     *
     * @mbggenerated
     */
    int insertSelective(Archives record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_archives
     *
     * @mbggenerated
     */
    Archives selectByPrimaryKey(Integer archivesid);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_archives
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Archives record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_archives
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Archives record);

    Archives getArchivesByYT(@Param("YT") String yt, @Param("status") int status);

    /**
     * 根据年月查询归档信息
     * 
     * @param yt
     * @return
     * @throws Exception
     */
    Archives findArchivesByYT(@Param("yt") String yt) throws Exception;

    /**
     * 查询考勤归档信息列表
     * 
     * @param year
     *            年份
     * @param month
     *            月份
     * @return
     * @throws Exception
     */
    List<Archives> findArchivesList(@Param("year") String year,
                                    @Param("month") String month,
                                    @Param("recordNum") Integer recordNum, @Param("num") Integer num)
	    throws Exception;

    /**
     * 考勤归档信息记录条数
     * 
     * @param year
     * @param month
     * @return
     * @throws Exception
     */
    Integer archivesCountNum(@Param("year") String year,
                             @Param("month") String month) throws Exception;

}
package com.qlhx.service.base.realize.mapper;

import com.qlhx.service.base.realize.model.BaseAccessRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BaseAccessRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseAccessRecord record);

    int insertSelective(BaseAccessRecord record);

    BaseAccessRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseAccessRecord record);

    int updateByPrimaryKey(BaseAccessRecord record);
    
    Integer findAccessRecordPageCount(BaseAccessRecord recode) throws Exception;
    
    List<BaseAccessRecord> findAccessRecord(BaseAccessRecord recode) throws Exception;
    
    BaseAccessRecord findAccessRecordByCardNum(@Param("cardNum") String cardNum) ;
    /**
     * <p>
     * Title:获取指定日期访客出/入数量
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param date
     * @return
     * @throws Exception
     */
    Map<String, Integer> findTodayIntoAndOutNum(@Param("date") String date)
            throws Exception;


    /**
     * <p>
     * Title:通过门禁卡查询门禁卡是否被占用
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cardNum
     * @return
     * @throws Exception
     */
    List<BaseAccessRecord> selectByCardNum(@Param("cardNum") String cardNum)
            throws Exception;

    /**
     *
     * <p>
     * Title:根据来访人证件号码查询上次来访信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param idNum
     * @return
     * @throws Exception
     */
    BaseAccessRecord findLastAccessRecordByIdNum(@Param("idNum") String idNum);
}
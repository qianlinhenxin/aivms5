package com.qlhx.service.wechat.realize.dao;

import com.qlhx.service.wechat.realize.model.Visitor;
import com.qlhx.service.wechat.realize.model.VisitorRecordBo;
import com.qlhx.service.wechat.realize.model.VisitorStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface VisitorMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor
     *
     * @mbggenerated
     */
    int insert(Visitor record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor
     *
     * @mbggenerated
     */
    int insertSelective(Visitor record) throws Exception;

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor
     *
     * @mbggenerated
     */
    Visitor selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Visitor record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table u_visitor
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Visitor record);

    /**
     * <p>
     * Title:根据身份证号码查询访客信息
     * </p>
     * <p>
     * Description:根据身份证号码查询访客信息
     * </p>
     *
     * @param idNum
     *            身份证号码
     * @return
     * @throws Exception
     */
    Visitor findVisitorByIdNum(@Param("idNum") String idNum) throws Exception;

    List<VisitorRecordBo> findVisitorRecord(Map<String, Object> resultMap);

    List<Visitor> findAll(Map<String, Object> resultMap);

    /**
     * <p>
     * Title:根据访客凭证二维码或一维码信息查询访客信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param rCode
     * @return
     * @throws Exception
     */
    Visitor findVisitorByRCode(@Param("rCode") String rCode) throws Exception;

    /**
     * 根据身份证号码查询是否预约
     *
     * @param idNum
     *            身份证号码
     * @return
     */
    Integer findOrderVisitorByIdNumCount(@Param("idNum") String idNum);

    VisitorRecordBo findOrderVisitorByRId(@Param("id") Integer id);

    List<VisitorStatistics> findVisitorStatistics(Map<String, Object> modelMap);

    /**
     *
     * <p>
     * Title:根据ic卡号查询当前正在进行访问的访客信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param icNum
     * @return
     * @throws Exception
     */
    Visitor findVisitorByIcNum(@Param("icNum") String icNum) throws Exception;

    List<VisitorRecordBo> selectPhotoByVids(Map<String, Object> resultModel);

    /**
     * 
     * <p>
     * Title:根据来访人员身份证号码查询该访客是否在黑名单
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param idNum
     * @return
     * @throws Exception
     */
    List<Visitor> findBlackListByIdNum(@Param("idNum") String idNum) throws Exception;

    /**
     * 
     * <p>
     * Title:根据来访人员身份号码判断该访客是否注销访问
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param idNum
     * @return
     * @throws Exception
     */
    List<Visitor> isLogOff(@Param("idNum") String idNum) throws Exception;

    /**
     * 
     * <p>
     * Title:根据来访人员身份号码判断该访客是否有预约
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param idNum
     * @return
     * @throws Exception
     */
    List<Visitor> isAppointment(@Param("idNum") String idNum) throws Exception;

    /**
     * 更新访客信息已经绑定指纹
     * @param uid
     * @return
     */
    Integer UpateIsBindFinger(@Param("uid") Integer uid) throws Exception;
    
    Visitor selectVisitorByPama(Visitor visitor) throws Exception;
    
    int updateByCompanyCodeAndId(Visitor visitor);
    
    int findCount(Map parama);
    
    int  findVisitorRecordCount(Map parama);
    
    List<Map> selectVisitorToUploadPolice(Map param);
    
    List<Integer> selectVidsByName(String name);
    
}
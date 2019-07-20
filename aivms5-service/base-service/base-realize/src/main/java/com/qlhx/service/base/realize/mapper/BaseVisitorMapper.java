package com.qlhx.service.base.realize.mapper;

import com.qlhx.service.base.realize.model.BaseAccessRecord;
import com.qlhx.service.base.realize.model.BaseVisitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseVisitorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseVisitor record);

    int insertSelective(BaseVisitor record);

    BaseVisitor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseVisitor record);

    int updateByPrimaryKey(BaseVisitor record);
    
    List<BaseVisitor> findAll();
    
    BaseVisitor selectByIdNum(String idNum);
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
    BaseVisitor findVisitorByIdNum(@Param("idNum") String idNum) throws Exception;
    BaseVisitor findVisitorByIdPhone(@Param("phone") String phone) throws Exception;

    Integer findEmployeesCountById(Integer deptid) throws Exception;

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
    BaseVisitor findVisitorByRCode(@Param("rCode") String rCode) throws Exception;

    /**
     * 根据ic卡号查询访客最后一条访问记录没有注销的
     * @param icNum
     * @return
     */
    BaseAccessRecord findVisitorAccessByICNum(String icNum);

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
    List<BaseVisitor> isLogOff(@Param("idNum") String idNum) throws Exception;

}
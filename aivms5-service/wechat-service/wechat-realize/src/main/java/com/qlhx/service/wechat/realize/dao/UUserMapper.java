package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.ComboResult;
import com.qlhx.service.wechat.realize.model.URoleBo;
import com.qlhx.service.wechat.realize.model.UUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Map;

public interface UUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record) throws Exception;

    UUser selectByPrimaryKey(Long id);

    UUser selectUserInfoByPrimaryKey(Long id) throws Exception;

    int updateByPrimaryKeySelective(UUser record) throws Exception;

    int updateByPrimaryKey(UUser record);

    UUser login(Map<String, Object> map);

    UUser findUserByEmail(String emaFil);

    List<URoleBo> selectRoleByUserId(Long id);

    /**
     * <p>
     * Title:获取被访人列表
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @return
     * @throws Exception
     */
    List<UUser> findEmployeesById(@Param("depId") Integer depId,
                                  @Param("pageindex") Integer pageindex,
                                  @Param("pagesize") Integer pagesize) throws Exception;

    Integer findEmployeesCountById(@Param("depId") Integer depId)
	    throws Exception;

    List<ComboResult> findUserComboList();

    /**
     * <p>
     * Title:根据用户卡号查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cardNum
     * @return
     * @throws Exception
     */
    UUser findUserInfoByCardNum(@Param("cardNum") String cardNum)
	    throws Exception;

    List<UUser> selectUserByDeptId(@Param("depId") Integer depId);

    List<UUser> selectExportUserList(ModelMap modelMap);

    UUser findByIdNum(@Param("idnum") String idNum);

    List<UUser> findByPhoneOreMail(@Param("phone") String phone,
                                   @Param("email") String email);

    Integer findByPhoneOrEmailCount(@Param("phone") String phone,
                                    @Param("email") String email, @Param("cardnum") String cardnum);

    Integer insertMembers(List<UUser> memebers) throws Exception;

    /**
     * <p>
     * Title:根据手机号码后4为或姓名拼音首字母查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param param
     * @param pageindex
     * @param pagesize
     * @return
     * @throws Exception
     */
    List<UUser> findUserByPhoneOrName(@Param("param") String param,
                                      @Param("pageindex") Integer pageindex,
                                      @Param("pagesize") Integer pagesize) throws Exception;

    Integer findUserCountByPhoneOrName(@Param("param") String param)
	    throws Exception;

    /**
     * <p>
     * Title:根据手机号码查询员工信息
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param phone
     * @return
     * @throws Exception
     */
    UUser findUserByPhoneNum(@Param("phone") String phone) throws Exception;

    UUser findByPhone(@Param("phone") String phone);

    UUser findUserByCardNum(@Param("ecardNum") String ecardNum);

    Integer findUserCountByDeptId(@Param("id") Integer id) throws Exception;

    /**
     * 根据员工同步id获取员工信息
     * 
     * @param syncId
     * @return
     * @throws Exception
     */
    UUser selectUserBySyncId(@Param("syncId") Integer syncId,
                             @Param("companyNum") String companyNum) throws Exception;
    
    UUser selectUserByPama(UUser user) throws Exception;

}
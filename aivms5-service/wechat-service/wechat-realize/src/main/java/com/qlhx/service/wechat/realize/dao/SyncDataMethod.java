/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author 余佳建
 * @date 2017年12月1日 下午6:14:19
 */
package com.qlhx.service.wechat.realize.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author 余佳建
 * @date 2017年12月1日 下午6:14:19
 */
public interface SyncDataMethod {

    /**
     * 数据同步
     * 
     * @param num
     *            同步数量（记录条数）
     * @return
     * @throws Exception
     */
    List<Object> syncData(@Param("num") Integer num) throws Exception;

    /**
     * 数据更新
     * 
     * @param
     *
     * @param
     *
     * @return
     * @throws Exception
     */
    int updateUploadStatus(Object obj) throws Exception;

}

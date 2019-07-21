package com.qlhx.service.wechat.realize.service;

/**
 * 
 * <p>
 * Title:定时任务相关接口服务
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author alaskayu
 * @date 2016年11月23日 下午5:30:26
 */
public interface TimerService {

    /**
     * 
     * <p>
     * Title:执行数据库升级SQL语句
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param updateSql
     * @return
     * @throws Exception
     */
    public Integer updateDB(String updateSql) throws Exception;

}

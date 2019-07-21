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
 * @date 2017年12月1日 下午2:46:32
 */
package com.qlhx.service.wechat.realize.utils;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 
 * <p>
 * Title:数据同步处理
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company:北京钱林恒兴科技股份有限公司
 * </p>
 * 
 * @author 余佳建
 * @date 2017年12月1日 下午2:46:32
 */
@Component
public class SyncDataManager {

    /**
     * 数据传输条数
     */
    private Integer num;

    /**
     * 数据 传输配制
     */
    private Map<String, Map<String, Map<String, String>>> configitem;

    /**
     * @return the num
     */
    public Integer getNum() {
	return num;
    }

    /**
     * @param num
     *            the num to set
     */
    public void setNum(Integer num) {
	this.num = num;
    }

    /**
     * @return the configitem
     */
    public Map<String, Map<String, Map<String, String>>> getConfigitem() {
	return configitem;
    }

    /**
     * @param configitem
     *            the configitem to set
     */
    public void setConfigitem(
	    Map<String, Map<String, Map<String, String>>> configitem) {
	this.configitem = configitem;
    }

}

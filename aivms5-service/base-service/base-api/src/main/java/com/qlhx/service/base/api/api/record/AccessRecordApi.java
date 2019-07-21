package com.qlhx.service.base.api.api.record;

import com.qlhx.base.model.ApiResult;
import com.qlhx.base.model.DataGridResult;
import com.qlhx.service.base.api.vo.record.BaseAccessRecordVO;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/21 15:26
 * @Description desc:
 */
public interface AccessRecordApi {

    String PATH = "/accessRecord";

    /**
     * 访客记录列表
     * @param modelMap
     * @param offset
     * @param limit
     * @param token
     * @return
     */
    @RequestMapping(value = PATH+"/list", method = RequestMethod.POST)
    DataGridResult list(ModelMap modelMap, Integer offset, Integer limit, @RequestHeader String token);

    /**
     * 添加访客记录
     * @param vo
     * @return
     */
    @RequestMapping(value = PATH+"addOrUpdate", method = RequestMethod.POST)
    ApiResult<String> addOrUpdate(BaseAccessRecordVO vo);

    /**
     *
     * @param record
     * @return
     */
    @RequestMapping(value = PATH+"/updateByPrimaryKeySelective", method = RequestMethod.POST)
    Integer updateByPrimaryKeySelective(BaseAccessRecordVO record);

    /**
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = PATH+"/insertSelective", method = RequestMethod.POST)
    Integer insertSelective(@RequestBody BaseAccessRecordVO vo);
}

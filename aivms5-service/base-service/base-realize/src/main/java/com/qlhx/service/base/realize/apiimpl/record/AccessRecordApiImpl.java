package com.qlhx.service.base.realize.apiimpl.record;

import com.qlhx.base.model.ApiResult;
import com.qlhx.base.model.DataGridResult;
import com.qlhx.base.util.bean.ObjectUtil;
import com.qlhx.service.base.api.api.record.AccessRecordApi;
import com.qlhx.service.base.api.vo.record.BaseAccessRecordVO;
import com.qlhx.service.base.realize.apiimpl.user.UserApiImpl;
import com.qlhx.service.base.realize.model.BaseAccessRecord;
import com.qlhx.service.base.realize.mybatis.page.Pagination;
import com.qlhx.service.base.realize.service.AccessRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/21 15:26
 * @Description desc:
 */
@RequestMapping("/accessRecord")
public class AccessRecordApiImpl  implements AccessRecordApi {

    private  static Logger logger = LoggerFactory.getLogger(UserApiImpl.class);


    @Autowired
    private AccessRecordService accessRecordService;

    /**
     * 访客记录列表
     * @param modelMap
     * @param offset
     * @param limit
     * @param token
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public DataGridResult list(ModelMap modelMap, Integer offset, Integer limit, @RequestHeader String token){
        DataGridResult dataGridResult = new DataGridResult();
        try {
            Pagination<BaseAccessRecord> list = accessRecordService.selectAccessRecord(modelMap, offset, limit);
            DataGridResult r = new DataGridResult();
            r.setTotal(list.getTotalCount());
            r.setRows(list.getList());
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return dataGridResult;
    }

    /**
     * 添加访客记录
     * @param vo
     * @return
     */
    @RequestMapping(value = "addOrUpdate", method = RequestMethod.POST)
    public ApiResult<String> addOrUpdate(@RequestBody BaseAccessRecordVO vo){
        ApiResult<String> apiResult = new ApiResult<>();
        try {
            accessRecordService.insertSelective(ObjectUtil.copy(vo,BaseAccessRecord.class));
        }catch (Exception e){
            logger.error(e.getMessage());
            return apiResult;
        }
        return apiResult;

    }

    /**
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
    public Integer updateByPrimaryKeySelective(@RequestBody BaseAccessRecordVO vo){
        Integer result = null;
        try {
            BaseAccessRecord accessRecord = ObjectUtil.copy(vo, BaseAccessRecord.class);
            result = accessRecordService.updateByPrimaryKeySelective(accessRecord);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;

    }

    /**
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
    public Integer insertSelective(@RequestBody BaseAccessRecordVO vo){
        Integer result = null;
        try {
            BaseAccessRecord accessRecord = ObjectUtil.copy(vo, BaseAccessRecord.class);
            result = accessRecordService.insertSelective(accessRecord);

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return result;
    }
}

package com.qlhx.web.base.main.controller;

import com.qlhx.common.model.ApiResult;
import com.qlhx.common.model.DataGridResult;
import com.qlhx.model.BaseAccessRecord;
import com.qlhx.mybatis.page.Pagination;
import com.qlhx.service.AccessRecordService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(value = "访客记录接口", description = "访客记录接口")
@CrossOrigin(maxAge = 3600)
@RestController
@Scope(value = "prototype")
@RequestMapping("accessRecord")
public class AccessRecordController {

	private final Logger logger = LoggerFactory.getLogger(AccessRecordController.class);

	@Autowired
    AccessRecordService accessRecordService;

	/**
	 * 访客记录列表
	 * @param modelMap
	 * @param offset
	 * @param limit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST ,produces = { "application/json" })
	public @ResponseBody DataGridResult list(ModelMap modelMap, Integer offset, Integer limit,@RequestHeader String token) {
		Pagination<BaseAccessRecord> list = accessRecordService.selectAccessRecord(modelMap, offset, limit);
		DataGridResult r = new DataGridResult();
		r.setTotal(list.getTotalCount());
		r.setRows(list.getList());
		return r;
	}
	
	/**
	 * 添加访客记录
	 * @param baseAccessRecord
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addOrUpdate", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody
    ApiResult<String> addOrUpdate(BaseAccessRecord baseAccessRecord, HttpServletRequest request) {
		ApiResult<String> result = new ApiResult<String>();
		try {
			baseAccessRecord.setCreatetime(new Date());
			accessRecordService.insertSelective(baseAccessRecord);
		} catch (Exception e) {
			result.getErrorResult(e);
		}
		return result;
		
	}
	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public int updateByPrimaryKeySelective(@RequestBody  BaseAccessRecord record){
		return accessRecordService.updateByPrimaryKeySelective(record);
	}


	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
	public int insertSelective(@RequestBody BaseAccessRecord record){
		int i = accessRecordService.insertSelective(record);
		if(i>0){
			i = record.getId();
		}else{
			i = 0;
		}
		return i;
	}




}

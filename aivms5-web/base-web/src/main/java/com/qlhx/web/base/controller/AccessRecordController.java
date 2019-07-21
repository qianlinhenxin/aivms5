package com.qlhx.web.base.controller;

import com.qlhx.base.model.ApiResult;
import com.qlhx.base.model.DataGridResult;
import com.qlhx.service.base.api.api.record.AccessRecordApi;
import com.qlhx.service.base.api.vo.record.BaseAccessRecordVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * 访客记录接口
 */
@CrossOrigin(maxAge = 3600)
@RestController
@Scope(value = "prototype")
@RequestMapping("accessRecord")
public class AccessRecordController {

	private final Logger logger = LoggerFactory.getLogger(AccessRecordController.class);

	@Autowired
	private AccessRecordApi accessRecordApi;

	/**
	 * 访客记录列表
	 * @param modelMap
	 * @param offset
	 * @param limit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST )
	public DataGridResult list(ModelMap modelMap, Integer offset, Integer limit, @RequestHeader String token) {
		return  accessRecordApi.list(modelMap, offset, limit,token);
	}
	
	/**
	 * 添加访客记录
	 * @param vo
	 * @return
	 */
	@RequestMapping(value = "addOrUpdate", method = RequestMethod.POST)
	public @ResponseBody
    ApiResult<String> addOrUpdate(BaseAccessRecordVO vo) {
		ApiResult<String> result = new ApiResult();
		try {
			vo.setCreatetime(new Date());
			result = accessRecordApi.addOrUpdate(vo);
		} catch (Exception e) {
			result.getErrorResult(e);
		}
		return result;
	}

	@RequestMapping(value = "/updateByPrimaryKeySelective", method = RequestMethod.POST)
	public int updateByPrimaryKeySelective(@RequestBody  BaseAccessRecordVO vo){
		return accessRecordApi.updateByPrimaryKeySelective(vo);
	}


	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
	public int insertSelective(@RequestBody BaseAccessRecordVO record){
		return accessRecordApi.insertSelective(record);
	}




}

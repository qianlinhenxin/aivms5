package com.qlhx.web.base.main.controller;

import com.qlhx.common.model.DataGridResult;
import com.qlhx.model.BaseVisitor;
import com.qlhx.mybatis.page.Pagination;
import com.qlhx.service.VisitorService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Api(value = "访客接口接口", description = "访客接口接口")
@CrossOrigin(maxAge = 3600)
@RestController
@Scope(value = "prototype")
@RequestMapping("visitor")
public class VisitorController {

	private final Logger logger = LoggerFactory.getLogger(VisitorController.class);

	@Autowired
    VisitorService visitorService;

	/**
	 * 访客列表
	 * @param modelMap
	 * @param offset
	 * @param limit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST ,produces = { "application/json" })
	public @ResponseBody DataGridResult list(ModelMap modelMap, Integer offset, Integer limit,@RequestHeader String token) {
		Pagination<BaseVisitor> list = visitorService.selectVisitorRecord(modelMap, offset, limit);
		DataGridResult r = new DataGridResult();
		r.setTotal(list.getTotalCount());
		r.setRows(list.getList());
		return r;
	}

	@RequestMapping(value = "/findVisitorByIdNum/{idNum}",method = RequestMethod.GET)
	public  BaseVisitor findVisitorByIdNum(@PathVariable String idNum){
		BaseVisitor baseVisitor = null;
		try {
			baseVisitor = visitorService.findVisitorByIdNum(idNum);
		} catch (Exception e) {
			logger.error(":根据身份证号码查询访客信息 方法发生异常",e);
			e.printStackTrace();
		}
		return baseVisitor;
	}


	@RequestMapping(value = "/findVisitorByIdPhone/{phone}",method = RequestMethod.GET)
	public  BaseVisitor findVisitorByIdPhone(@PathVariable String phone){
		BaseVisitor baseVisitor = null;
		try {
			baseVisitor = visitorService.findVisitorByIdPhone(phone);
		} catch (Exception e) {
			logger.error(":根据电话号码查询访客信息 方法发生异常",e);
			e.printStackTrace();
		}
		return baseVisitor;
	}

	@RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
	public int updateByPrimaryKey(@RequestBody BaseVisitor baseVisitor){
		return visitorService.updateByPrimaryKey(baseVisitor);
	}


	@RequestMapping(value = "/insertSelective",method = RequestMethod.POST)
	public int insertSelective(@RequestBody BaseVisitor baseVisitor){
		return visitorService.insertSelective(baseVisitor);
	}

}

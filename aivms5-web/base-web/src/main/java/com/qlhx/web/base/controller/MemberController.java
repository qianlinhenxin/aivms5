package com.qlhx.web.base.controller;

import com.qlhx.common.model.ApiResult;
import com.qlhx.common.model.DataGridResult;
import com.qlhx.common.util.PinYinUtil;
import com.qlhx.model.BaseMember;
import com.qlhx.mybatis.page.Pagination;
import com.qlhx.service.MemberService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Api(value = "员工接口", description = "员工接口")
@CrossOrigin(maxAge = 3600)
@RestController
@Scope(value = "prototype")
@RequestMapping("member")
public class MemberController {

	private final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	MemberService memberService;

	/**
	 * 员工记录列表
	 * @param modelMap
	 * @param offset
	 * @param limit
	 * @param token
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST ,produces = { "application/json" })
	public @ResponseBody DataGridResult list(ModelMap modelMap, Integer offset, Integer limit,@RequestHeader String token) {
		Pagination<BaseMember> list = memberService.selectMemberRecord(modelMap, offset, limit);
		DataGridResult r = new DataGridResult();
		r.setTotal(list.getTotalCount());
		r.setRows(list.getList());
		return r;
	}
	
	/**
	 * 添加修改员工
	 * @param baseMember
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "addOrUpdate", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody ApiResult<String> addOrUpdate(BaseMember baseMember, HttpServletRequest request) {
		ApiResult<String> result = new ApiResult<String>();
		try {
			baseMember.setCreateTime(new Date());
			baseMember.setPinyin(PinYinUtil.getFirstSpell(baseMember.getNickname()));
			baseMember.setMemberidentifier(UUID.randomUUID().toString());
			memberService.insertSelective(baseMember);
		} catch (Exception e) {
			result.getErrorResult(e);
		}
		return result;
	}

}

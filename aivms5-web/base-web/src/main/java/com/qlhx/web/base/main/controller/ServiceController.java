package com.qlhx.web.base.main.controller;

import com.qlhx.common.model.ApiResult;
import com.qlhx.model.BO.PersonBO;
import com.qlhx.model.BaseAccessRecord;
import com.qlhx.model.BaseMember;
import com.qlhx.model.BaseVisitor;
import com.qlhx.service.AccessRecordService;
import com.qlhx.service.MemberService;
import com.qlhx.service.VisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

@Api(value = "基础服务-微服务之间调用的接口", description = "基础服务-微服务之间调用的接口")
@CrossOrigin(maxAge = 3600)
@RestController
@Scope(value = "prototype")
@RequestMapping("service")
public class ServiceController {

	private final Logger logger = LoggerFactory.getLogger(ServiceController.class);

	@Autowired
	AccessRecordService accessRecordService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	VisitorService visitorService;

	@ApiOperation(value ="根据卡号查询员工或访客" ,notes ="根据卡号查询员工或访客")
	@RequestMapping(value = "/getUserByCardNum/{cardNum}",method = RequestMethod.GET, produces = {
    "application/json"})
	public @ResponseBody ApiResult<PersonBO> getUserByCardNum(@PathVariable String cardNum)
	{
		ApiResult<PersonBO> result = new ApiResult<PersonBO>() ;
		PersonBO personBO = new PersonBO();
		BaseMember member = memberService.selectByCardNum(cardNum);
		if(member != null)
		{
			personBO.setId(member.getId().intValue());
			personBO.setName(member.getNickname());
			personBO.setType(1);
			personBO.setGroupname(member.getGroupname());
			personBO.setGroupidentifier(member.getGroupidentifier());
			result.setContent(personBO);
		}
		else 
		{
			BaseAccessRecord baseAccessRecord = accessRecordService.findAccessRecordByCardNum(cardNum);
			if(baseAccessRecord != null)
			{
				BaseVisitor visitor = visitorService.selectByPrimaryKey(baseAccessRecord.getVid());
				if(visitor != null)
				{
					personBO.setId(visitor.getId().intValue());
					personBO.setName(visitor.getName());
					personBO.setType(2);
					personBO.setGroupname("缺省组");
					personBO.setGroupidentifier("3f1ed4e0-3eda-4f1d-b911-3ef4ca88cef8");
					result.setContent(personBO);
				}
				else
				{
					result.setCode(ApiResult.NO_FOUND_DATA);
					result.setMsg("根据卡号:"+cardNum+" 没有找到任何人员");
				}
			}
			else
			{
				result.setCode(ApiResult.NO_FOUND_DATA);
				result.setMsg("根据卡号:"+cardNum+" 没有找到任何人员");
			}
			
			
		}
		return result;
	}

}

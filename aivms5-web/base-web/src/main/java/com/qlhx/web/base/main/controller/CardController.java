package com.qlhx.web.base.main.controller;

import com.qlhx.common.model.ApiResult;
import com.qlhx.model.Card;
import com.qlhx.service.CardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value = "卡片接口", description = "卡片接口")
@CrossOrigin(maxAge = 3600)
@RestController
@Scope(value = "prototype") 
@RequestMapping("card")
public class CardController {
	
	private  final Logger logger = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
	CardService cardService;
	

	
	@ApiOperation(value ="发卡" ,notes ="发卡")
	@RequestMapping(value = "/save",method = RequestMethod.POST, produces = {
    "application/json"}, consumes = {"application/json"})
	public @ResponseBody ApiResult<String> save(@RequestBody Card card, HttpServletRequest request
			) 
	{
		ApiResult<String> result = new ApiResult<String>();
		try {
			cardService.insertSelective(card);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户登录发生错误", e);
			result.getErrorResult(e);
		}

		return result;

	}
	
	@ApiOperation(value ="查询卡是否存在" ,notes ="查询卡是否存在")
	@RequestMapping(value = "/power/{cardNum}",method = RequestMethod.GET, produces = {
    "application/json"})
	public @ResponseBody ApiResult<String> power(@PathVariable String cardNum)
	{
		ApiResult<String> result = new ApiResult<String>();
		Card card = cardService.selectByCardNum(cardNum);
		if(card == null)
		{
			result.setCode(ApiResult.NO_FOUND_DATA);
			result.setMsg("没有权限");
		}
		logger.info("======收到卡号:"+cardNum+" 验证结果:"+result.getMsg());
		return result;
	}
	
	

}

package com.qlhx.web.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.qlhx.common.VideoCallBackCache;
import com.qlhx.common.model.MQRegFace;
import com.qlhx.common.model.Result;
import com.qlhx.common.util.IConfig;
import com.qlhx.common.util.MD5Util;
import com.qlhx.dao.BaseNatiomMapper;
import com.qlhx.model.*;
import com.qlhx.mq.MQSender;
import com.qlhx.service.AccessRecordService;
import com.qlhx.service.MemberService;
import com.qlhx.service.UserManager;
import com.qlhx.service.VisitorReasonsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tools.DateTime;
import tools.HTTP;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "访客机接口", description = "访客机接口")
@CrossOrigin(maxAge = 3600)
@RestController
@PropertySource("classpath:application-config.properties")
@Scope(value = "prototype")
@RequestMapping("fkterminal")
public class TerminalController {

    @Value("vcardnum")
    String numlimit ;
	private final Logger logger = LoggerFactory.getLogger(TerminalController.class);

	@Autowired
	AccessRecordService accessRecordService;

	@Autowired
	MemberService memberService;

	@Autowired
	VisitorReasonsService visitorReasonsService;


	@Autowired
    BaseNatiomMapper baseNatiomMapper;

	@Autowired
	private MQSender sender;

	@ApiOperation(value = "根据手机号码后4位或姓名首字母查询被访人", notes = "根据手机号码后4位或姓名首字母查询被访人（不区分大小写）")
	@RequestMapping(value = "/findUserByPhoneOrName/{param}/{pageindex}/{pagesize}", method = RequestMethod.GET, produces = {
			"application/json", "application/xml" })
	public @ResponseBody Result<List<BaseMember>> findUserByPhoneOrName(
			@ApiParam("手机后4位或姓名首字母（不区分大小写）") @PathVariable String param,
			@ApiParam("页码") @PathVariable Integer pageindex, @ApiParam("显示数量") @PathVariable Integer pagesize)
			throws Exception {
		Result<List<BaseMember>> result = new Result<List<BaseMember>>();
		try {
			if (param != null && !"".equals(param) && !"{param}".equals(param)) {
				Double pagecount = Math
						.ceil(memberService.findUserCountByPhoneOrName(param.toLowerCase()) / (double) pagesize);
				result.setPagecount(pagecount.intValue());
				List<BaseMember> listUser = memberService.findUserByPhoneOrName(param.toLowerCase(), pageindex,
						pagesize);
				result.setContent(listUser);
			} else {
				result.setCode(1001);
				result.setMsg("参数错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(5000);
			result.setMsg("服务端异常！");
			throw e;
		}
			return result;

	}

    @ApiOperation(value = "访客登记接口", notes = "记录访客登记信息", response = Result.class)
	@Transactional
	@RequestMapping(value = "/saveVisitorInfo", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" }, consumes = { "application/json", "application/xml" })
	public @ResponseBody Result<Map<String, Object>> saveVisitorInfo(
			@ApiParam("来访记录对象") @RequestBody BaseAccessRecord record) throws Exception {
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		try {
			record.setCreatetime(new Date());
			result = accessRecordService.SaveAccessRecord(record);
			MQRegFace mQRegFace = new MQRegFace(null, record.getVisitor().getName(), record.getCardnum(),
					record.getSitephoto());
			sender.regFace(mQRegFace);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 手动回滚事物
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setCode(5000);
			result.setMsg("登记失败" + e.getMessage());
			logger.error("访客登记失败", e);
		}
		return result;
	}

	@ApiOperation(value = "添加随行人员", notes = "添加随行人员")
	@Transactional
	@RequestMapping(value = "/addEntourage", method = RequestMethod.POST, produces = { "application/json",
			"application/xml" }, consumes = { "application/json", "application/xml" })
	public @ResponseBody Result<Map<String, Object>> addEntourage(
			@ApiParam("来访记录对象") @RequestBody BaseAccessRecord record) throws Exception {
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();
		try {
			result = accessRecordService.addEntourage(record);
			MQRegFace mQRegFace = new MQRegFace(null, record.getVisitor().getName(), record.getCardnum(),
					record.getSitephoto());
			sender.regFace(mQRegFace);
		} catch (Exception e) {
			e.printStackTrace();
			// 手动回滚事物
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			result.setCode(5000);
			result.setMsg("服务端异常！");
		}
		return result;
	}

	@ApiOperation(value = "来访事由列表", notes = "获取来访事由列表")
	@RequestMapping(value = "/findReasons", method = RequestMethod.GET, produces = { "application/json",
			"application/xml" })
	public @ResponseBody Result findReasons() {
		Result result = new Result();
		try {
			// 获取来访事由列表
			result.setContent(visitorReasonsService.list());
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(5000);
			result.setMsg("服务端异常！");
		}
		return result;

	}

	 @ApiOperation(value = "来访记录查询", notes = "来访记录查询")
	    @Transactional
	    @RequestMapping(value = "/findAccessRecord", method = RequestMethod.POST, produces = {
	            "application/json", "application/xml"}, consumes = {
	            "application/json", "application/xml"})
	    public
	    @ResponseBody
	    Result<List<BaseAccessRecord>> findAccessRecord(
	            @ApiParam("来访记录对象") @RequestBody BaseAccessRecord record)
	            throws Exception {
	        Result<List<BaseAccessRecord>> result = new Result<List<BaseAccessRecord>>();
	        try {
	            if (record.getPageNum() == null || record.getPageNum() <= 0) {
	                record.setPageNum(1);
	            }
	            if (record.getNum() == null || record.getNum() <= 0) {
	                record.setNum(10);
	            }

	            // 获取总页数
	            int pageCount = (int) Math.ceil(accessRecordService
	                    .findAccessRecordPageCount(record) / 10d);

	            result.setPagecount(pageCount);

	            result.setPageNum(record.getPageNum());

	            // 获取指定页数的数据
	            result.setContent(accessRecordService.findAccessRecord(record));

	        } catch (Exception e) {
	            e.printStackTrace();
	            result.setCode(5000);
	            result.setMsg("服务端异常！");
	            throw e;
	        } finally {
	            return result;
	        }
	    }

	 @ApiOperation(value = "根据卡号查询有效的访客记录", notes = "根据卡号查询有效的访客记录")
	    @RequestMapping(value = "/findAccessRecordByCardNum/{cardNum}", method = RequestMethod.GET, produces = {
	            "application/json", "application/xml"})
	    public
	    @ResponseBody
	    Result<BaseAccessRecord> findAccessRecordByCardNum(
	            @ApiParam("卡号") @PathVariable String cardNum)
	            throws Exception {
	        Result<BaseAccessRecord> result = new Result<BaseAccessRecord>();
	        try {
	        	BaseAccessRecord ar = accessRecordService.findAccessRecordByCardNum(cardNum);
	        	if(ar != null)
	        	{
	        		result.setContent(ar);
	        	}
	        	else
	        	{
	        		result.setCode(1001);
	                result.setMsg("没有找到有效的访客记录");
	        	}

	        } catch (Exception e) {
	            e.printStackTrace();
	            result.setCode(5000);
	            result.setMsg("服务端异常！");
	            throw e;
	        } finally {
	            return result;
	        }
	    }


    @ApiOperation(value = "指定部门员工列表", notes = "根据部门ID员查询该部门可被访问的工")
    @RequestMapping(value = "/findEmployeesById/{depId}/{pageindex}/{pagesize}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<List<BaseMember>> findEmployeesById(
            @ApiParam("部门ID") @PathVariable Integer depId,
            @ApiParam("页码") @PathVariable Integer pageindex,
            @ApiParam("显示数量") @PathVariable Integer pagesize) throws Exception {
        Result<List<BaseMember>> result = new Result<List<BaseMember>>();
        try {
            if (depId == null || depId == 0) {
                result.setCode(1001);
                result.setMsg("部门ID错误！");
            } else {
                Double pagecount = Math.ceil(memberService.findEmployeesCountById(depId)
                        / (double) pagesize);
                result.setPagecount(pagecount.intValue());
                result.setContent(memberService.findEmployeesById(depId, pageindex,
                        pagesize));
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * <p>
     * Title:指定部门员工列表
     * </p>
     * <p>
     * Description:根据部门ID查询该部门可被访问的员工
     * </p>
     *
     * @param depId 部门ID
     * @return
     */
    @ApiOperation(value = "指定部门下含子部门员工列表", notes = "根据部门ID查询该部门含子部门可被访问的员工")
    @RequestMapping(value = "/findSubDeptsEmployeesById/{depId}/{pageindex}/{pagesize}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<List<BaseMember>> findSubDeptsEmployeesById(
            @ApiParam("部门ID") @PathVariable Integer depId,
            @ApiParam("页码") @PathVariable Integer pageindex,
            @ApiParam("显示数量") @PathVariable Integer pagesize) throws Exception {
        Result<List<BaseMember>> result = new Result<List<BaseMember>>();
        try {
            if (depId == null || depId == 0) {
                result.setCode(1001);
                result.setMsg("部门ID错误！");
            } else {
                Double pagecount = Math.ceil(memberService.findSubDeptEmployeesCountById(depId)
                        / (double) pagesize);
                result.setPagecount(pagecount.intValue());
                result.setContent(memberService.findSubDeptEmployeesById(depId, pageindex,
                        pagesize));
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * <p>
     * Title:根据访客身份证号码最近访问人员列表
     * </p>
     * <p>
     * Description:根据访客身份证号码最近访问人员列表
     * </p>
     *
     * @param idnum 部门ID
     * @return
     */
    @ApiOperation(value = "根据访客身份证号码最近访问人员列表", notes = "根据访客身份证号码最近访问人员列表")
    @RequestMapping(value = "/findEmployeesByIdnum/{idnum}/{pageindex}/{number}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<List<BaseMember>> findEmployeesByIdnum(
            @ApiParam("身份证号码") @PathVariable String idnum,
            @ApiParam("页码") @PathVariable Integer pageindex,
            @ApiParam("查询数量") @PathVariable Integer number) throws Exception {
        Result<List<BaseMember>> result = new Result<List<BaseMember>>();
        try {
            if (idnum == null || idnum == "") {
                result.setCode(1001);
                result.setMsg("身份证号码不能为空！");
            } else {
                if (number == null || number <= 0) {
                    result.setCode(1001);
                    result.setMsg("查询数量不能小于1！");
                } else {
                    Double pagecount = Math.ceil(memberService.findEmployeesCountByIdnum(idnum)
                            / (double) number);
                    result.setPagecount(pagecount.intValue());
                    //result.setPagecount(0);
                    result.setContent(memberService.findEmployeesByIdnum(idnum, pageindex, number));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * <p>
     * Title:部门列表
     * </p>
     * <p>
     * Description:获取所有部门信息列表
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "部门列表", notes = "获取所有部门信息列表")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @RequestMapping(value = "/findDepartment", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<List<UUserDep>> findDepartment(
            @RequestParam(value = "companyNum", required = false) String companyNum)
            throws Exception {
        Result<List<UUserDep>> result = new Result<List<UUserDep>>();
        try {
            result.setContent(memberService.findDepartment(companyNum));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * <p>
     * Title:部门列表
     * </p>
     * <p>
     * Description:根据拼音获取所有部门信息列表
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "根据拼音获取部门列表", notes = "根据拼音获取所有部门信息列表")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @RequestMapping(value = "/findDepartmentByPY", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<List<UUserDep>> findDepartmentByPY(
            @RequestParam(value = "companyNum", required = false) String companyNum, @RequestParam(value = "pinyin", required = false) String pinyin)
            throws Exception {
        Result<List<UUserDep>> result = new Result<List<UUserDep>>();
        try {
            result.setContent(memberService.findDepartmentByPY(companyNum, pinyin));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }


    /**
     * <p>
     * Title:部门列表
     * </p>
     * <p>
     * Description:根据拼音获取所有部门信息列表
     * </p>
     *
     * @return
     */
    @ApiOperation(value = "根据ID获取部门列表", notes = "根据ID获取所有部门信息列表")
    @ApiResponse(code = 200, message = "success", response = Result.class)
    @RequestMapping(value = "/findDepartmentByID", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<UUserDep> findDepartmentByID(@RequestParam(value = "deptId", required = false) Integer deptId)
            throws Exception {
        Result<UUserDep> result = new Result<UUserDep>();
        try {
            result.setContent(memberService.findDepartmentByID(deptId));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * <p>
     * Title:根据来访人员身份证号查询来访人员信息
     * </p>
     * <p>
     * Description:根据来访人员身份证号查询来访人员信息
     * </p>
     *
     * @param idNum 身份证号
     * @return
     */
    @ApiOperation(value = "来访人员信息（身份证号）", notes = "根据来访人员身份证号查询来访人员信息")
    @RequestMapping(value = "/findVisitorByIdNum/{idNum}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<BaseVisitor> findVisitorByIdNum(
            @ApiParam("证件ID") @PathVariable String idNum)  {
        Result<BaseVisitor> result = new Result<>();
        try {
            final BaseVisitor visitorByIdNum = memberService.findVisitorByIdNum(idNum);
            BaseVisitor v = visitorByIdNum;
            if (v == null) {
                result.setCode(1001);
                result.setMsg("无来访人信息！");
                return result;
            }
            result.setContent(v);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * <p>
     * Title:根据来访人员Id查询来访人员信息
     * </p>
     * <p>
     * Description:根据来访人员Id查询来访人员信息
     * </p>
     *
     * @param id 标识
     * @return
     */
    @ApiOperation(value = "来访人员信息（Id）", notes = "根据来访人员Id查询来访人员信息")
    @RequestMapping(value = "/findVisitorById/{id}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<BaseVisitor> findVisitorById(
            @ApiParam("ID") @PathVariable Integer id)  {
        Result<BaseVisitor> result = new Result<>();
        try {
            final BaseVisitor visitorByIdNum = memberService.findVisitorById(id);
            BaseVisitor v = visitorByIdNum;
            if (v == null) {
                result.setCode(1001);
                result.setMsg("无来访人信息！");
                return result;
            }
            result.setContent(v);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    @ApiOperation(value = "来访人员信息（二维码或一维码信息）", notes = "根据来访人员凭证二维码或一维码信息查询未注销来访人员信息")
    @RequestMapping(value = "/findVisitorByRCode/{rCode}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<BaseVisitor> findVisitorByRCode(
            @ApiParam("条码信息") @PathVariable String rCode)  {
        Result<BaseVisitor> result = new Result<>();
        try {
            result.setContent(memberService.findVisitorByRCode(rCode));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    @ApiOperation(value = "来访人员信息（IC卡号）", notes = "根据来访人员IC卡号查询未注销来访人员信息")
    @RequestMapping(value = "/findVisitorByICNum/{icNum}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<BaseVisitor> findVisitorByICNum(
            @ApiParam("ic卡号") @PathVariable String icNum) throws Exception {
        Result<BaseVisitor> result = new Result<BaseVisitor>();
        try {
            BaseVisitor v = memberService.findVisitorByIcNum(icNum);
            if (v == null) {
                result.setCode(1001);
                result.setMsg("卡片未使用！");
                return result;
            }
            result.setContent(v);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }


    @ApiOperation(value = "来访人员信息带被访人id（IC卡号）", notes = "根据来访人员IC卡号查询未注销来访人员信息")
    @RequestMapping(value = "/findVisitorAccessByICNum/{icNum}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<BaseAccessRecord> findVisitorAccessByICNum(
            @ApiParam("ic卡号") @PathVariable String icNum)  {
        Result<BaseAccessRecord> result = new Result<>();
        try {
            BaseAccessRecord v = memberService.findVisitorAccessByICNum(icNum);
            if (v == null) {
                result.setCode(1001);
                result.setMsg("卡片未使用！");
                return result;
            }
            result.setContent(v);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    /**
     * <p>
     * Title:注销门禁卡
     * </p>
     * <p>
     * Description:
     * </p>
     *
     * @param cardNum 卡号
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "注销门禁卡", notes = "门禁卡注销")
    @Transactional
    @RequestMapping(value = "/recoverCard/{cardNum}", method = RequestMethod.POST, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result recoverCard(
            @ApiParam("ic卡号") @PathVariable String cardNum) throws Exception {
        Result result = new Result();
        try {
            // 查询卡片是什么状态
            List<BaseAccessRecord> arlist = memberService.checkCardIsActive(cardNum);
            if (arlist != null && arlist.size() > 0) {
                BaseAccessRecord ar = arlist.get(0);
                if (new Date().compareTo(DateTime.stringToDate(ar.getEndtime(),
                        "yyyy-MM-dd HH:mm:ss")) > 0) {
                    ar.setStatus(1);// 超时注销
                } else {
                    ar.setStatus(2);// 正常结束
                }
                ar.setLogofftime(DateTime.getNow());
                memberService.recoverCard(ar);

                String isopenweixin = IConfig.get("isopenweixin");
                if (isopenweixin.equals("1")) {
                    // 如果是微信预约则将线上的预约状态修改为已注销状态
                    if (ar.getYyid() != null && ar.getYyid() != 0) {
                        String uri = IConfig.get("wx.updatestatusuri")
                                + "state=2&id=" + ar.getYyid();
                        String response = HTTP.get(uri);
                        if (response != null) {
                            // 将字符串转换成json对象
                            JSONObject json = JSONObject.parseObject(response);
                        }
                    }
                }

//                //注销成功如果开启人脸注册则同时注销人脸
//                visitorServer.CancelFaceReg(ar);
//                visitorServer.CancelCar(ar);
//                visitorServer.CancelFinger(ar);
//                //清空进出控制
//                oneinOneoutService.deleteRecord(cardNum);
            } else {
                result.setMsg("卡片可用无须注销！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }




    @ApiOperation(value = "获取当天出/未出门人数", notes = "获取当天出/未出门人数")
    @RequestMapping(value = "/findTodayIntoAndOutNum", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<Map<String, Integer>> findTodayIntoAndOutNum()
            throws Exception {
        Result<Map<String, Integer>> result = new Result<Map<String, Integer>>();

        try {

            String key = "findTodayIntoAndOutNum";

            Map<String, Integer> map = new HashMap<>();
            if (VideoCallBackCache.exists(key)) {
                String json = VideoCallBackCache.get(key);
                if (StringUtils.isNotBlank(json)) {
                    map = JSONObject.parseObject(json, HashedMap.class);
                }

            } else {
                map = memberService.findTodayIntoAndOutNum(DateTime
                        .getNowDate());
                // 查询当天出/入门人数
                try {
                    if (map != null) {
                        VideoCallBackCache.setex(key, JSONObject.toJSONString(map), 5);
                    }
                } catch (Exception ex) {

                }
            }

            if (map == null) {
                Map<String, Integer> ion = new HashMap<String, Integer>();
                ion.put("intoNum", 0);
                ion.put("outNum", 0);
                map = ion;
            }

            result.setContent(map);


        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    @ApiOperation(value = "带入/带出物品选项列表", notes = "获取带入/带出物品选项列表")
    @RequestMapping(value = "/findDraginAndTakeoutRes", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result findDraginAndTakeoutRes() throws Exception {
        Result result = new Result();
        try {
            // 获取来访事由列表
            result.setContent(memberService.findDraginAndTakeoutRes());
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }
//
    @ApiOperation(value = "根据身份证号码获取员工信息", notes = "根据身份证号码获取员工信息")
    @RequestMapping(value = "/findUserByIdno/{param}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<BaseMember> findUserByIdno(
            @ApiParam("根据身份证号码获取员工信息") @PathVariable String param) throws Exception {
        Result<BaseMember> result = new Result<>();
        try {
            if (param != null && !"".equals(param) && !"{param}".equals(param)) {
                BaseMember listUser = memberService.findUserByIdno(param);
                result.setContent(listUser);
            } else {
                result.setCode(1001);
                result.setMsg("参数错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    @ApiOperation(value = "来访人员来访状态查询", notes = "根据身份证号码查询来访人当前处在什么状态（0：正常 1：在黑名单 2：未注销访问 3：已预约）")
    @Transactional
    @RequestMapping(value = "/findVisitingStateByIdNum/{idNum}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result findVisitingStateByIdNum(
            @ApiParam("证件ID") @PathVariable String idNum) throws Exception {
        Result result = new Result();
        try {
//            // 查询来访人是否在黑名单
//            List<BaseVisitor> list = memberService.findBlackListByIdNum(idNum);
//            // 查看此人是否在公共黑名单
//            List<BaseBlacklistShare> bs = memberService.findBlackShare(idNum);
//            if ((list != null && list.size() > 0)
//                    || (bs != null && bs.size() > 0)) {// 在黑名单
//                result.setCode(1);
//                result.setMsg("警告！此人在黑名单！");
//                if (list != null && list.size() > 0) {
//                    result.setContent(list.get(0));
//                }
//                return result;
//            }
//
//            // 查询来访人员是否预约
//            list = memberService.isAppointment(idNum);
//            if (list != null && list.size() > 0) {// 有预约
//                result.setCode(3);
//                result.setMsg("您已预约！");
//                // 获取预约信息（访客ID）
//                result.setContent(memberService
//                        .findAccessRecordByVid(list.get(0).getId()));
//                return result;
//            } else {// 本地库没有预约,查询线上是否有预约
//                String isopenweixin = IConfig.get("isopenweixin");
//                if (isopenweixin.equals("1")) {
//                    System.out.println("本地库没有预约,查询线上是否有预约");
//                    String uri = IConfig.get("wx.getyyuri") + "=" + idNum;
//                    System.out.println("微信预约查询uri:" + uri);
//                    String response = HTTP.get(uri);
//                    System.out.println("查询返回结果：" + response);
//                    if (response != null) {
//                        // 将字符串转换成json对象
//                        JSONObject json = JSONObject.parseObject(response);
//                        if (json != null && json.getIntValue("code") == 1) {
//
//                            // 根据手机号码查询预约员工是否存在
//                            BaseMember user = memberService.findUserByPhoneNum(json.getJSONObject(
//                                    "content").getString("EmployeeTel"));
//                            System.out.println("被约人信息："
//                                    + JSONObject.toJSONString(user));
//                            if (user != null) {// 被约人存在
//                                // 将访客信息更新到访客信息表
//                                BaseVisitor v = new BaseVisitor();
//                                v.setIdnum(json.getJSONObject("content").getString(
//                                        "IdentityNum"));
//                                v.setAddress(json.getJSONObject("content")
//                                        .getString("Address"));
//                                v.setSex("1".equals(json.getJSONObject("content")
//                                        .getString("Sex")) ? "男" : "女");
//                                v.setNation(json.getJSONObject("content")
//                                        .getString("Nation"));
//                                v.setName(json.getJSONObject("content").getString(
//                                        "Name"));
//                                v.setPhone(json.getJSONObject("content").getString(
//                                        "VisitorTel"));
//                                System.out.println("访客信息："
//                                        + JSONObject.toJSONString(v));
//                                if (memberService.updateVisitorInfo(v) > 0) {// 访客信息更新成功
//                                    // 将预约信息更新到来访记录表
//                                    BaseAccessRecord r = new BaseAccessRecord();
//                                    r.setCardnum(json.getJSONObject("content")
//                                            .getString("CardNum"));
//                                    r.setCreatetime(new Date());
//                                    r.setEndtime(json.getJSONObject("content")
//                                            .getString("OrderEndDate"));
//                                    r.setStarttime(json.getJSONObject("content")
//                                            .getString("OrderDate"));
//                                    r.setReasons(json.getJSONObject("content")
//                                            .getString("Reasons"));
//                                    r.setVid(v.getId());
//                                    r.setVisitor(v);
//                                    r.setUid(Integer.parseInt(user.getId() + ""));
//                                    r.setUser(user);
//                                    r.setType(1);
//                                    r.setStatus(3);
//                                    r.setYyid(Integer.parseInt(json.getJSONObject(
//                                            "content").getString("Id")));
//                                    System.out.println("预约记录信息："
//                                            + JSONObject.toJSONString(r));
//                                    // 查询预约信息是否存在
//                                    List<BaseAccessRecord> re = memberService.isAccessRecord(r);
//                                    if (re == null || re.size() == 0) {
//                                        if (memberService.updateAccessRecord(r) > 0) {
//                                            result.setCode(3);
//                                            result.setMsg("您已预约！");
//                                            // 获取预约信息（访客ID）
//                                            List<BaseAccessRecord> rlist = new ArrayList<BaseAccessRecord>();
//                                            rlist.add(r);
//                                            result.setContent(rlist);
//                                            return result;
//                                        }
//                                    } else {
//                                        if (re.get(0).getStatus() == 3) {
//                                            result.setCode(3);
//                                            result.setMsg("您已预约！");
//                                            // 获取预约信息（访客ID）
//                                            result.setContent(re);
//                                            return result;
//                                        }
//                                    }
//                                }
//                            }
//                        } else {
//                            // 查询报错
//
//                        }
//                    }
//
//                }
//            }
            // 查询来访人员是否注销
            List<BaseVisitor> record = memberService.isLogOff(idNum);
            if (record != null && record.size() > 0) {// 没有注销访问
                result.setCode(2);
                result.setMsg("未注销访问！");
                result.setContent(record.get(0));
                return result;
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }

    @ApiOperation(value = "来访人员上次来访信息", notes = "根据来访人证件号码查询上次来访信息")
    @RequestMapping(value = "/findLastAccessRecordByIdNum/{idNum}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result findLastAccessRecordByIdNum(
            @ApiParam("证件ID") @PathVariable String idNum) throws Exception {
        Result result = new Result();
        try {
            if (idNum == null || "".equals(idNum.trim())) {
                result.setCode(1001);
                result.setMsg("证件号不能为空！");
                return result;
            }
            // 查询上次访客记录
            BaseAccessRecord ar = memberService.findLastAccessRecordByIdNum(idNum);
            if (ar == null) {
                result.setCode(1001);
                result.setMsg("新来访客！");
                return result;
            }
            result.setContent(ar);

        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }
//
//    @ApiOperation(value = "来访人员上次来访信息列表", notes = "根据来访人证件号码查询上次来访信息列表")
//    @RequestMapping(value = "/findLastAccessRecordListByIdNum/{idNum}/{number}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result findLastAccessRecordListByIdNum(
//            @ApiParam("证件ID") @PathVariable String idNum, @ApiParam("查询数量") @PathVariable Integer number) throws Exception {
//        Result result = new Result();
//        try {
//            if (idNum == null || "".equals(idNum.trim())) {
//                result.setCode(1001);
//                result.setMsg("证件号不能为空！");
//                return result;
//            }
//            if (number <= 0) {
//                result.setCode(1001);
//                result.setMsg("查询数量不能小于1！");
//                return result;
//            }
//            // 查询上次访客记录
//            List<AccessRecord> ar = fts.findLastAccessRecordListByIdNum(idNum, number);
//            if (ar == null || ar.size() < 1) {
//                result.setCode(1001);
//                result.setMsg("新来访客！");
//                return result;
//            }
//            result.setContent(ar);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
//    @ApiOperation(value = "预约登记", notes = "微信预约登记")
//    @Transactional
//    @RequestMapping(value = "/wxyydj", method = RequestMethod.POST, produces = {
//            "application/json", "application/xml"}, consumes = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result wxyydj(
//            @ApiParam("预约信息") @RequestBody AccessRecord record)
//            throws Exception {
//        System.out.println("预约登记");
//        System.out.println("请求数据：" + JSONObject.toJSONString(record));
//        Result result = new Result();
//        result.setCode(1001);
//        result.setMsg("二维码无效！");
//        try {
//            if (record != null && !StringUtils.isBlank(record.getRcode())) {
//                //预约卡号
//                // 判断预约是否存在.
//                AccessRecord re = fts.selectbyrcode(record.getRcode());
//                if (re != null && !StringUtils.isBlank(re.getId())) {// 预约存在
//                    result.setCode(0);
//                    result.setMsg("您已预约！");
//                    result.setContent(re);
//                    return result;
//                } else {
//                    result.setCode(1001);
//                    result.setMsg("无效的二维码！");
//                    return result;
//                }
//            } else {
//                //判断离线二维码
//                if (record == null || record.getUser() == null
//                        || record.getVisitor() == null) {
//                    result.setCode(1001);
//                    result.setMsg("参数错误！");
//                    return result;
//                }
//                // 判断预约是否存在.
//                List<AccessRecord> re = fts.isAccessRecord(record);
//                if (re != null && re.size() > 0) {// 预约存在
//
//                    if (re.get(0).getStatus() == 3) {
//                        result.setCode(0);
//                        result.setMsg("您已预约！");
//                        return result;
//                    }
//                } else {// 预约不存在
//                    // 检查被约人是否存在
//                    UUser user = fts
//                            .findUserByPhoneNum(record.getUser().getPhone());
//                    if (user == null) {
//                        result.setCode(1001);
//                        result.setMsg("无效的二维码！");
//                        return result;
//                    } else {// 被约人存在
//                        // 将访客信息更新到访客信息表
//                        if (fts.updateVisitorInfo(record.getVisitor()) > 0) {// 访客信息更新成功
//                            // 将预约信息更新到来访记录表
//                            record.setVid(record.getVisitor().getId());
//                            record.setUid(Integer.parseInt(user.getId() + ""));
//                            record.setUser(user);
//                            record.setType(1);
//                            record.setStatus(3);
//                            if (fts.updateAccessRecord(record) > 0) {
//                                result.setCode(0);
//                                result.setMsg("您已预约！");
//                                return result;
//                            }
//                        }
//
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
//    @ApiOperation(value = "终端值守人员登录（密码）", notes = "终端值守人员使用密码登录")
//    @Transactional
//    @RequestMapping(value = "/loginTerminalpwd", method = RequestMethod.POST, produces = {
//            "application/json", "application/xml"}, consumes = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result loginTerminalpwd(
//            @ApiParam("值守人员登录信息") @RequestBody UUser user) throws Exception {
//        System.out.println("终端值守人员登录（密码）");
//        System.out.println("请求数据：" + JSONObject.toJSONString(user));
//        Result result = new Result();
//        try {
//            if (user.getId() == null || user.getId() == 0
//                    || user.getTerminalpswd() == null) {
//                result.setCode(1001);
//                result.setMsg("ID或密码不能为空！");
//            } else {
//                UUser u = fts.loginTerminalpwd(user);
//                if (u == null) {
//                    result.setCode(1001);
//                    result.setMsg("密码错误或未设置密码！");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
//    @ApiOperation(value = "终端值守人员登录（刷身份证）", notes = "终端值守人员使用身份证登录")
//    @Transactional
//    @RequestMapping(value = "/loginTerminalID", method = RequestMethod.POST, produces = {
//            "application/json", "application/xml"}, consumes = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result loginTerminalID(
//            @ApiParam("值守人员身份证信息") @RequestBody UUser user) throws Exception {
//        System.out.println("终端值守人员登录（刷身份证）");
//        System.out.println("请求数据：" + JSONObject.toJSONString(user));
//        Result result = new Result();
//        try {
//            if (user.getIdNum() == null) {
//                result.setCode(1001);
//                result.setMsg("身份证号码不能为空！");
//            } else {
//                UUser u = fts.loginTerminalID(user);
//                if (u == null) {
//                    result.setCode(1001);
//                    result.setMsg("用户身份证未关联！");
//                } else {
//                    result.setCode(0);
//                    result.setContent(u);
//                    result.setMsg("登陆成功！");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
//    @ApiOperation(value = "网页IM审批提交接口", notes = "提交IM审批")
//    @Transactional
//    @RequestMapping(value = "/subimifo", method = RequestMethod.POST, produces = {
//            "application/json", "application/xml"}, consumes = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result subimifo(
//            @ApiParam("审批内容") @RequestBody ImApprove imApprove)
//            throws Exception {
//        System.out.println("网页IM审批提交");
//        System.out.println("请求数据：" + JSONObject.toJSONString(imApprove));
//        Result result = new Result();
//        try {
//            if (imApprove == null) {
//                result.setCode(1001);
//                result.setMsg("审批信息不能为空！");
//            } else if (imApprove.getUphone() == null
//                    || imApprove.getUid() == null
//                    || imApprove.getUname() == null
//                    || imApprove.getVidnum() == null
//                    || imApprove.getVname() == null
//                    || imApprove.getCompanycode() == null) {
//                result.setCode(1001);
//                result.setMsg("提交失败，审批信息不全！");
//            } else {
//
//                // 提交审批
//                ImApprove ia = fts.subimifo(imApprove);
//                if (ia != null && ia.getId() != null) {
//                    result.setContent(ia.getId());
//
//                } else {
//                    result.setCode(1001);
//                    result.setMsg("提交失败！");
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
//    @ApiOperation(value = "获取网页IM审批状态（审批id）", notes = "根据审批id获取审批状态（0：未审核 1：审核通过 2：审核不通过）")
//    @Transactional
//    @RequestMapping(value = "/getimstatus/{id}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result getimstatus(
//            @ApiParam("审批ID") @PathVariable Long id) throws Exception {
//        System.out.println("网页IM审批提交");
//        System.out.println("请求数据：" + id);
//        Result result = new Result();
//        try {
//            if (id == null) {
//                result.setCode(1001);
//                result.setMsg("审批id不能为空！");
//            } else {
//                ImApprove ia = fts.getImStatus(id);
//                if (ia == null) {
//                    result.setCode(1001);
//                    result.setMsg("该审批不存在！");
//                } else {
//                    result.setContent(ia.getStatus());
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
//
    @ApiOperation(value = "获取平台版本号", notes = "获取平台版本号")
    @Transactional
    @RequestMapping(value = "/getplatformversion", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public
    @ResponseBody
    Result getplatformversion() throws Exception {

        Result result = new Result();
        try {
            result.setCode(0);
            String rms_version = IConfig.get("rms_version");
            result.setContent(rms_version);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }
//
//
//    @ApiOperation(value = "查询所有员工", notes = "查询所有员工")
//    @RequestMapping(value = "/findAllEmployees", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<UUser>> findAllEmployees() throws Exception {
//        Result<List<UUser>> result = new Result<List<UUser>>();
//        try {
//            result.setCode(0);
//            result.setContent(fts.findAllEmployees());
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
    @ApiOperation(value = "根据id查询员工", notes = "根据id查询员工")
    @RequestMapping(value = "/findEmployeesById/{id}", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result<BaseMember> findEmployeesById(@ApiParam("员工ID") @PathVariable Long id) throws Exception {
        Result<BaseMember> result = new Result<>();
        try {
            result.setCode(0);
            BaseMember user = memberService.findUserInfoById(id);
            BaseNatiom uUserNation = baseNatiomMapper.selectByPrimaryKey(user.getNation());
            user.setNations(uUserNation);
            result.setContent(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            result.setCode(5000);
            result.setMsg("服务端异常！");
            throw e;
        } finally {
            return result;
        }
    }
//
//
//    /**
//     * 指定日期访客进出刷卡情况
//     *
//     * @param dayNum
//     * @return
//     */
//    @ApiOperation(value = "指定日期访客进出刷卡情况", notes = "指定日期访客进出刷卡情况")
//    @RequestMapping(value = "/getvisitordayslidcardreport/{dayNum}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<String[]>> getvisitordayslidcardreport(@ApiParam("日期") @PathVariable String dayNum) {
//
//        Result<List<String[]>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(dayNum)) {
//                Date date = new Date();
//                dayNum = DateTime.Date2String(date, "yyyy-MM-dd");
//            }
//            List<VisitorReport> list = visitorReportService.GetDayVisitor(dayNum);
//            List<VisitorReport> inList = list.stream().filter(w -> w.getIsOut() == 1).collect(Collectors.toList());
//            List<VisitorReport> outList = list.stream().filter(w -> w.getIsOut() != 1).collect(Collectors.toList());
//            List<String[]> hournum = new ArrayList<>();
//            //未刷出人数
//            Integer shuachucount = 0;
//            //累计刷入人数
//            Integer shuaRucount = 0;
//            for (int m = 0; m <= 23; m++) {
//                String hourn = String.valueOf(m);
//                int outpersoncount = outList.stream().filter(w -> w.getHourNum().equals(hourn)).mapToInt(w -> w.getPersonNum()).sum();
//                shuachucount += outpersoncount;
//                int inpersoncount = inList.stream().filter(w -> w.getHourNum().equals(hourn)).mapToInt(w -> w.getPersonNum()).sum();
//                shuaRucount += inpersoncount;
//                String[] array = new String[3];
//                array[0] = String.valueOf(inpersoncount);
//                array[1] = String.valueOf(outpersoncount);
//                Integer inpecounttem = shuaRucount - shuachucount;
//                Integer inpcount = inpecounttem > 0 ? inpecounttem : 0;
//                array[2] = String.valueOf(inpcount);
//                hournum.add(array);
//            }
//            result.setCode(0);
//            result.setContent(hournum);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//
//    }
//
//    /**
//     * 指定日期员工根据刷卡流水判断在岗情况
//     *
//     * @param dayNum
//     * @return
//     */
//    @ApiOperation(value = "指定日期员工根据刷卡流水判断在岗情况", notes = "指定日期员工根据刷卡流水判断在岗情况")
//    @RequestMapping(value = "/getemployeedayslidcardreport/{dayNum}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<String[]>> getemployeedayslidcardreport(@ApiParam("日期") @PathVariable String dayNum) {
//
//        Result<List<String[]>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(dayNum)) {
//                Date date = new Date();
//                dayNum = DateTime.Date2String(date, "yyyy-MM-dd");
//            }
//
//            List<String[]> hournum = new ArrayList<>();
//
//            List<VisitorReport> list = visitorReportService.GetDayEmployee(dayNum);
//            //打卡进
//            List<VisitorReport> inList = list.stream().filter(w -> w.getIsOut() == 1).collect(Collectors.toList());
//            //打卡出
//            List<VisitorReport> outList = list.stream().filter(w -> w.getIsOut() != 1).collect(Collectors.toList());
//            //公司总人数
//            Integer companypsersoncount = visitorReportService.GetAllUserCount();
//            //进入的人卡集合
//            List<String> incardList = new ArrayList<>();
//
//            for (int m = 0; m <= 23; m++) {
//                String hourn = String.valueOf(m);
//                List<VisitorReport> outperson = outList.stream().filter(w -> hourn.equals(w.getHourNum())).collect(Collectors.toList());
//                List<VisitorReport> inperson = inList.stream().filter(w -> hourn.equals(w.getHourNum())).collect(Collectors.toList());
//                for (int i = 0; i < inperson.size(); i++) {
//                    int index = i;
//                    String cardNum = inperson.get(index).getCardNum();
//                    Optional<String> ishas = incardList.stream().filter(w -> cardNum.equals(w)).findAny();
//                    if (!ishas.isPresent()) {
//                        //进入
//                        incardList.add(cardNum);
//                    }
//                }
//                for (int i = 0; i < outperson.size(); i++) {
//                    int index = i;
//                    String cardNum = outperson.get(index).getCardNum();
//                    Optional<String> ishas = incardList.stream().filter(w -> cardNum.equals(w)).findAny();
//                    if (ishas.isPresent()) {
//                        //存在则移出
//                        incardList.removeIf(w -> cardNum.equals(w));
//                    }
//                }
//
//                String[] array = new String[2];
//                Integer zaigang = incardList.size();
//                array[0] = String.valueOf(zaigang);
//                Integer buzaigangtemp = companypsersoncount - zaigang;
//                Integer buzaigang = buzaigangtemp >= 0 ? buzaigangtemp : 0;
//                array[1] = String.valueOf(buzaigang);
//                hournum.add(array);
//            }
//            result.setCode(0);
//            result.setContent(hournum);
//        } catch (Exception ex) {
//
//            logger.error("指定日期员工根据刷卡流水判断在岗情况异常" + ex.getMessage());
//
//
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//
//
//    /**
//     * 年度访客情况
//     *
//     * @param yearNum
//     * @return
//     */
//    @ApiOperation(value = "年度访客情况", notes = "年度访客情况")
//    @RequestMapping(value = "/getvisitoryearreport/{yearNum}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<Integer>> getvisitoryearreport(@ApiParam("年") @PathVariable String yearNum) {
//        Result<List<Integer>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(yearNum)) {
//                Date date = new Date();
//                yearNum = DateTime.Date2String(date, "yyyy");
//            }
//
//            List<VisitorReport> list = visitorReportService.GetYearVisitor(yearNum);
//            List<Integer> monthnum = new ArrayList<>();
//            for (int m = 1; m <= 12; m++) {
//                String month = String.valueOf(m);
//                int personcount = list.stream().filter(w -> month.equals(w.getMonthNum())).mapToInt(w -> w.getPersonNum()).sum();
//                monthnum.add(personcount);
//            }
//            result.setCode(0);
//            result.setContent(monthnum);
//        } catch (Exception ex) {
//
//            logger.error("年度访客情况异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//
//
//    /**
//     * 月份每天访客登记情况
//     *
//     * @param yearMothNum
//     * @return
//     */
//    @ApiOperation(value = "月份每天访客登记情况", notes = "月份每天访客登记情况 201701")
//    @RequestMapping(value = "/getvisitormothreport/{yearMothNum}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<Integer>> getvisitormothreport(@ApiParam("月份") @PathVariable String yearMothNum) {
//        Result<List<Integer>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(yearMothNum)) {
//                Date date = new Date();
//                yearMothNum = DateTime.Date2String(date, "yyyyMM");
//            }
//
//            List<VisitorReport> list = visitorReportService.GetMothVisitor(yearMothNum);
//            List<Integer> monthnum = new ArrayList<>();
//            Calendar cal = Calendar.getInstance(); //调用Calendar 中的方法；
//            cal.set(Calendar.DAY_OF_MONTH, 1); // 把时间调整为当月的第一天；
//            cal.add(Calendar.MONTH, 1);   // 月份调至下个月；
//            cal.add(Calendar.DAY_OF_MONTH, -1); // 时间减去一天（就等于上个月的最后一天）
//            //cal.get(Calendar.MONTH)+1;  //调取月份（月份在表示中会少 1，如：1月份得出数字是 0；
//            int days = cal.get(Calendar.DAY_OF_MONTH);//调取当月的天数。
//            for (int m = 1; m <= days; m++) {
//                String daynum = String.valueOf(m);
//                int personcount = list.stream().filter(w -> daynum.equals(w.getDayNum())).mapToInt(w -> w.getPersonNum()).sum();
//                monthnum.add(personcount);
//            }
//            result.setCode(0);
//            result.setContent(monthnum);
//        } catch (Exception ex) {
//
//            logger.error("月份每天访客登记情况异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//

//    /**
//     * 年度月份黑名单
//     *
//     * @param yearNum
//     * @return
//     */
//    @ApiOperation(value = "年度月份黑名单", notes = "年度月份黑名单 例：2017")
//    @RequestMapping(value = "/getyearmothblackreport/{yearNum}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<Integer>> getyearmothblackreport(@ApiParam("年") @PathVariable String yearNum) {
//        Result<List<Integer>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(yearNum)) {
//                Date date = new Date();
//                yearNum = DateTime.Date2String(date, "yyyy");
//            }
//
//            List<VisitorReport> list = visitorReportService.GetYearMothBlack(yearNum);
//            List<Integer> monthnum = new ArrayList<>();
//            for (int m = 1; m <= 12; m++) {
//                String moth = String.valueOf(m);
//                int personcount = list.stream().filter(w -> moth.equals(w.getMonthNum())).mapToInt(w -> w.getPersonNum()).sum();
//                monthnum.add(personcount);
//            }
//            result.setCode(0);
//            result.setContent(monthnum);
//        } catch (Exception ex) {
//
//            logger.error("年度黑名单情况异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }


//    /**
//     * 年度时间段访问情况
//     *
//     * @param yearNum
//     * @return
//     */
//    @ApiOperation(value = "年度时间段访问情况", notes = "年度时间段访问情况 例：2017")
//    @RequestMapping(value = "/GetYearTimePserson/{yearNum}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<Integer>> GetYearTimePserson(@ApiParam("年") @PathVariable String yearNum) {
//        Result<List<Integer>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(yearNum)) {
//                Date date = new Date();
//                yearNum = DateTime.Date2String(date, "yyyy");
//            }
//
//            List<VisitorReport> list = visitorReportService.GetYearTimePserson(yearNum);
//            List<Integer> monthnum = new ArrayList<>();
//            for (int m = 1; m <= 24; m++) {
//                String moth = "0";
//                if (m < 10) {
//                    moth = "0" + m;
//                } else {
//                    moth = String.valueOf(m);
//                }
//                String daynum = String.valueOf(moth);
//                int personcount = list.stream().filter(w -> daynum.equals(w.getDayNum())).mapToInt(w -> w.getPersonNum()).sum();
//                monthnum.add(personcount);
//            }
//            result.setCode(0);
//            result.setContent(monthnum);
//        } catch (Exception ex) {
//
//            logger.error("年度时间段访问情况异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//

//    /**
//     * 当天员工拜访量排行
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "当天员工拜访量排行", notes = "当天员工拜访量排行 例：10")
//    @RequestMapping(value = "/getdayvisistorstatics/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<VisitorStatistics>> getdayvisistorstatics(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<VisitorStatistics>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            List<VisitorStatistics> list = uVisitorService.findDayVisitorStatics(num);
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取当天员工拜访量排行异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }

//    /**
//     * 员工拜访量排行
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "员工拜访量排行", notes = "员工拜访量排行 例：10")
//    @RequestMapping(value = "/getvisistorstatics/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<VisitorStatistics>> getvisistorstatics(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<VisitorStatistics>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            List<VisitorStatistics> list = uVisitorService.findVisitorStatics(num);
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取当天员工拜访量排行异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//

//    /**
//     * 获取指定条数刷卡访客记录
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取指定条数刷卡访客记录", notes = "获取指定条数刷卡访客记录 例：10")
//    @RequestMapping(value = "/getvisitorswinginfo/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<Visitor>> getvisitorswinginfo(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<Visitor>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            List<Visitor> list = uVisitorService.getVisiSwafcardLimit(num);
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取指定条数刷卡访客记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }

//    /**
//     * 获取指定条数刷卡员工记录
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取指定条数刷卡员工记录", notes = "获取指定条数刷卡员工记录 例：10")
//    @RequestMapping(value = "/getuserswinginfo/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<UUser>> getuserswinginfo(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<UUser>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            List<UUser> list = uUserService.selectUserSwingInfoLimit(num);
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取指定条数刷卡员工记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }

//    /**
//     * 获取指定条数刷卡流水
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取指定条数刷卡流水", notes = "获取指定条数刷卡流水 例：10 头像，如果有证件照显示证件照，没有显示现场照")
//    @RequestMapping(value = "/getswingcardlimit/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<SwingCardRecordBo>> getswingcardlimit(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<SwingCardRecordBo>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//
//            List<SwingCardRecordBo> list = new ArrayList<>();
//            String key = "getswingcardlimit" + num;
//            String json = VideoCallBackCache.get(key);
//            if (StringUtils.isNotBlank(json)) {
//
//                list = JSONObject.parseArray(json, SwingCardRecordBo.class);
//
//            } else {
//                list = visitorReportService.getswingcardlimit(num);
//                if (list != null && list.size() > 0) {
//                    VideoCallBackCache.setex(key, JSONObject.toJSONString(list), 2);
//                }
//            }
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//            logger.error("获取指定条数刷卡员工记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }


//    /**
//     * 获取指定条数刷卡流水
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取指定条数刷卡流水包含钉钉", notes = "获取指定条数刷卡流水包含钉钉 例：10")
//    @RequestMapping(value = "/getswingcardlimit2/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<SwingCardRecordBo>> getswingcardlimit2(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<SwingCardRecordBo>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//
//            List<SwingCardRecordBo> list = new ArrayList<>();
//            String key = "getswingcardlimit" + num;
//            String json = VideoCallBackCache.get(key);
//            if (StringUtils.isNotBlank(json)) {
//                list = JSONObject.parseArray(json, SwingCardRecordBo.class);
//            } else {
//                list = visitorReportService.getswingcardlimit(num);
//                String isopendingdingswap = IConfig.getInstance().get("isopendingdingswap");
//                if ("true".equals(isopendingdingswap)) {
//                    String url = IConfig.getInstance().get("dingdingserverurl");
//                    HttpUtils utils = new HttpUtils();
//                    String resStr = utils.SendGetCmd(url + "/" + num);
//
//                    Result result1 = JSON.parseObject(resStr, Result.class);
//                    if (result1 != null && result1.getCode() == 0 && result1.getContent() != null) {
//                        List<JSONObject> uddSwingCardRecords = JSON.parseObject(JSONObject.toJSONString(result1.getContent()), ArrayList.class);
//
//                        if (uddSwingCardRecords != null && uddSwingCardRecords.size() > 0) {
//                            if (list == null)
//                                list = new ArrayList<>();
//                            SwingCardRecordBo swingCardRecordBo = null;
//                            for (JSONObject item : uddSwingCardRecords
//                            ) {
//                                swingCardRecordBo = new SwingCardRecordBo();
//                                swingCardRecordBo.setUsername(item.get("name").toString());
//                                if ("员工".equals(item.get("type").toString())) {
//                                    swingCardRecordBo.setType(0);
//                                } else {
//                                    swingCardRecordBo.setType(1);
//                                }
//                                swingCardRecordBo.setSwingcardtime(item.get("createdate").toString());
//                                if ("进门".equals( item.get("director").toString())) {
//                                    swingCardRecordBo.setInoutflag(1);
//                                } else {
//                                    swingCardRecordBo.setInoutflag(2);
//                                }
//                                list.add(swingCardRecordBo);
//                            }
//                            Collections.sort(list, new Comparator<SwingCardRecordBo>() {
//                                @Override
//                                public int compare(SwingCardRecordBo o1, SwingCardRecordBo o2) {
//                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                    try {
//                                        String str1=o1.getSwingcardtime().replace(".0","");
//                                        String str2=o2.getSwingcardtime().replace(".0","");
//
//                                        Date dt1 = format.parse(str1);
//                                        Date dt2 = format.parse(str2);
//                                        if (dt1.getTime() > dt2.getTime()) {
//                                            return -1;
//                                        } else if (dt1.getTime() < dt2.getTime()) {
//                                            return 1;
//                                        } else {
//                                            return 0;
//                                        }
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                    return 0;
//                                }
//                            });
//                            list = list.stream().collect(Collectors.toList()).subList(0, num);
//
//                        }
//                    }
//
//                }
//                if (list != null && list.size() > 0) {
//                    VideoCallBackCache.setex(key, JSONObject.toJSONString(list), 2);
//                }
//            }
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//            logger.error("获取指定条数刷卡员工记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }

    /**
     * 验证登录
     *
     * @param entity
     * @return
     */
    @ApiOperation(value = "验证登录", notes = "验证登录 传 email  pswd")
    @RequestMapping(value = "/loginvalid", method = RequestMethod.POST, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result loginvalid(@ApiParam("用户信息") @RequestBody ModelMap entity) {
        Result result = new Result();
        try {

            BaseMember userc = new BaseMember();
            userc.setEmail(entity.get("email").toString());
            userc.setPswd(entity.get("pswd").toString());
            // 把密码md5
            userc.setPswd(  MD5Util.MD5(userc.getPswd()));
            BaseMember u = UserManager.md5Pswd(userc);

            try {
                if (u == null) {
                    result.setCode(1);
                    result.setMsg("账号或密码错误！");
                } else {
                    result.setCode(0);
                    ModelMap usermap = new ModelMap();
                    usermap.put("id", u.getId());
                    usermap.put("photo", u.getPhoto());
                    usermap.put("nickname", u.getNickname());
                    usermap.put("phone", u.getPhone());
                    result.setContent(usermap);
                }

            } catch (Exception e) {
                logger.info("登录成功后获取用户信息异常:" + e.getMessage());
            }

        } catch (Exception ex) {
            logger.error("验证登录异常" + ex.getMessage());
            result.setCode(5000);
            result.setMsg(ex.getMessage());
        } finally {
            return result;
        }
    }
//
//
//    /**
//     * 获取公共配置参数
//     *
//     * @return
//     */
//    @ApiOperation(value = "获取公共配置参数", notes = "获取公共配置参数")
//    @RequestMapping(value = "/getdevicecommonParams", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<DeviceCommonParams> getdevicecommonParams() {
//        Result result = new Result<>();
//        try {
//            DeviceCommonParams params = new DeviceCommonParams();
//            IConfig config = IConfig.getInstance();
//            params.setFaceserverurl(config.get("faceserverurl"));
//            params.setMenjinserverurl(config.get("menjinserverurl"));
//            params.setValidedsendmsgserverurl(config.get("validedsendmsgserverurl"));
//            result.setCode(0);
//            result.setContent(params);
//        } catch (Exception ex) {
//            logger.error("获取公共配置参数异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//
//
//    /**
//     * 获取指定条数的登记流水
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取指定条数的登记流水", notes = "获取指定条数的登记流水 例：10")
//    @RequestMapping(value = "/getvisitorrecord/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<VisitorReportRecord>> getvisitorrecord(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<VisitorReportRecord>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            String key = "getvisitorrecordlimit";
//            List<VisitorReportRecord> list = new ArrayList<>();
//            if (VideoCallBackCache.exists(key)) {
//                result.setCode(0);
//                String json = VideoCallBackCache.get(key);
//                if (StringUtils.isNotBlank(json)) {
//                    list = JSONObject.parseArray(json, VisitorReportRecord.class);
//                }
//            } else {
//                list = visitorReportService.getvisitorrecord(num);
//                if (list != null && list.size() > 0) {
//                    VideoCallBackCache.setex(key, JSONObject.toJSONString(list), 5);
//                }
//            }
//
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取指定条数的登记流水记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//
//    /**
//     * 获取指定条数注销的登记流水
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取指定条数注销的登记流水", notes = "获取指定条数注销的登记流水 例：10")
//    @RequestMapping(value = "/getcancelvisitor/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<VisitorReportRecord>> getcancelvisitor(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<VisitorReportRecord>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            List<VisitorReportRecord> list = visitorReportService.getcancelvisitor(num);
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取指定条数注销的登记流水记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//
//
//    /**
//     * 获取指定条数最新访客车辆信息
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取指定条数最新访客车辆信息", notes = "获取指定条数最新访客车辆信息 例：10")
//    @RequestMapping(value = "/getVisitorCarLimit/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<VisitorCarRecord>> getVisitorCarLimit(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<VisitorCarRecord>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            List<VisitorCarRecord> list = visitorReportService.getVisitorCarLimit(num);
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取指定条数最新访客车辆信息流水记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//
//
//    /**
//     * 获取最新加入黑名单流水
//     *
//     * @param num
//     * @return
//     */
//    @ApiOperation(value = "获取最新加入黑名单流水", notes = "获取最新加入黑名单流水 例：10")
//    @RequestMapping(value = "/getblackvisitor/{num}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<List<BlackListBo>> getblackvisitor(@ApiParam("数量") @PathVariable Integer num) {
//        Result<List<BlackListBo>> result = new Result<>();
//        try {
//
//            if (!StringUtils.isNotBlank(num)) {
//                num = 10;
//            }
//            List<BlackListBo> list = visitorReportService.getblackvisitor(num);
//            result.setCode(0);
//            result.setContent(list);
//        } catch (Exception ex) {
//
//            logger.error("获取最新加入黑名单流水流水记录异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//
//
//    @ApiOperation(value = "访客签到预约登记接口", notes = "访客签到预约登记接口", response = Result.class)
//    @Transactional
//    @RequestMapping(value = "/saveWxVisitorInfo", method = RequestMethod.POST, produces = {
//            "application/json", "application/xml"}, consumes = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<Map<String, Object>> saveWxVisitorInfo(
//            @ApiParam("来访记录对象") @RequestBody AccessRecord record)
//            throws Exception {
//        Result<Map<String, Object>> result = new Result<Map<String, Object>>();
//        try {
//            // 访客记录必要参数检查
//            if (StringUtils.isBlank(record.getUser().getPhone()) ||
//                    StringUtils.isBlank(record.getVisitor().getName()) ||
//                    StringUtils.isBlank(record.getVisitor().getPhone()) ||
//                    StringUtils.isBlank(record.getVisitor().getIdnum())) {
//                result.setCode(1001);
//                result.setMsg("参数错误");
//                return result;
//            }
//            //判断是否在黑名单
//            String Idnum = record.getVisitor().getIdnum();
//            // 查看此人是否在本地黑名单
//            List<Visitor> b = fts.findBlackListByIdNum(Idnum);
//            List<BlacklistShare> bs = fts.findBlackShare(Idnum);
//            if ((b != null && b.size() > 0) || (bs != null && bs.size() > 0)) {
//                result.setCode(1001);
//                result.setMsg("警告！此人在黑名单！");
//                return result;
//            }
//
//            // 查询来访人员是否注销
//            List<Visitor> records = fts.isLogOff(Idnum);
//            if (record != null && records.size() > 0) {
//                VisitorBo vo = (VisitorBo) records.get(0);
//                // 没有注销访问
//                result.setCode(1002);
//
//                Map<String, Object> m = new HashMap<String, Object>();
//                m.put("id", vo.getId());
//                m.put("vid", vo.getId());
//                m.put("rCode", vo.getCardNum());
//                m.put("visitorname", record.getVisitor().getName());
//
//                result.setContent(m);
//                result.setMsg("未注销访问！");
//                return result;
//            }
//
//            //根据被访人电话查询被访人信息
//            UUser user = fts.findUserByPhoneNum(record.getUser().getPhone());
//            if (user == null || (user != null && user.getId() == 0)) {
//                result.setCode(1001);
//                result.setMsg("未查询到被访人");
//                return result;
//            }
//            //生成访客登记记录
//            if (StringUtils.isBlank(record.getStarttime())) {
//                //如果没有传时间默认当天当前时间
//                record.setStarttime(tools.DateTime.Date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
//            }
//            if (StringUtils.isBlank(record.getEndtime())) {
//                //如果没有传时间默认当天"23:59:59"
//                String endtime = tools.DateTime.Date2String(new Date(), "yyyy-MM-dd") + " 23:59:59";
//                record.setEndtime(endtime);
//            }
//
//            record.setUid(Integer.parseInt(user.getId() + ""));
//            // 更新访客信息（新访客添加，已添加访客更新）
//            if (fts.updateVisitorInfo(record.getVisitor()) > 0) {
//                record.setVid(record.getVisitor().getId());
//                // 添加主访客记录信息
//                record.setStatus(0);
//                if (fts.updateAccessRecord(record) > 0) {
//                    // 添加访客车辆信息
//                    if (record.getVCar() != null) {
//                        fts.updateVisitorCar(record);
//                    }
//                    // 添加访客带入物品
//                    if (record.getDrList() != null && record.getDrList().size() > 0) {
//                        fts.updateDraginRes(record);
//                    }
//                    Map<String, Object> m = new HashMap<String, Object>();
//                    m.put("id", record.getId());
//                    m.put("vid", record.getVisitor().getId());
//                    m.put("rCode", fts.findAccesRecordById(record.getId()).getCardnum());
//                    m.put("visitorname", record.getVisitor().getName());
//                    result.setContent(m);
//                }
//            }
//
//        } catch (DataIntegrityViolationException d) {
//            d.printStackTrace();
//            result.setCode(1001);
//            result.setMsg("参数错误");
//            throw d;
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        } finally {
//            return result;
//        }
//    }
//
//
    /**
     * 获取微信预约限制
     *
     * @return
     */
    @ApiOperation(value = "获取微信预约限制", notes = "获取微信预约限制 0 不限制直接过 1 限制需要登记 2 直接跳转到发卡打印")
    @RequestMapping(value = "/getwxyuyuevalid", method = RequestMethod.GET, produces = {
            "application/json", "application/xml"})
    public
    @ResponseBody
    Result getwxyuyuevalid() {
        Result result = new Result();
        try {
            String type = IConfig.getInstance().get("wxyytype");
            result.setContent(type);
            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception ex) {
            logger.error("获取微信预约限制异常" + ex.getMessage());
            result.setCode(5000);
            result.setMsg("服务端异常！");
        } finally {
            return result;
        }
    }
//
//
//    /**
//     * 获取车辆通行记录
//     *
//     * @return
//     */
//    @ApiOperation(value = "获取车辆通行记录", notes = "获取车辆通行记录")
//    @RequestMapping(value = "/getcarrecordlist/{num}/{type}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result getcarrecordlist(@ApiParam("数量") @PathVariable Integer num, @ApiParam("进出 1 进 2出") @PathVariable Integer type) {
//        Result result = new Result();
//        try {
//            if (num == null) {
//                num = 10;
//            }
//            CarSearchParam param = new CarSearchParam();
//            param.setPageSize(num);
//            param.setPageNo(1);
//            param.setDirection(type);
//            List<CarRecordModel> list = hdwareDeviceService.CarRecordList(param);
//            result.setContent(list);
//            result.setCode(0);
//            result.setMsg("成功");
//        } catch (Exception ex) {
//            logger.error("获取车辆通行列表异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//

    @ApiOperation(value = "获取卡号限制", notes = "获取卡号限制")
    @RequestMapping(value = "/CardNumberLimit", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    public @ResponseBody
    Result CardNumberLimit() {
        Result result = new Result();
        try {

            if ("1".equals(numlimit)) {
                result.setContent(10);
            } else {
                result.setContent(8);
            }

            result.setCode(0);
            result.setMsg("成功");
        } catch (Exception ex) {
            logger.error("获取卡号位数限制异常" + ex.getMessage());
            result.setCode(5000);
            result.setMsg("服务端异常！");
        } finally {
            return result;
        }
    }
//
//    @ApiOperation(value = "总访客量", notes = "总访客量")
//    @RequestMapping(value = "/VisitorCount", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
//    public @ResponseBody
//    Result VisitorCount() {
//        Result result = new Result();
//        try {
//            int count = 0;
//            if (VideoCallBackCache.exists("VISITORCOUNT")) {
//                String num = VideoCallBackCache.get("VISITORCOUNT");
//                count = Integer.parseInt(num);
//            } else {
//                count = visitorReportService.VisitorCount();
//                VideoCallBackCache.setex("VISITORCOUNT", "" + count, 10);
//            }
//            result.setContent(count);
//            result.setCode(0);
//            result.setMsg("成功");
//        } catch (Exception ex) {
//            logger.error("获取总访客量异常" + ex.getMessage());
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        } finally {
//            return result;
//        }
//    }
//    @ApiOperation(value = "跳转到数据中心", notes = "跳转到数据中心")
//    @RequestMapping(value = "/todatacenter", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
//    public @ResponseBody
//    void todatacenter(HttpServletRequest request, HttpServletResponse response) {
//
//        String basethpath=request.getContextPath();
//        String title = null;
//        try {
//
//            title = URLDecoder.decode(IConfig.getInstance().get("datacentertitle"), "utf-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String datacenter=IConfig.getInstance().get("datacenterurl");
//        String serverbaseurl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
//        try {
//            String url=serverbaseurl+"/"+datacenter+"/index.html?apiurl="+serverbaseurl+"&appname="+title;
//            response.sendRedirect(url);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @ApiOperation(value = "根据手机号查询访客信息", notes = "根据手机号查询访客信息")
//    @RequestMapping(value = "/findVisitorByPhone/{phone}", method = RequestMethod.GET, produces = {
//            "application/json"})
//    public
//    @ResponseBody
//    Result<Visitor> findVisitorByPhone(
//            @ApiParam("手机号") @PathVariable String phone,HttpServletRequest request) throws Exception {
//        Result<Visitor> result = new Result<Visitor>();
//        try {
//            Visitor vistor = fts.findVisitorByPhone(phone);
//            if (vistor == null) {
//                result.setCode(1001);
//                result.setMsg("无来访人信息！");
//                return result;
//            }
//            result.setContent(vistor);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("根据手机号查询访客信息发生错误", e);
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        }
//        return result;
//
//    }
//
//    @ApiOperation(value = "来访人员来访状态查询")
//    @Transactional
//    @RequestMapping(value = "/findVisitingStateByPhone/{phone}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public @ResponseBody Result<Object> findVisitingStateByPhone(@ApiParam("手机号") @PathVariable String phone,
//                                                                 HttpServletRequest request) throws Exception {
//        Result<Object> result = new Result<Object>();
//        try {
//            // 查询来访人员是否注销
//            List<Visitor> record = fts.isLogOffByPhone(phone);
//            if (record != null && record.size() >= VISITMAX) {// 没有注销访问
//                result.setCode(2);
//                result.setMsg("一天之内最多只能预约该公司" + VISITMAX + "个人员");
//                result.setContent(record.get(0));
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("来访人状态查询发生错误", e);
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//        }
//        return result;
//    }
//
//    @ApiOperation(value = "来访人员上次来访信息", notes = "根据来访人手机码查询上次来访信息")
//    @RequestMapping(value = "/findLastAccessRecordByPhone/{phone}", method = RequestMethod.GET, produces = {
//            "application/json", "application/xml"})
//    public
//    @ResponseBody
//    Result<AccessRecord> findLastAccessRecordByPhone(
//            @ApiParam("手机号") @PathVariable String phone,HttpServletRequest request) throws Exception {
//
//        Result<AccessRecord> result = new Result<AccessRecord>();
//        try {
//            if (phone == null || "".equals(phone.trim())) {
//                result.setCode(1001);
//                result.setMsg("手机号不能为空！");
//                return result;
//            }
//            // 查询上次访客记录
//            AccessRecord ar = fts.findLastAccessRecordByPhone(phone);
//            if (ar == null) {
//                result.setCode(1001);
//                result.setMsg("新来访客！");
//                return result;
//            }
//            result.setContent(ar);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.setCode(5000);
//            result.setMsg("服务端异常！");
//            throw e;
//        }
//        return result;
//
//    }

}

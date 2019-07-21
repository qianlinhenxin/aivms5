package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;

import com.qlhx.service.wechat.realize.dao.UMemberMapper;
import com.qlhx.service.wechat.realize.model.UMember;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.UMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UMemberServiceImpl extends BaseMybatisDao<UMemberMapper> implements UMemberService {

	Logger logger = LoggerFactory.getLogger(UMemberServiceImpl.class);

	@Autowired
	UMemberMapper memberMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return memberMapper.deleteByPrimaryKey(id);
	}

	
	@Override
	public int deleteByUid(int uid, String areaCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("areaCode", areaCode);
		return memberMapper.deleteByUid(map);
	}


	@Override
	public UMember insert(UMember entity) {
		memberMapper.insert(entity);
		return entity;
	}

	@Override
	public UMember insertSelective(UMember entity) throws Exception {
		memberMapper.insertSelective(entity);
		return entity;
	}

	@Override
	public UMember selectByPrimaryKey(Long id) {
		return memberMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(UMember entity) {
		return memberMapper.updateByPrimaryKey(entity);
	}

	@Override
	public int updateByPrimaryKeySelective(UMember entity) throws Exception {
		return memberMapper.updateByPrimaryKeySelective(entity);
	}

	@Override
	@Transactional
	public Integer insertOrUpdateMembers(String companyCode, String areaCode, String terminalCode,
			List<UMember> userList) throws Exception {
		for (UMember member : userList) {
			member.setCompanyCode(companyCode);
			member.setAreaCode(areaCode);
			member.setTerminalCode(terminalCode);
			member.setPrimaryId(member.getId());

			// 保存图片
			// if(!StringUtils.isBlank(member.getPhotoBase64()))
			// {
			// String path = saveImage(member.getPhotoBase64(),
			// member.getIdNum());
			// logger.info("PhotoPath="+path);
			// member.setPhoto(path);
			// }
//			if (member.getSyncstatus() == 0) {
//				member.setId(null);
//				member.setNative_createDate(new Date());
//				member.setNative_updateDate(new Date());
//				memberMapper.insertSelective(member);
//			} else {
//				UMember tempUser = memberMapper.selectMemberByPama(member);
//				member.setPrimaryId(tempUser.getPrimaryId());
//				member.setNative_updateDate(new Date());
//				memberMapper.updateByPrimaryKeySelective(member);
//			}
		}
		return 1;
	}

	@Override
	@Transactional
	public Integer insertOrUpdateMembers(List<UMember> userList) throws Exception {
		for (UMember member : userList) {
			member.setAreaCode(member.getCompanyNum());
			member.setCompanyCode(member.getAreaCode().substring(0, member.getAreaCode().indexOf("_")));
			if (member.getTabTime() == null || member.getUploadTime() == null) {
				member.setNative_createDate(new Date());
				member.setNative_updateDate(new Date());
				
				try {
					memberMapper.insertSelective(member);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = memberMapper.updateByCompanyCodeAndId(member);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(member));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(member));
					}

				}

			} else if (member.getTabTime().getTime() != member.getUploadTime().getTime()) {
//				member.setCompanyCode(member.getCompanyNum());
				member.setNative_updateDate(new Date());
				try {
					memberMapper.updateByCompanyCodeAndId(member);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return 1;
	}

//	private String saveImage(String base64Img, String idNum) throws Exception {
//		String picPath = System.getProperty("rms").replace(IConfig.get("pro"), IConfig.get("imgpro"))
//				+ IConfig.get("sitepic") + File.separator + idNum + "_" + DateTime.getTime("HHmmss") + ".png";
//		logger.info("图片路径：" + picPath);
//		if (Img.base642img(base64Img, picPath)) {
//			return File.separator + IConfig.get("imgpro") + File.separator + IConfig.get("sitepic") + File.separator
//					+ idNum + "_" + DateTime.getTime("HHmmss") + ".png";
//		} else {
//			return null;
//		}
//	}

	@SuppressWarnings("unchecked")
	@Override
	public Pagination<UMember> findUserAndRole(ModelMap modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage("findUserAndRole", "findCount", modelMap, pageNo, pageSize);
	}

	@Override
	public int findCount(Map param) 
	{
		return memberMapper.findCount(param);
	}

	@Override
	public List countReport(Map map) {
		return memberMapper.countReport(map);
	}

	@Override
	public List newCountReportByMonth(Map map) {
		return memberMapper.newCountReportByMonth(map);
	}

	@Override
	public List newCountReportByYear(Map param) {
		return memberMapper.newCountReportByYear(param);
	}
	
	@Override
	public UMember login(String email, String pswd , String areaCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("pswd", pswd);
		map.put("areaCode", areaCode);
		UMember user = memberMapper.login(map);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UMember selectByPhone(String phone, String areaCode) {
		Map param = new HashMap();
		param.put("phone", phone);
		param.put("areaCode", areaCode);
		return memberMapper.selectByPhone(param);
	}

	@Override
	public UMember selectByUid(Integer uid, String areaCode) {
		Map param = new HashMap();
		param.put("uid", uid);
		param.put("areaCode", areaCode);
		return memberMapper.selectByUid(param);
	}
	
	

}
package com.qlhx.service.base.realize.service.impl;


import com.qlhx.base.model.Result;
import com.qlhx.base.util.CommonUtil;
import com.qlhx.base.util.GenericCodeUtils;
import com.qlhx.service.base.realize.mapper.BaseAccessRecordMapper;
import com.qlhx.service.base.realize.mapper.BaseVisitorMapper;
import com.qlhx.service.base.realize.model.BaseAccessRecord;
import com.qlhx.service.base.realize.model.BaseVisitor;
import com.qlhx.service.base.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.base.realize.mybatis.page.Pagination;
import com.qlhx.service.base.realize.service.AccessRecordService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessRecordServiceImpl extends BaseMybatisDao<BaseAccessRecordMapper> implements AccessRecordService {

	@Autowired
    BaseAccessRecordMapper baseAccessRecordMapper;
	
	@Autowired
    BaseVisitorMapper baseVisitorMapper;
	
	@Override
	public Pagination<BaseAccessRecord> selectAccessRecord(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage(modelMap, pageNo, pageSize);
	}
	@Override
	public int insertSelective(BaseAccessRecord record) {
		return baseAccessRecordMapper.insertSelective(record);
	}

	@Override
	public Result<Map<String, Object>> SaveAccessRecord(BaseAccessRecord record) throws Exception {
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();

		// 访客记录必要参数检查
		if (StringUtils.isBlank(record.getReasons())) {
			record.setReasons("开会");
		}
		if (checkccessRecordParam(result, record) && checkIDInfo(result, record.getVisitor())) {
			record.setUid(Integer.parseInt(record.getUser().getId() + ""));
			// 更新访客信息（新访客添加，已添加访客更新）
			BaseVisitor visitor = record.getVisitor();
			BaseVisitor visitorTemp = baseVisitorMapper.selectByIdNum(record.getVisitor().getIdnum());
			
			// 获取需要注册的图片
			String visitorPhoto = visitor.getPhoto();
			if(StringUtils.isNotBlank(visitorPhoto))
			{
				String sitePhotoPath = CommonUtil.saveImg(visitorPhoto, com.qlhx.util.Constant.IMG_PATH, "visitorPhoto", record.getVisitor().getIdnum());
				visitor.setPhoto(sitePhotoPath);
			}
			if (visitorTemp == null) {
				baseVisitorMapper.insertSelective(visitor);
			} else {
				visitor.setId(visitorTemp.getId());
				baseVisitorMapper.updateByPrimaryKeySelective(visitor);
			}
			record.setVid(visitor.getId());
			// 添加主访客记录信息
			record.setStatus(0);
			// 获取需要注册的图片
			String sitePhoto = record.getSitephoto();
			if(StringUtils.isNotBlank(sitePhoto))
			{
				String sitePhotoPath = CommonUtil.saveImg(sitePhoto, com.qlhx.util.Constant.IMG_PATH, "sitePhoto", record.getVisitor().getIdnum());
				record.setSitephoto(sitePhotoPath);
			}
			
			if (baseAccessRecordMapper.insertSelective(record) > 0) {
				String code = GenericCodeUtils.GetRandomPre() + GenericCodeUtils.autoGenericCode(record.getId(), 5);
				if (record.getCardnum() == null || "".equals(record.getCardnum())) {
                    record.setCardnum(code);
                    //默认生成的卡号type 0
                    record.setCardtype(2);
                } else {
                    //发卡
                    record.setCardtype(0);
                }
				baseAccessRecordMapper.updateByPrimaryKey(record);
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", record.getId());
				m.put("vid", record.getVisitor().getId());
				m.put("rCode", baseAccessRecordMapper.selectByPrimaryKey(record.getId()).getCardnum());
				result.setContent(m);
				
			}
		}
		return result;
	}
	
	   private boolean checkccessRecordParam(Result result, BaseAccessRecord record)
			throws Exception {
		// TODO Auto-generated method stub
		if (record == null || record.getUser().getId() == null || record.getUser().getId() == 0) {
			result.setCode(1001);
			result.setMsg("被访人不存在！");
			return false;
		} else if (record.getStarttime() == null) {
			result.setCode(1001);
			result.setMsg("访问开始时间不存在！");
			return false;
		} else if (record.getEndtime() == null) {
			result.setCode(1001);
			result.setMsg("访问结束时间不存在！");
			return false;
		}
		return true;
	}
	   
	   private boolean checkIDInfo(Result result, BaseVisitor visitor){
	        if (visitor == null) {
	            result.setCode(1001);
	            result.setMsg("访客信息不存在！");
	            return false;
	        } else if (visitor.getIdnum() == null) {
	            result.setCode(1001);
	            result.setMsg("访客身份证号不存在！");
	            return false;
	        } else if (visitor.getName() == null) {
	            result.setCode(1001);
	            result.setMsg("访客姓名不存在！");
	            return false;
	        } else if (visitor.getSex() == null) {
	            visitor.setSex("男");
	        }
	        return true;
	    }

	@Override
	public Result<Map<String, Object>> addEntourage(BaseAccessRecord record) throws Exception {
		Result<Map<String, Object>> result = new Result<Map<String, Object>>();

		BaseAccessRecord ac = baseAccessRecordMapper.selectByPrimaryKey(record.getParentid());
		if (record.getParentid() == null || ac == null) 
		{
			result.setCode(1001);
			result.setMsg("主访记录ID不存在！");
			return result;
		}
		// 访客记录必要参数检查
		if (StringUtils.isBlank(record.getReasons())) {
			record.setReasons("开会");
		}
		if (checkccessRecordParam(result, record) && checkIDInfo(result, record.getVisitor())) {
			record.setUid(Integer.parseInt(record.getUser().getId() + ""));
			// 更新访客信息（新访客添加，已添加访客更新）
			BaseVisitor visitor = record.getVisitor();
			BaseVisitor visitorTemp = baseVisitorMapper.selectByIdNum(record.getVisitor().getIdnum());
			if (visitor == null) {
				baseVisitorMapper.insertSelective(visitor);
			} else {
				visitor.setId(visitorTemp.getId());
				baseVisitorMapper.updateByPrimaryKeySelective(visitor);
			}
			record.setVid(visitor.getId());
			// 添加主访客记录信息
			record.setStatus(0);

			// 获取需要注册的图片
			String photo = record.getSitephoto();
			if (baseAccessRecordMapper.insertSelective(record) > 0) {
				String code = GenericCodeUtils.GetRandomPre() + GenericCodeUtils.autoGenericCode(record.getId(), 5);
				if (record.getCardnum() == null || "".equals(record.getCardnum())) {
                    record.setCardnum(code);
                    //默认生成的卡号type 0
                    record.setCardtype(2);
                } else {
                    //发卡
                    record.setCardtype(0);
                }
				baseAccessRecordMapper.updateByPrimaryKey(record);
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", record.getId());
				m.put("vid", record.getVisitor().getId());
				m.put("rCode", baseAccessRecordMapper.selectByPrimaryKey(record.getId()).getCardnum());
				result.setContent(m);
				// RegisterFace(record, photo);
			}
		}
		return result;
	}

	@Override
	public Integer findAccessRecordPageCount(BaseAccessRecord record) throws Exception {
		return baseAccessRecordMapper.findAccessRecordPageCount(record);
	}

	@Override
	public List<BaseAccessRecord> findAccessRecord(BaseAccessRecord record) throws Exception {
		return baseAccessRecordMapper.findAccessRecord(record);
	}

	@Override
	public BaseAccessRecord findAccessRecordByCardNum(String cardNum) {
		return baseAccessRecordMapper.findAccessRecordByCardNum(cardNum);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseAccessRecord record) {
		return baseAccessRecordMapper.updateByPrimaryKeySelective(record);
	}

}

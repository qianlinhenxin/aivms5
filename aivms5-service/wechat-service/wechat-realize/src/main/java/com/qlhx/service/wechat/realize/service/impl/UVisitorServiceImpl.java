package com.qlhx.service.wechat.realize.service.impl;

import com.alibaba.fastjson.JSON;
import com.qlhx.base.util.IConfig;
import com.qlhx.service.wechat.realize.dao.*;
import com.qlhx.service.wechat.realize.model.*;
import com.qlhx.service.wechat.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.wechat.realize.mybatis.page.Pagination;
import com.qlhx.service.wechat.realize.service.UVisitorService;
import com.qlhx.service.wechat.realize.utils.LoggerUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import tools.DateTime;
import tools.Img;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UVisitor 表数据服务层接口实现类
 */
@Service
public class UVisitorServiceImpl extends BaseMybatisDao<VisitorMapper> implements UVisitorService {

    @Autowired
    private VisitorMapper visitorMapper;
    
    @Autowired
    private VisitorCarMapper visitorCarMapper;
    
    @Autowired
    private DraginResMapper draginResMapper;
    
    @Autowired
    private TakeoutResMapper takeoutResMapper;

    @Autowired
    private AccessRecordMapper accessRecordMapper;
    /**
     * 获取访客流水信息
     *
     * @param modelMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<VisitorRecordBo> selectVisitorRecord(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findVisitorRecord", "findVisitorRecordCount", modelMap, pageNo,
                pageSize);
    }

    /**
     * 获取访客流水列表,
     *
     * @param resultMap
     * @return
     */
    @Override
    public List<VisitorRecordBo> selectVisitorByModelMap(Map<String, Object> resultMap) {
        return visitorMapper.findVisitorRecord(resultMap);
    }

    /**
     * 获取访客表格信息
     *
     * @param modelMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<Visitor> selectVisitorList(ModelMap modelMap, Integer pageNo, int pageSize) {
        return super.findPage(modelMap, pageNo, pageSize);
    }

    /**
     * 获取所有访客列表
     *
     * @param modelMap
     * @return
     */
    @Override
    public List<Visitor> selectVisitors(ModelMap modelMap) {
        return visitorMapper.findAll(modelMap);
    }

    /**
     * 根据身份证号获取访客信息
     *
     * @param idnum 身份证号
     * @return
     * @throws Exception
     */
    @Override
    public Visitor findVisitorByIdNum(String idnum) throws Exception {
        return visitorMapper.findVisitorByIdNum(idnum);
    }

    /**
     * 获取预约列表
     *
     * @param modelMap
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<VisitorRecordBo> selectVisitorOrderList(ModelMap modelMap, Integer pageNo, int pageSize) {
        return super.findPage("findVisitorOrderList", "findVisitorOrderListCount", modelMap, pageNo,
                pageSize);
    }

    /**
     * 根据身份证号判断是否存在预约信息
     *
     * @param idNum
     * @return
     */
    @Override
    public Boolean findOrderVisitorByIdNumCount(String idNum) {
        return visitorMapper.findOrderVisitorByIdNumCount(idNum) <= 0;
    }


    /**
     * 修改或新增访客预约信息
     *
     * @param visitor
     * @return
     * @throws Exception
     */
    @Override
    public Integer addOrEditVisitor(Visitor visitor) throws Exception {
        if (null == visitor.getId())
            return visitorMapper.insertSelective(visitor);
        else
            return visitorMapper.updateByPrimaryKeySelective(visitor);
    }

    /**
     * 根据流水ID获取访客信息
     *
     * @param id
     * @return
     */
    @Override
    public VisitorRecordBo selectVisitorRecordByRId(Integer id) {
        return visitorMapper.findOrderVisitorByRId(id);
    }

    @Override
    public Map<String, Object> delVisitorOrders(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String resultMsg = "删除成功。";
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            for (String idx : idArray) {
                Long id = new Long(idx);
                count += this.delVisitorOrder(id.intValue());
            }

            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除预约出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    @Override
    public Integer delVisitorOrder(Integer id) {
        return visitorMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<VisitorStatistics> findVisitorStatistics(ModelMap modelMap) {
        return visitorMapper.findVisitorStatistics(modelMap);
    }

    @Override
    public List<VisitorRecordBo> selectPhotoByVids(Map<String, Object> resultModel) {
        return visitorMapper.selectPhotoByVids(resultModel);
    }
    
    @Override
    @Transactional
    public Integer insertOrUpdateVistors(String companyCode ,String areaCode,String terminalCode, List<Visitor> vistorList) throws Exception
    {
    	for (Visitor visitor : vistorList) {
    		visitor.setCompanyCode(companyCode);
    		visitor.setAreaCode(areaCode);
    		visitor.setTerminalCode(terminalCode);
    		visitor.setPrimaryId(visitor.getId());
    		Visitor tempUser = visitorMapper.selectVisitorByPama(visitor);
    		if(tempUser != null)
    		{  
    			visitor.setId(tempUser.getId());
    			visitorMapper.updateByPrimaryKeySelective(visitor);
    		}
    		else
    		{
    			visitor.setId(null);
    			visitorMapper.insertSelective(visitor);
    		}
		}
    	return 1;
    }
    
    @Override
    @Transactional
    public Integer insertOrUpdateVistors(List<Visitor> vistorList) throws Exception
	{
		for (Visitor visitor : vistorList) {
			visitor.setAreaCode(visitor.getCompanyCode());
			visitor.setCompanyCode(visitor.getAreaCode().substring(0, visitor.getAreaCode().indexOf("_")));
			if (visitor.getTabTime() == null || visitor.getUploadTime() == null) {
				visitor.setNative_createDate(new Date());
				visitor.setNative_updateDate(new Date());
				try {
					visitorMapper.insertSelective(visitor);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = visitorMapper.updateByCompanyCodeAndId(visitor);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(visitor));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(visitor));
					}

				}

			} else if (visitor.getTabTime().getTime() != visitor.getUploadTime().getTime()) {
				visitor.setNative_updateDate(new Date());
				visitorMapper.updateByCompanyCodeAndId(visitor);
			}
		}
		return 1;
	}
    
    @Override
    @Transactional
    public Integer insertOrUpdateVistorsCar(List<VisitorCar> vistorCarList) throws Exception
	{
		for (VisitorCar visitor : vistorCarList) {
			visitor.setAreaCode(visitor.getCompanyCode());
			visitor.setCompanyCode(visitor.getAreaCode().substring(0, visitor.getAreaCode().indexOf("_")));
			if (visitor.getTabTime() == null || visitor.getUploadTime() == null) {
				visitor.setNative_createDate(new Date());
				visitor.setNative_updateDate(new Date());
				try {
					visitorCarMapper.insertSelective(visitor);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = visitorCarMapper.updateByPrimaryKeySelective(visitor);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(visitor));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(visitor));
					}

				}

			} else if (visitor.getTabTime().getTime() != visitor.getUploadTime().getTime()) {
				visitor.setNative_updateDate(new Date());
				try {
					visitorCarMapper.updateByPrimaryKeySelective(visitor);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}
    
    
    
    @Override
	public Integer insertOrUpdateDraginRes(List<DraginRes> draginResList) throws Exception {
		for (DraginRes draginRes : draginResList) {
			draginRes.setAreaCode(draginRes.getCompanyCode());
			draginRes.setCompanyCode(draginRes.getAreaCode().substring(0, draginRes.getAreaCode().indexOf("_")));
			if (draginRes.getTabTime() == null || draginRes.getUploadTime() == null) {
				draginRes.setNative_createDate(new Date());
				draginRes.setNative_updateDate(new Date());
				try {
					draginResMapper.insertSelective(draginRes);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = draginResMapper.updateByPrimaryKeySelective(draginRes);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(draginRes));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(draginRes));
					}

				}

			} else if (draginRes.getTabTime().getTime() != draginRes.getUploadTime().getTime()) {
				draginRes.setNative_updateDate(new Date());
				try {
					draginResMapper.updateByPrimaryKeySelective(draginRes);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}

	@Override
	public Integer insertOrUpdateTakeoutRes(List<TakeoutRes> takeoutResList) throws Exception {
		for (TakeoutRes takeoutRes : takeoutResList) {
			takeoutRes.setAreaCode(takeoutRes.getCompanyCode());
			takeoutRes.setCompanyCode(takeoutRes.getAreaCode().substring(0, takeoutRes.getAreaCode().indexOf("_")));
			if (takeoutRes.getTabTime() == null || takeoutRes.getUploadTime() == null) {
				takeoutRes.setNative_createDate(new Date());
				takeoutRes.setNative_updateDate(new Date());
				try {
					takeoutResMapper.insertSelective(takeoutRes);
				} catch (Exception e) {
					e.printStackTrace();
					String msg = "";
					String cause = "";
					try
					{
						int i = takeoutResMapper.updateByPrimaryKeySelective(takeoutRes);
						if (i == 0) 
						{
							msg = e.getMessage();
							cause = e.getCause()==null?"":e.getCause().getMessage();
							throw new Exception("这条记录插入失败,尝试更新但0条数据被更改，插入失败原因:"+ msg +";"+ cause + "数据:" + JSON.toJSONString(takeoutRes));
						}
					} 
					catch (Exception e1) 
					{
						e.printStackTrace();
						String msg1 = e1.getMessage();
						String cause1 = e1.getCause() == null ? "" : e1.getCause().getMessage();
						throw new Exception("这条记录插入失败,更新也失败，插入失败原因:" + msg + cause + " 更新失败原因:"+msg1+";"+cause1+"数据:" + JSON.toJSONString(takeoutRes));
					}

				}

			} else if (takeoutRes.getTabTime().getTime() != takeoutRes.getUploadTime().getTime()) {
				takeoutRes.setNative_updateDate(new Date());
				try {
					takeoutResMapper.updateByPrimaryKeySelective(takeoutRes);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return 1;
	}

	private String saveImage(String base64Img,String idNum) throws Exception
    {
    	    String picPath = System.getProperty("rms").replace(
    		    IConfig.get("pro"), IConfig.get("imgpro"))
    		    + IConfig.get("sitepic")
    		    + File.separator
    		    + idNum
    		    + "_"
    		    + DateTime.getTime("HHmmss") + ".png";
    	    if (Img.base642img(base64Img, picPath))
    	    {
    		return File.separator + IConfig.get("imgpro")
    			+ File.separator + IConfig.get("sitepic")
    			+ File.separator + idNum + "_"
    			+ DateTime.getTime("HHmmss") + ".png";
    	    }
    	    else
    	    {
    	    	return null;
    	    }
    }

	@Override
	public int findCount(Map param) {
		return visitorMapper.findCount(param);
	}

	@Override
	public int findRecordCount(Map param) 
	{
		return visitorMapper.findVisitorRecordCount(param);
	}

	@Override
	public List<Map> selectVisitorToUploadPolice(Map param) {
		return visitorMapper.selectVisitorToUploadPolice(param);
	}

	@Override
	public int updateByPrimaryKeySelective(Visitor visitor) {
		return visitorMapper.updateByPrimaryKeySelective(visitor);
	}

	@Override
	public List<Integer> selectVidsByName(String name) {
		return visitorMapper.selectVidsByName(name);
	}
	
}
package com.qlhx.base.api.impl;

import com.qlhx.base.bean.BaseBean;
import com.qlhx.base.bean.PageBean;
import com.qlhx.base.service.BaseService;
import com.qlhx.base.util.bean.BeanUtil;
import com.qlhx.base.util.bean.ObjectUtil;
import com.qlhx.base.util.web.ApiResponse;
import com.qlhx.base.vo.BaseVO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/18 8:30
 * @Description desc:
 */
public class BaseApiImpl<Model extends BaseBean,VO extends BaseVO> {
    private static final Logger logger = LoggerFactory.getLogger(BaseApiImpl.class);

    protected Model model;

    protected Class<Model> modelClass;

    protected String entityClassName;

    protected Class<VO> voClass;

    protected Object findBean(String beanId) {
        return BeanUtil.getBean(beanId);
    }

    @SuppressWarnings("unchecked")
    protected BaseService<Model> getBaseService() {
        BaseService<Model> baseService = (BaseService<Model>)findBean(StringUtils.uncapitalize(entityClassName) + "ServiceImpl");
        return baseService;
    }

    @SuppressWarnings("unchecked")
    public BaseApiImpl() {
        super();
        try {
            modelClass = (Class<Model>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            voClass = (Class<VO>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            entityClassName = modelClass.getSimpleName();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ApiResponse<Integer> save(@RequestBody VO vo) {
        ApiResponse<Integer> ApiResponse = new ApiResponse<>();
        try {
            Model model = ObjectUtil.copy(vo, modelClass);
            Integer result = getBaseService().insert(model);
            if (result!=null && result > 0) {
                ApiResponse.setRetContent(result);
            }else{
                ApiResponse.setRetCode("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
        }
        return ApiResponse;
    }

    @RequestMapping(value="/update", method = RequestMethod.POST)
    public ApiResponse<Integer> update(@RequestBody VO vo) {
        ApiResponse<Integer> ApiResponse = new ApiResponse<>();
        try {
            Model model = ObjectUtil.copy(vo, modelClass);
            Integer result = getBaseService().update(model);
            if (result > 0) {
                ApiResponse.setRetContent(result);
            }else{
                ApiResponse.setRetCode("");
            }
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
        }
        return ApiResponse;
    }

    @RequestMapping(value="/updateIgnoreNull", method = RequestMethod.POST)
    public ApiResponse<Integer> updateIgnoreNull(@RequestBody VO vo) {
        ApiResponse<Integer> ApiResponse = new ApiResponse<>();
        try {
            Model model = ObjectUtil.copy(vo, modelClass);
            Integer result = getBaseService().updateIgnoreNull(model);
            if (result > 0) {
                ApiResponse.setRetContent(result);
            }else{
                ApiResponse.setRetCode("");
            }
        }  catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
        }
        return ApiResponse;
    }

    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public ApiResponse<PageBean<VO>> findByPage(@RequestBody Map<String, Object> params) {
        ApiResponse<PageBean<VO>> ApiResponse = new ApiResponse<>();
        try {
            logger.info("findByPage params:" + params);
            int pageNum =1;
            int pageSize =10;
            if (params.containsKey("pageNum")) {
                if (params.get("pageNum")!=null && !params.get("pageNum").toString().trim().equals("")) {
                    pageNum = Integer.parseInt(params.get("pageNum").toString());
                }
            }
            if (params.containsKey("pageSize")) {
                if (params.get("pageSize")!=null && !params.get("pageSize").toString().trim().equals("")) {
                    pageSize = Integer.parseInt(params.get("pageSize").toString());
                }
            }
            List<Model> modelList = getBaseService().findByParams(params);
            List<VO> voList =  ObjectUtil.copyList(modelList, voClass);
            PageBean<VO> voPageBean = new PageBean<>();
            voPageBean.setPageNum(pageNum);
            voPageBean.setPageSize(pageSize);
            voPageBean.setResultData(voList);
            ApiResponse.setRetContent(voPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
        }
        return ApiResponse;
    }

    @RequestMapping(value = "/findAll")
    public ApiResponse<List<VO>> findAll() {
        ApiResponse<List<VO>> ApiResponse = new ApiResponse<>();
        try {
            List<Model> modelList = getBaseService().findAll();
            List<VO> voList = ObjectUtil.copyList(modelList, voClass);
            ApiResponse.setRetContent(voList);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
            logger.error(e.getMessage());
        }
        return ApiResponse;
    }

    @RequestMapping(value = "/findByPrimaryKey")
    public ApiResponse<VO> findByPrimaryKey(@RequestParam("id")  Long id) {
        ApiResponse<VO> ApiResponse = new ApiResponse<>();
        try {
            Model model = getBaseService().findById(id);
            VO vo = ObjectUtil.copy(model, voClass);
            ApiResponse.setRetContent(vo);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
        }
        return ApiResponse;
    }

    @RequestMapping(value = "/deleteByPrimaryKey")
    public ApiResponse<Integer> deleteByPrimaryKey(@RequestParam("id")  Long id) {
        ApiResponse<Integer> ApiResponse = new ApiResponse<>();
        try {
            int count = getBaseService().deleteById(id);
            ApiResponse.setRetContent(count);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
        }
        return ApiResponse;
    }

    @RequestMapping(value = "/findByParams")
    public ApiResponse<List<VO>> findByParams(@RequestBody Map<String, Object> params) {
        ApiResponse<List<VO>> ApiResponse = new ApiResponse<>();
        try {
            List<Model> modelList = getBaseService().findByParams(params);
            List<VO> voList = ObjectUtil.copyList(modelList, voClass);
            ApiResponse.setRetContent(voList);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setRetCode("500");
        }
        return ApiResponse;
    }

}

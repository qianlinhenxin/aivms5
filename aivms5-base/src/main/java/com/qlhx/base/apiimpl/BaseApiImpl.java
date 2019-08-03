package com.qlhx.base.apiimpl;

import com.qhlx.core.bean.BaseBean;
import com.qhlx.core.bean.Code;
import com.qhlx.core.page.PageDTO;
import com.qhlx.core.page.PageDTOUtil;
import com.qhlx.core.util.bean.BeanUtil;
import com.qhlx.core.util.bean.ObjectUtil;
import com.qhlx.core.util.web.ApiResponse;
import com.qhlx.core.vo.BaseVO;
import com.qlhx.base.service.BaseService;
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
                ApiResponse.setCode(Code.FAILURE);
            }
            logger.info("{} ---->save , params:{}",getBaseService().getClass().getName(), ObjectUtil.toString(vo));
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
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
                ApiResponse.setCode(Code.FAILURE);
            }
            logger.info("{} ---->update , params:{}",getBaseService().getClass().getName(),ObjectUtil.toString(vo));
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }

    /**
     * 忽略空值更新
     * @param vo
     * @return
     */
    @RequestMapping(value="/updateBySelective", method = RequestMethod.POST)
    public ApiResponse<Integer> updateBySelective(@RequestBody VO vo) {
        ApiResponse<Integer> ApiResponse = new ApiResponse<>();
        try {
            Model model = ObjectUtil.copy(vo, modelClass);
            Integer result = getBaseService().updateBySelective(model);
            if (result > 0) {
                ApiResponse.setRetContent(result);
            }else{
                ApiResponse.setCode(Code.FAILURE);
            }
            logger.info("{} ---->updateIgnoreNull , params:{}",getBaseService().getClass().getName(),ObjectUtil.toString(vo));
        }  catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }

    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public ApiResponse<PageDTO<VO>> findByPage(@RequestBody Map<String, Object> params) {
        ApiResponse<PageDTO<VO>> ApiResponse = new ApiResponse<>();
        try {
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
            PageDTOUtil.startPage(pageNum, pageSize);
            List<Model> modelList = getBaseService().findByParams(params);
            List<VO> voList =  ObjectUtil.copyList(modelList, voClass);
            PageDTO<VO> pages = PageDTOUtil.transform(voList);
            logger.info("{} ---->findByPage , params:{}",getBaseService().getClass().getName(),params);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }finally {
            PageDTOUtil.endPage();
        }
        return ApiResponse;
    }

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(value = "/findAll")
    public ApiResponse<List<VO>> findAll() {
        ApiResponse<List<VO>> ApiResponse = new ApiResponse<>();
        try {
            List<Model> modelList = getBaseService().findAll();
            List<VO> voList = ObjectUtil.copyList(modelList, voClass);
            ApiResponse.setRetContent(voList);
            logger.info("{} ---->findAll , params:{}",getBaseService().getClass().getName(),"");
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
            logger.error(e.getMessage());
        }
        return ApiResponse;
    }

    /**
     * 根据sid来查询数据
     * @param sid
     * @return
     */
    @RequestMapping(value = "/findByPrimaryKey")
    public ApiResponse<VO> findByPrimaryKey(@RequestParam("sid")  Long sid) {
        ApiResponse<VO> ApiResponse = new ApiResponse<>();
        try {
            Model model = getBaseService().findByPrimaryKey(sid);
            VO vo = ObjectUtil.copy(model, voClass);
            ApiResponse.setRetContent(vo);
            logger.info("{} ---->findByPrimaryKey , sid:{}",getBaseService().getClass().getName(),sid);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }

    /**
     * 根据主键删除数据
     * @param sid
     * @return
     */
    @RequestMapping(value = "/deleteByPrimaryKey")
    public ApiResponse<Integer> deleteByPrimaryKey(@RequestParam("sid")  Long sid) {
        ApiResponse<Integer> ApiResponse = new ApiResponse<>();
        try {
            int count = getBaseService().deleteByPrimaryKey(sid);
            ApiResponse.setRetContent(count);
            logger.info("{} ---->deleteByPrimaryKey , sid:{}",getBaseService().getClass().getName(),sid);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }

    /**
     * 根据map参数来查询
     * @param params
     * @return
     */
    @RequestMapping(value = "/findByParams")
    public ApiResponse<List<VO>> findByParams(@RequestBody Map<String, Object> params) {
        ApiResponse<List<VO>> ApiResponse = new ApiResponse<>();
        try {
            List<Model> modelList = getBaseService().findByParams(params);
            List<VO> voList = ObjectUtil.copyList(modelList, voClass);
            ApiResponse.setRetContent(voList);
            logger.info("{} ---->findByParams , params:{}",getBaseService().getClass().getName(),params);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }


    /**
     * 根据对象来查询数据
     * @param vo
     * @return
     */
    @RequestMapping(value = "/findByObject")
    public ApiResponse<List<VO>> findByObject(@RequestBody VO vo) {
        ApiResponse<List<VO>> ApiResponse = new ApiResponse<>();
        try {
            Map<String, Object> objectMap = ObjectUtil.toMap(vo);
            List<Model> modelList = getBaseService().findByParams(objectMap);
            List<VO> voList = ObjectUtil.copyList(modelList, voClass);
            ApiResponse.setRetContent(voList);
            logger.info("{} ---->findByParams , Object:{}",getBaseService().getClass().getName(),vo);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }


    /**
     * 根据对象属性来删除
     * @param vo
     * @return
     */
    @RequestMapping(value = "/deleteByObject")
    public ApiResponse<String> deleteByObject(@RequestBody VO vo) {
        ApiResponse<String> ApiResponse = new ApiResponse<>();
        try {
            Map<String, Object> objectMap = ObjectUtil.toMap(vo);
            getBaseService().delete(ObjectUtil.copy(vo,modelClass));
            logger.info("{} ---->findByParams , Object:{}",getBaseService().getClass().getName(),vo);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }


    /**
     * 根据map参数来删除
     * @param params
     * @return
     */
    @RequestMapping(value = "/deleteByObject")
    public ApiResponse<String> deleteByParams(@RequestBody Map<String, Object> params) {
        ApiResponse<String> ApiResponse = new ApiResponse<>();
        try {
            getBaseService().deleteByParams(params);
            logger.info("{} ---->deleteByParams , params:{}",getBaseService().getClass().getName(),params);
        } catch (Exception e) {
            e.printStackTrace();
            ApiResponse.setCode(Code.SYS_ERROR);
            ApiResponse.setDesc(e.getMessage());
        }
        return ApiResponse;
    }



}

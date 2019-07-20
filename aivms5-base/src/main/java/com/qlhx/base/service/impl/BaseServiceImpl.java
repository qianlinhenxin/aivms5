package com.qlhx.base.service.impl;


import com.qlhx.base.bean.BaseBean;
import com.qlhx.base.bean.PageBean;
import com.qlhx.base.mapper.BaseMapper;
import com.qlhx.base.service.BaseService;
import com.qlhx.base.util.bean.BeanUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @versio 1.0.o
 * @Author he ming xi
 * @date 2019/4/6 15:11
 * @description
 */
@Service
public class BaseServiceImpl<T extends BaseBean> implements BaseService<T> {
    /**
     * 日志记录
     */
    private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    private BaseMapper<T> baseMapper;

    /**
     * 实体的Class对象
     */
    protected Class<T> modelClass;

    /**
     * 实体类名
     */
    protected String modelClassName;


    protected BaseMapper<T> getBaseMapper() {
        BaseMapper<T> baseMapper = null;
        if (this.modelClass != null) {
            baseMapper = (BaseMapper<T>) BeanUtil.getBean(StringUtils.uncapitalize(modelClassName) + "Mapper");
        } else {
            baseMapper = this.baseMapper;
        }
        return baseMapper;
    }

    public BaseServiceImpl() {
        try {
            Object genericClz = getClass().getGenericSuperclass();
            if (genericClz instanceof ParameterizedType) {
                modelClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
                modelClassName = modelClass.getSimpleName();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据model条件查询
     * @param model
     * @return
     */
    @Override
    public List<T> find(T model) {
        try {
            List<T> listData = getBaseMapper().find(model);
            logger.info("BaseMapper ====> find("+modelClassName+" model ), params :"+model.toString());
            return listData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 新增
     * @param model
     * @return
     */
    @Override
    public int insert(T model) {
        if(model.getCreateUser() == null){
            model.setCreateUser(1L);
        }
        if(model.getCreateName() == null){
            model.setCreateName("default");
        }
        if(model.getCreateIp() == null){
            model.setCreateIp("127.0.0.1");
        }
        if(model.getCreateDate() == null){
            model.setCreateDate(new Date());
        }
        try {
            int i = getBaseMapper().insert(model);
            logger.info("BaseMapper ====> insert("+modelClassName+" model ), params :"+model.toString());
            return i;
        }catch (DuplicateKeyException e) {
            e.printStackTrace();
            logger.error("重复的主键，"+model.getId());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return 0;
    }

    /**
     * 删除
     * @param model
     */
    @Override
    public void delete(T model) {
        try {
            getBaseMapper().delete(model);
            logger.info("BaseMapper ====> delete("+modelClassName+" model ), params :"+model.toString());
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 修改
     * @param model
     * @return
     */
    @Override
    public int update(T model) {
        if(model.getUpdateUser() == null){
            model.setUpdateUser(1L);
        }
        if(model.getUpdateName() == null){
            model.setUpdateName("default");
        }
        if(model.getUpdateIp() == null){
            model.setUpdateIp("127.0.0.1");
        }
        if(model.getUpdateDate() == null){
            model.setUpdateDate(new Date());
        }
        try {
            int i = getBaseMapper().update(model);
            logger.info("BaseMapper ====> update("+modelClassName+" model ), params :"+model.toString());
            return i;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return 0;
    }

    /**
     * 修改忽略NULL值
     * @param model
     * @return
     */
    @Override
    public int updateIgnoreNull(T model) {
        if(model.getUpdateUser() == null){
            model.setUpdateUser(1L);
        }
        if(model.getUpdateName() == null){
            model.setUpdateName("default");
        }
        if(model.getUpdateIp() == null){
            model.setUpdateIp("127.0.0.1");
        }
        if(model.getUpdateDate() == null){
            model.setUpdateDate(new Date());
        }
        try {
            int i = getBaseMapper().updateIgnoreNull(model);
            logger.info("BaseMapper ====> updateIgnoreNull("+modelClassName+" model ), params :"+model.toString());
            return i;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return 0;
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {
        try {
            int i = getBaseMapper().deleteById(id);
            logger.info("BaseMapper ====> deleteById(Long id), params : id= "+id);
            return i;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return 0;
    }

    /**
     * 根据条件删除
     * @param params
     * @return
     */
    @Override
    public int deleteByParams(Map<String, Object> params) {
        try {
            int i = getBaseMapper().deleteByParams(params);
            logger.info("BaseMapper ====> deleteByParams( Map<String, Object> params), params : "+params.toString());
            return i;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return 0;
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<T> findAll() {
        try {
            List<T> listData = getBaseMapper().findAll();
            logger.info("BaseMapper ====> findAll(), params : null");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

    /**
     * 批量新增
     * @param listData
     */
    @Override
    public void insertList(List<T> listData) {
        try {
            if(listData != null && listData.size()>0){
                getBaseMapper().insertList(listData);
                logger.info("BaseMapper ====> insertList(List<"+modelClassName+"> listData )");
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 批量修改
     * @param listData
     */
    @Override
    public void updateList(List<T> listData) {
        try {
            getBaseMapper().updateList(listData);
            logger.info("BaseMapper ====> updateList(List<"+modelClassName+"> listData )");
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Override
    public T findById(Long id) {
        try {
            T model = getBaseMapper().findById(id);
            logger.info("BaseMapper ====> findById(Long d),params :"+id);
            return model;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

    /**
     * 根据Map参数，查询
     * @param params
     * @return
     */
    @Override
    public List<T> findByParams(Map<String, Object> params) {
        try {
            List<T> listData = getBaseMapper().findByParams(params);
            logger.info("BaseMapper ====> findByParams(Map<String, Object> params),params :"+params.toString());
            return listData;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

    @Override
    public PageBean<List<T>> findByPage(Map<String, Object> params) {
        try {
            PageBean page = new PageBean();
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
            page.setPageSize(pageSize);
            page.setPageNum(pageNum);
            //起始行，算出上次结束位置
            page.setStartRow(page.getPageNum()-1*page.getPageSize());
            Integer count = getBaseMapper().count();
            page.setPages(count%page.getPageNum() != 0?count/page.getPageNum()+1:count/page.getPageNum());
            page.setResultData(getBaseMapper().findByPage(params));
            logger.info("BaseMapper ====> findByPage(Map<String, Object> params),params :"+params.toString());
            return page;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

    public PageBean<List<T>> findByPage(T model) {
        try {
            PageBean page = new PageBean();
            int pageNum =1;
            int pageSize =10;
            if (model.getPageNum() != null) {
                pageNum =model.getPageNum();
            }
            if (model.getPageSize() != null) {
                pageSize =model.getPageSize();
            }
            page.setPageSize(pageSize);
            page.setPageNum(pageNum);
            //起始行，算出上次结束位置
            page.setStartRow((page.getPageNum()-1)*page.getPageSize());
            page.setTotal(getBaseMapper().count());
            page.setPages(page.getTotal()%page.getPageNum() != 0 ? page.getTotal()/page.getPageNum()+1:page.getTotal()/page.getPageNum());
            page.setResultData(getBaseMapper().findByPage2(model));
            logger.info("BaseMapper ====> findByPage("+modelClassName+" model),params :"+model.toString());
            return page;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return null;
    }

    public Integer count(){
        try {
            Integer i = getBaseMapper().count();
            logger.info("BaseMapper ====> count()");
            return i;
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.toString());
        }
        return 0;
    }
}

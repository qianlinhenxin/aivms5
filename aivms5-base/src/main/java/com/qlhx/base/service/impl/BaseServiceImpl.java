package com.qlhx.base.service.impl;

import com.qhlx.core.bean.BaseBean;
import com.qhlx.core.mapper.BaseMapper;
import com.qhlx.core.util.IDUtil;
import com.qhlx.core.util.bean.BeanUtil;
import com.qlhx.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
        if(model.getSid() == null){
            model.setSid(IDUtil.getId());
        }
        if(model.getCreateUser() == null){
            model.setCreateUser(0L);
        }
        if(model.getCreateName() == null){
            model.setCreateName("N/A");
        }
        if(model.getCreateIp() == null){
            model.setCreateIp("127.0.0.1");
        }
        if(model.getCreateTime() == null){
            model.setCreateTime(new Date());
        }
        try {
            int i = getBaseMapper().insert(model);
            logger.info("BaseMapper ====> insert("+modelClassName+" model ), params :"+model.toString());
            return i;
        }catch (RuntimeException e) {
            e.printStackTrace();
            logger.error("重复的主键，"+model.getSid());
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
        if(model.getUpdateTime() == null){
            model.setUpdateTime(new Date());
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
    public int updateBySelective(T model) {
        if(model.getUpdateUser() == null){
            model.setUpdateUser(1L);
        }
        if(model.getUpdateName() == null){
            model.setUpdateName("default");
        }
        if(model.getUpdateIp() == null){
            model.setUpdateIp("127.0.0.1");
        }
        if(model.getUpdateTime() == null){
            model.setUpdateTime(new Date());
        }
        try {
            int i = getBaseMapper().updateBySelective(model);
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
     * @param sid
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long sid) {
        try {
            int i = getBaseMapper().deleteByPrimaryKey(sid);
            logger.info("BaseMapper ====> deleteById(Long id), params : id= "+sid);
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
            T model = getBaseMapper().findByPrimaryKey(id);
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
}

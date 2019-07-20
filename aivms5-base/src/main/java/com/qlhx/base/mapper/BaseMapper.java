package com.qlhx.base.mapper;

import java.util.List;
import java.util.Map;

/**
 * @versio 1.0.0
 * @Author xigexb
 * @date 2019/4/6 15:05
 * @description
 */

public interface BaseMapper<T> {

    /**
     * 按照条件查询
     * @param model
     * @return
     */
    List<T> find(T model);

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<T> findByParams(Map<String, Object> params);

    /**
     *根据ID查询
     * @param id
     * @return
     */
    T findById(Long id);


    /**
     * 分页查询
     * @param params
     * @return
     */
    List<T> findByPage(Map<String, Object> params);

    /**
     * 分页查询
     * @param model
     * @return
     */
    List<T> findByPage2(T model);


    /**
     * 查询全部
     * @return
     */
    List<T> findAll();

    /**
     * 新增
     * @param model
     * @return
     */
    int insert(T model);

    /**
     * 批量新增
     * @param listData
     */
    void insertList(List<T> listData);

    /**
     * 修改
     * @param model
     * @return
     */
    int update(T model);

    /**
     * 批量修改
     * @param listData
     */
    void updateList(List<T> listData);

    /**
     * 忽略空值修改
     * @param model
     * @return
     */
    int updateIgnoreNull(T model);

    /**
     * 删除
     * @param model
     */
    void delete(T model);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 根据条件删除
     * @param params
     * @return
     */
    int deleteByParams(Map<String, Object> params);

    /**
     * 总数
     * @return
     */
    Integer count();


}

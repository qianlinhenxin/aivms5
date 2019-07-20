package com.qlhx.base.service;


import com.qlhx.base.bean.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Create by xb
 * The file is [ BaseService] on [ book-system ] project
 * The file path is com.example.booksystem.service.BaseService
 *
 * @versio 1.0.o
 * @Author he ming xi
 * @date 2019/4/6 14:49
 * @description
 */
public interface BaseService<T> {

    /**
     * 按照条件查询
     * @param model
     * @return
     */
    List<T> find(T model);

    /**
     * 查询全部
     * @return
     */
    List<T> findAll();

    /**
     * 根据条件查询
     * @param params
     * @return
     */
    List<T> findByParams(Map<String, Object> params);

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageBean<List<T>> findByPage(Map<String, Object> params);

    /**
     * 分页查询
     * @param model
     * @return
     */
    PageBean<List<T>> findByPage(T model);

    /**
     *根据ID查询
     * @param id
     * @return
     */
    T findById(Long id);

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
     * 忽略空值修改
     * @param model
     * @return
     */
    int updateIgnoreNull(T model);

    /**
     * 批量修改
     * @param listData
     */
    void updateList(List<T> listData);

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
     * 删除
     * @param model
     */
    void delete(T model);

    /**
     * 总数 支持分页
     * @return
     */
    Integer count();
}

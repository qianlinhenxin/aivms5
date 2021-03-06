package com.qhlx.core.util.jdbc.dao;

import java.util.List;

/**
 * Create by xigexb
 *
 * @versio 1.0.0
 * @Author xigexb
 * @date 2019/6/18 9:05
 * @Description
 *      desc:desc:JDBC通用操作基类接口
 */
public  interface BaseDao<T> {

    /**
     * 新增用户
     * @return 新增得对象
     */
    T insert(T t);

    /**
     * 修改
     * @param t
     * @return 受影响的行数
     */
    String update(T t);

    /**
     * 删除
     * @param t 用户ID
     * @return  受影响的行数
     */
    String delete(T t);

    /**
     * 根据对象属性值来查询数据
     * @param t
     * @return List<T> list
     */
    List<T> find(T t);

    /**
     * 根据ID查询数据
     * @param t
     * @return
     */
    T findById(T t);

}

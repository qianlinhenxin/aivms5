package com.qhlx.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qhlx.core.bean.PageBean;
import com.qhlx.core.util.bean.ObjectUtil;

import java.util.List;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/8/3 14:12
 * @Description desc:
 */
public class PageUtil {

    /**
     * 开始分页
     * @param pageNum
     * @param pageSize
     */
    public static void startPage(Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
    }

    /**
     * 简单分页
     * @param list
     * @param <T>
     * @return
     */
    public static <T>  PageBean<T>  page(List<T> list){
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        PageBean<T> pageBean = ObjectUtil.copy(pageInfo, PageBean.class);
        return pageBean;
    }
}

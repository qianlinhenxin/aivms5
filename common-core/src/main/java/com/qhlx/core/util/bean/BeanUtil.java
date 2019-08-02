package com.qhlx.core.util.bean;

import org.springframework.context.ApplicationContext;

/**
 * Create by xb
 * The file is [ BeanUtil] on [ book-system ] project
 * The file path is com.example.booksystem.util.BeanUtil
 *
 * @versio 1.0.o
 * @Author he ming xi
 * @date 2019/4/6 15:25
 * @description
 */
public class BeanUtil {

    private static ApplicationContext  applicationContext;

    //获取上下文
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //设置上下文
    public static void setApplicationContext(ApplicationContext applicationContext) {
        BeanUtil.applicationContext = applicationContext;
    }

    /**
     * getBean
     *
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * getBean
     *
     * @param beanId
     * @param clazz
     * @return
     */
    public static <T> T getBean(String beanId, Class<T> clazz) {
        return applicationContext.getBean(beanId, clazz);
    }

    /**
     * getBean
     *
     * @param beanId
     * @return
     */
    public static Object getBean(String beanId) {
        return applicationContext.getBean(beanId);
    }

    /**
     * containsBean
     *
     * @param beanId
     * @return
     */
    public static boolean containsBean(String beanId) {
        return applicationContext.containsBean(beanId);
    }

}

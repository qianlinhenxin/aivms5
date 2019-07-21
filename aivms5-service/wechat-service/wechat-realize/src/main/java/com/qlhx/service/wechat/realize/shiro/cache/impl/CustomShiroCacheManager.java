//package com.qlhx.service.wechat.realize.shiro.cache.impl;
//
//
//import com.qlhx.service.wechat.realize.shiro.cache.ShiroCacheManager;
//import org.apache.ibatis.cache.CacheException;
//import org.springframework.cache.CacheManager;
//
//import javax.security.auth.Destroyable;
//
///**
// *
// * 开发公司：sojson.com<br/>
// * 版权：sojson.com<br/>
// * <p>
// *
// * shiro Custom Cache
// *
// * <p>
// *
// * 区分　责任人　日期　　　　说明<br/>
// * 创建　周柏成　2016年4月29日 　<br/>
// * <p>
// * *******
// * <p>
// * @author zhou-baicheng
// * @email  json@sojson.com
// * @version 1.0,2016年4月29日 <br/>
// *
// */
//public class CustomShiroCacheManager implements CacheManager, Destroyable {
//
//    private ShiroCacheManager shiroCacheManager;
//
//    @Override
//    public <K, V> Ca che<K, V> getCache(String name) throws CacheException {
//        return getShiroCacheManager().getCache(name);
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        shiroCacheManager.destroy();
//    }
//
//    public ShiroCacheManager getShiroCacheManager() {
//        return shiroCacheManager;
//    }
//
//    public void setShiroCacheManager(ShiroCacheManager shiroCacheManager) {
//        this.shiroCacheManager = shiroCacheManager;
//    }
//
//}

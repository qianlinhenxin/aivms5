package com.qhlx.core.session;


import com.qhlx.core.util.json.GsonUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import java.util.Date;

/**
 * 全局session
 */
public class SessionContext {

    public static final String USER_SESSION_KEY = "QL_TOKEN_KEY";
    
    public static void destroy() {
    	 Session session = SecurityUtils.getSubject().getSession();
         session.removeAttribute(USER_SESSION_KEY);
    }

    public static void init(UserSession userSession){
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(USER_SESSION_KEY, GsonUtil.toJson(userSession));
    }

    /** 获得seesion中的user */
    public static UserSession getUser() {
        return get();
    }

    public static UserSession get() {
        Session session = SecurityUtils.getSubject().getSession();
        String userJson = (String)session.getAttribute(USER_SESSION_KEY);
        UserSession userSession = null;
        if( null != userJson) {
        	userSession = GsonUtil.gsonToBean(userJson, UserSession.class);
        }else {
        	userSession = new UserSession();
        }
        return userSession;
    }
    
    public static String getUserInfoString() {
        Session session = SecurityUtils.getSubject().getSession();
        String userJson = (String)session.getAttribute(USER_SESSION_KEY);

        return userJson;
    }

    /**
     * 获得用户的登录时间,用户登录时候前端选择的登录日期
     *
     * @return
     */
    public static Date loginTime() {
        UserSession userSession = get();
        if (userSession == null) {
            userSession = new UserSession();
        }
        return userSession.getLoginTime();
    }

    /**
     * 获得用户姓名
     *
     * @return
     */
    public static String userName() {
        UserSession userSession = get();
        return userSession.getUserName();
    }

    /**
     * 获得用户账号,就是登录名
     *
     * @return
     */
    public static Long userSid() {
        UserSession userSession = get();
        return userSession.getUserSid();
    }

    public static String nikeName() {
        UserSession userSession = get();
        return userSession.getNikeName();
    }

    /**
     * 获得用户号
     *
     * @return
     */
    public static Long userNo() {
        UserSession userSession = get();
        return userSession.getUserNo();
    }





}

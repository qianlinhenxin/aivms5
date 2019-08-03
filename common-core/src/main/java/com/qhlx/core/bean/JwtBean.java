package com.qhlx.core.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/28 13:42
 * @Description desc:
 */
public class JwtBean {


    /**
     * jwt id
     */
    private String jwtId;

    /**
     * 签发者
     */
    private String issuer;

    /**
     * 所有者
     */
    private String audience;

    /**
     * 生效时间
     */
    private Float minutes;

    /**
     * 主题
     */
    private String subject;

    /**
     * 值map key:string -> object:value
     */
    private Map<String,Object> valueMap;

    /**
     * 返回jwt
     */
    private String jwt;

    /**
     * 返回消息
     */
    private String msg;

    public String getJwtId() {
        return jwtId;
    }

    public void setJwtId(String jwtId) {
        this.jwtId = jwtId;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public Float getMinutes() {
        return minutes;
    }

    public void setMinutes(Float minutes) {
        this.minutes = minutes;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getValueMap() {
        return valueMap;
    }

    public void setValueMap(Map<String, Object> valueMap) {
        this.valueMap = valueMap;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String check(){
        StringBuffer sb = new StringBuffer("不能为空:)");
        if(issuer == null || "".equals(issuer)){
            return sb.insert(0,"Jwt签发者").toString();
        }
        if(audience == null || "".equals(audience)){
            return sb.insert(0,"Jwt所有者").toString();
        }
        if(subject == null || "".equals(subject)){
            return sb.insert(0,"Jwt主题").toString();
        }
        if(minutes == null ){
            minutes = 10F;
            System.out.println("JWT 默认时间生效:10分钟");
        }
        if(valueMap == null ){
            valueMap = new HashMap<>();
            valueMap.put("Sid",System.currentTimeMillis());
            System.out.println("JWT 默认主题:"+valueMap.get("sid"));
        }
        return "ok";
    }

    @Override
    public String toString() {
        return "{\"JwtBean\":{" +
                "\"jwtId=\"'" + jwtId + '\'' +
                ", \"issuer\"='" + issuer + '\'' +
                ", \"audience\"='" + audience + '\'' +
                ", \"minutes\"=" + minutes +
                ", \"subject\"='" + subject + '\'' +
                ", \"valueMap\"=" + valueMap +
                ", \"jwt\"='" + jwt + '\'' +
                ", \"msg\"='" + msg + '\'' +
                '}'+"}";
    }
}

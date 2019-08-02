package com.qhlx.core.exception;


/**
 * <p> 异常类 </p> 
 *
 * @project 	core-api
 * @class 		BusinessException
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    private String code;

    private String message;

    private Object[] ages;

    private String desc;

    public BusinessException(String code, String message, Object[] ages, String desc) {
        this.code = code;
        this.message = message;
        this.ages = ages;
        this.desc = desc;
    }

    public BusinessException() {

    }

    public BusinessException(String code, String message, String desc) {
        this.code = code;
        this.message = message;
        this.desc = desc;
    }

    public BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getAges() {
        return ages;
    }

    public void setAges(Object[] ages) {
        this.ages = ages;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

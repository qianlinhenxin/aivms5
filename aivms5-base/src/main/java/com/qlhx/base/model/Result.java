package com.qlhx.base.model;


import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Result<T> implements Serializable {

    /**
     * 返回码
     */
    private Integer code = 0;

    /**
     * 返回消息
     */
    private String msg = "成功";

    /**
     * 总页数
     */
    private Integer pagecount;

    /**
     * 当前页数
     */
    private Integer pageNum;

    /**
     * 返回内容
     */
    private T content;

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the content
     */
    public T getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(T content) {
        this.content = content;
    }

    /**
     * @return the pagecount
     */
    public Integer getPagecount() {
        return pagecount;
    }

    /**
     * @param pagecount the pagecount to set
     */
    public void setPagecount(Integer pagecount) {
        this.pagecount = pagecount;
    }

    /**
     * @return the pageNum
     */
    public Integer getPageNum() {
        return pageNum;
    }

    /**
     * @param pageNum the pageNum to set
     */
    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

}

package com.qlhx.base.model;

import java.util.Date;

public class MQLogger {

    public static final String ROUTING_KEY = "notify.logger";

    private String serviceName;
    private String title;
    private String content;
    private Date createDate;


    public MQLogger() {
        super();
    }

    public MQLogger(String serviceName, String title, String content, Date createDate) {
        super();
        this.serviceName = serviceName;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


}

package com.qlhx.service.wechat.realize.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@SuppressWarnings("serial")
@XmlRootElement
public class UploadStatusInfo implements Serializable {

    private Integer id;

    private Integer num;

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the num
     */
    public Integer getNum() {
	return num;
    }

    /**
     * @param num
     *            the num to set
     */
    public void setNum(Integer num) {
	this.num = num;
    }

}

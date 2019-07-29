package com.qhlx.core.util.json;

import java.io.Serializable;

/**
 * Create by xb
 * The file is [ RequestData] on [ x-file-system ] project
 * The file path is com.itgo.utils.json.RequestData
 *
 * @versio 1.0.0
 * @Author he ming xi
 * @date 2019/4/8 9:04
 * @description
 */
public class RequestData<T> implements Serializable {

    /**
     * 请求数据
     */
    private T reqData;

    public T getReqData() {
        return reqData;
    }

    public void setReqData(T reqData) {
        this.reqData = reqData;
    }

    public RequestData() {
        super();
    }

}

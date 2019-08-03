package com.qlhx.web.auth.controller;


import com.qhlx.core.bean.Code;
import com.qhlx.core.util.web.ApiResponse;
import com.qhlx.core.util.web.WebResponse;
import com.qhlx.core.vo.BaseVO;

import java.util.Date;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/7/27 15:03
 * @Description desc:
 */
public class BaseController {

    /**
     * 新增缺省参数
     * @param vo
     */
    public void setCreateDefaultColumn(BaseVO vo){
        vo.setCreateTime(new Date());
        vo.setCreateName("NA");
        vo.setCreateUser(1L);
        vo.setCreateIp("127.0.0.1");
    }

    /**
     * 修改缺省参数
     * @param vo
     */
    public void setUpdateDefaultColumn(BaseVO vo){
        vo.setUpdateTime(new Date());
        vo.setUpdateName("NA");
        vo.setUpdateUser(1L);
        vo.setUpdateIp("127.0.0.1");
    }

    /**
     * 返回设置数据
     * @param apiResponse
     * @param webResponse
     */
    public void setResponse(ApiResponse apiResponse, WebResponse webResponse){
        if(apiResponse == null || webResponse == null){
            return;
        }
        webResponse.setBody(apiResponse.getRetContent());
        webResponse.setDesc(apiResponse.getDesc());
        webResponse.setHeaders(apiResponse.getHeaders());
        webResponse.setCode(Code.get(apiResponse.getRetCode()));
    }


}

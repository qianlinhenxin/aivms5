package com.qlhx.service.wechat.realize.dao;


import com.qlhx.service.wechat.realize.model.LeaveApprove;

public interface LeaveApproveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveApprove record);

    int insertSelective(LeaveApprove record);

    LeaveApprove selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveApprove record);

    int updateByPrimaryKey(LeaveApprove record);
}
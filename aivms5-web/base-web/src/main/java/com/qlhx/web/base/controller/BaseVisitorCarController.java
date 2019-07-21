package com.qlhx.web.base.controller;

import com.qlhx.service.base.api.api.record.BaseVisitorCarApi;
import com.qlhx.service.base.api.vo.record.BaseVisitorCarVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/6 10:05
 * @description desc:
 */
@RestController
@RequestMapping("/baseVisitorCar")
public class BaseVisitorCarController {
    @Autowired
    private BaseVisitorCarApi baseVisitorCarApi;

    @RequestMapping(value = "/deleteByPrimaryKey/{id}",method = RequestMethod.POST)
    public int deleteByPrimaryKey(@PathVariable Integer id) {
        return baseVisitorCarApi.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insert(@RequestBody BaseVisitorCarVO record) {
        return baseVisitorCarApi.insert(record);
    }

    @RequestMapping(value = "/insertSelective",method = RequestMethod.POST)
    public int insertSelective(@RequestBody BaseVisitorCarVO record) {
        return baseVisitorCarApi.insertSelective(record);
    }

    @RequestMapping(value = "/selectByPrimaryKey/{id}",method = RequestMethod.POST)
    public BaseVisitorCarVO selectByPrimaryKey(@PathVariable Integer id) {
        return baseVisitorCarApi.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective",method = RequestMethod.POST)
    public int updateByPrimaryKeySelective(@RequestBody  BaseVisitorCarVO record) {
        return baseVisitorCarApi.updateByPrimaryKeySelective(record);
    }


    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    public int updateByPrimaryKey(@RequestBody BaseVisitorCarVO record) {
        return baseVisitorCarApi.updateByPrimaryKey(record);
    }

    @RequestMapping(value = "/findCarByArId/{id}",method = RequestMethod.GET)
    public BaseVisitorCarVO findCarByArId(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
        return baseVisitorCarApi.selectByPrimaryKey(idInt);
    }

}

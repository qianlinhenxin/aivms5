package com.qlhx.web.base.main.controller;

import com.qlhx.model.BaseVisitorCar;
import com.qlhx.service.BaseVisitorCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/6 10:05
 * @description desc:
 */
@CrossOrigin(maxAge = 3600)
@Scope(value = "prototype")
@RestController
@RequestMapping("/baseVisitorCar")
public class BaseVisitorCarController {
    @Autowired
    private BaseVisitorCarService baseVisitorCarService;

    @RequestMapping(value = "/deleteByPrimaryKey/{id}",method = RequestMethod.POST)
    public int deleteByPrimaryKey(@PathVariable  Integer id) {
        return baseVisitorCarService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insert(@RequestBody  BaseVisitorCar record) {
        return baseVisitorCarService.insert(record);
    }

    @RequestMapping(value = "/insertSelective",method = RequestMethod.POST)
    public int insertSelective(@RequestBody BaseVisitorCar record) {
        return baseVisitorCarService.insertSelective(record);
    }

    @RequestMapping(value = "/selectByPrimaryKey/{id}",method = RequestMethod.POST)
    public BaseVisitorCar selectByPrimaryKey(@PathVariable Integer id) {
        return baseVisitorCarService.selectByPrimaryKey(id);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective",method = RequestMethod.POST)
    public int updateByPrimaryKeySelective(@RequestBody  BaseVisitorCar record) {
        return baseVisitorCarService.updateByPrimaryKeySelective(record);
    }


    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    public int updateByPrimaryKey(@RequestBody BaseVisitorCar record) {
        return baseVisitorCarService.updateByPrimaryKey(record);
    }

    @RequestMapping(value = "/findCarByArId/{id}",method = RequestMethod.GET)
    public BaseVisitorCar findCarByArId(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
        return baseVisitorCarService.selectByPrimaryKey(idInt);
    }

}

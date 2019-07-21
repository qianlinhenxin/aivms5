package com.qlhx.service.base.api.api.record;

import com.qlhx.service.base.api.vo.record.BaseVisitorCarVO;
import org.springframework.web.bind.annotation.*;

/**
 * Create by byl
 *
 * @versio 1.0.0
 * @Author baiyanlong
 * @date 2019/7/6 10:05
 * @description desc:
 */
public interface BaseVisitorCarApi {

    String PATH = "/baseVisitorCar";

    @RequestMapping(value = PATH+"/deleteByPrimaryKey/{id}",method = RequestMethod.POST)
    Integer deleteByPrimaryKey(@PathVariable  Integer id);

    @RequestMapping(value = PATH+"/insert",method = RequestMethod.POST)
    int insert(@RequestBody  BaseVisitorCarVO record);

    @RequestMapping(value = PATH+"/insertSelective",method = RequestMethod.POST)
    int insertSelective(@RequestBody BaseVisitorCarVO record);

    @RequestMapping(value = PATH+"/selectByPrimaryKey/{id}",method = RequestMethod.POST)
    BaseVisitorCarVO selectByPrimaryKey(@PathVariable Integer id);

    @RequestMapping(value = PATH+"/updateByPrimaryKeySelective",method = RequestMethod.POST)
    int updateByPrimaryKeySelective(@RequestBody  BaseVisitorCarVO record);

    @RequestMapping(value = PATH+"/updateByPrimaryKey",method = RequestMethod.POST)
    int updateByPrimaryKey(@RequestBody BaseVisitorCarVO record);

    @RequestMapping(value = PATH+"/findCarByArId/{id}",method = RequestMethod.GET)
    BaseVisitorCarVO findCarByArId(@PathVariable String id);

}

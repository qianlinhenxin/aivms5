package com.qlhx.service.base.realize.apiimpl.record;

import com.qlhx.base.util.bean.ObjectUtil;
import com.qlhx.service.base.api.api.record.BaseVisitorCarApi;
import com.qlhx.service.base.api.vo.record.BaseVisitorCarVO;
import com.qlhx.service.base.realize.model.BaseVisitorCar;
import com.qlhx.service.base.realize.service.BaseVisitorCarService;
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
@RequestMapping("/baseVisitorCar")
public class BaseVisitorCarApiImpl implements BaseVisitorCarApi {

    @Autowired
    private BaseVisitorCarService baseVisitorCarService;

    @RequestMapping(value = "/deleteByPrimaryKey/{id}",method = RequestMethod.POST)
    public Integer deleteByPrimaryKey(@PathVariable  Integer id) {
        return baseVisitorCarService.deleteByPrimaryKey(id);
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insert(@RequestBody BaseVisitorCarVO record) {
        return baseVisitorCarService.insert(ObjectUtil.copy(record,BaseVisitorCar.class));
    }

    @RequestMapping(value = "/insertSelective",method = RequestMethod.POST)
    public int insertSelective(@RequestBody BaseVisitorCarVO record) {
        return baseVisitorCarService.insertSelective(ObjectUtil.copy(record,BaseVisitorCar.class));
    }

    @RequestMapping(value = "/selectByPrimaryKey/{id}",method = RequestMethod.POST)
    public BaseVisitorCarVO selectByPrimaryKey(@PathVariable Integer id) {
        BaseVisitorCar baseVisitorCar = baseVisitorCarService.selectByPrimaryKey(id);
        return ObjectUtil.copy(baseVisitorCar,BaseVisitorCarVO.class);
    }

    @RequestMapping(value = "/updateByPrimaryKeySelective",method = RequestMethod.POST)
    public int updateByPrimaryKeySelective(@RequestBody  BaseVisitorCarVO record) {
        return baseVisitorCarService.updateByPrimaryKeySelective(ObjectUtil.copy(record,BaseVisitorCar.class));
    }


    @RequestMapping(value = "/updateByPrimaryKey",method = RequestMethod.POST)
    public int updateByPrimaryKey(@RequestBody BaseVisitorCarVO record) {
        return baseVisitorCarService.updateByPrimaryKey(ObjectUtil.copy(record,BaseVisitorCar.class));
    }

    @RequestMapping(value = "/findCarByArId/{id}",method = RequestMethod.GET)
    public BaseVisitorCarVO findCarByArId(@PathVariable String id) {
        Integer idInt = Integer.valueOf(id);
        BaseVisitorCar visitorCar = baseVisitorCarService.selectByPrimaryKey(idInt);
        return ObjectUtil.copy(visitorCar,BaseVisitorCarVO.class);
    }

}

package com.qlhx.auth.api.api.test;

import com.qhlx.core.bean.PageBean;
import com.qhlx.core.util.web.ApiResponse;
import com.qlhx.auth.api.vo.test.TestVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Create by xigexb
 *
 * @version 1.0.0
 * @Author xigexb
 * @date 2019/8/3 15:10
 * @Description desc:
 */
public interface TestApi {

    String BASE_URL = "";

    @RequestMapping(value=BASE_URL+"/save")
    ApiResponse<Integer> save(TestVO vo);

    @RequestMapping(value=BASE_URL+"/update")
    ApiResponse<Integer> update(TestVO vo);

    /**
     * 忽略空值更新
     * @param vo
     * @return
     */
    @RequestMapping(value=BASE_URL+"/updateBySelective")
    ApiResponse<Integer> updateBySelective(TestVO vo);
    /**
     * 分页查询
     * @param params
     * @return
     */
    @RequestMapping(value =BASE_URL+"/findByPage")
    ApiResponse<PageBean<TestVO>> findByPage(Map<String, Object> params);

    /**
     * 查询全部
     * @return
     */
    @RequestMapping(value =BASE_URL+ "/findAll")
    ApiResponse<List<TestVO>> findAll();

    /**
     * 根据sid来查询数据
     * @param sid
     * @return
     */
    @RequestMapping(value =BASE_URL+ "/findByPrimaryKey")
    ApiResponse<TestVO> findByPrimaryKey(@RequestParam("sid")  Long sid) ;
    /**
     * 根据主键删除数据
     * @param sid
     * @return
     */
    @RequestMapping(value = BASE_URL+"/deleteByPrimaryKey")
    ApiResponse<Integer> deleteByPrimaryKey(@RequestParam("sid")  Long sid);

    /**
     * 根据map参数来查询
     * @param params
     * @return
     */
    @RequestMapping(value =BASE_URL+ "/findByParams")
    ApiResponse<List<TestVO>> findByParams(Map<String, Object> params);


    /**
     * 根据对象来查询数据
     * @param vo
     * @return
     */
    @RequestMapping(value =BASE_URL+ "/findByObject")
    ApiResponse<List<TestVO>> findByObject(TestVO vo);


    /**
     * 根据对象属性来删除
     * @param vo
     * @return
     */
    @RequestMapping(value = BASE_URL+"/deleteByObject")
    ApiResponse<String> deleteByObject(TestVO vo);


    @RequestMapping(value =BASE_URL+ "/deleteByParams")
    ApiResponse<String> deleteByParams(Map<String, Object> params);
    
    
}

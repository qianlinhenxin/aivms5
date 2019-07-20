package com.qlhx.service.base.realize.service.impl;

import com.qlhx.base.util.PadLeftUtil;
import com.qlhx.service.base.realize.mapper.*;
import com.qlhx.service.base.realize.model.*;
import com.qlhx.service.base.realize.mybatis.BaseMybatisDao;
import com.qlhx.service.base.realize.mybatis.page.Pagination;
import com.qlhx.service.base.realize.service.MemberService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MemberServiceImpl extends BaseMybatisDao<BaseMemberMapper> implements MemberService {

    @Autowired
    BaseVisitorMapper baseVisitorMapper;

	@Autowired
    BaseMemberMapper baseMemberMapper;

	@Autowired
    BaseDepartmentMapper baseDepartmentMapper;

	@Autowired
    BaseAccessRecordMapper baseAccessRecordMapper;


	@Autowired
    BaseBlacklistShareMapper baseBlacklistShareMapper;

	@Autowired
    BaseResMapper baseResMapper;
	
	@Override
	public Pagination<BaseMember> selectMemberRecord(Map<String, Object> modelMap, Integer pageNo, Integer pageSize) {
		return super.findPage(modelMap, pageNo, pageSize);
	}

	@Override
	public int insertSelective(BaseMember record) {
		return baseMemberMapper.insertSelective(record);
	}

	@Override
	public BaseMember selectByCardNum(String cardNum) {
		return baseMemberMapper.selectByCardNum(cardNum);
	}

	@Override
	public List<BaseMember> findUserByPhoneOrName(String param, Integer pageindex, Integer pagesize) throws Exception {
		return baseMemberMapper.findUserByPhoneOrName(param, pageindex, pagesize);
	}

	@Override
	public Integer findUserCountByPhoneOrName(String param) throws Exception {
		return baseMemberMapper.findUserCountByPhoneOrName(param);
	}



    @Override
    public BaseMember login(String email, String pswd) {
        return baseMemberMapper.login(email,pswd);
    }

    @Override
    public Integer findEmployeesCountById(Integer deptid) throws Exception {
        return baseMemberMapper.findEmployeesCountById(deptid);
    }

    @Override
    public List<BaseMember> findEmployeesById(Integer depId, Integer pageindex, Integer pagesize) throws Exception {
        return baseMemberMapper.findEmployeesById(depId,pageindex,pagesize);
    }
    /*
本部门和子部门列表
 */
    private Map<Integer, List<Integer>> subDepts = new HashMap<Integer, List<Integer>>();


    @Override
    public Integer findSubDeptEmployeesCountById(Integer depId) throws Exception {
        //局部变量，避免二次查询
        if (!subDepts.containsKey(depId)) {
            //获取某部门子部门列表
            List<Integer> deptList = new ArrayList<>();
            deptList.add(depId);
            deptList.addAll(CreateDeptsContainsSub(deptList));

            subDepts.put(depId, deptList);
        }
        //获取所有部门下人员总数


        return baseMemberMapper.findEmployeesCountByDeptIds(subDepts.get(depId));
    }

    @Override
    public Integer findEmployeesCountByIdnum(String idnum) throws Exception {
        return baseMemberMapper.findEmployeesCountByIdnum(idnum);
    }

    @Override
    public UUserDep findDepartmentByID(Integer deptId) {

        return baseDepartmentMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public BaseVisitor findVisitorByIdNum(String idNum) throws Exception {
        return baseVisitorMapper.findVisitorByIdNum(idNum);
    }

    @Override
    public BaseVisitor findVisitorById(Integer vID)  {
        return baseVisitorMapper.selectByPrimaryKey(vID);
    }

    @Override
    public BaseVisitor findVisitorByRCode(String rCode) throws Exception {
        return baseVisitorMapper.findVisitorByRCode(rCode);
    }

    @Override
    public BaseVisitor findVisitorByIcNum(String icNum) throws Exception {
        return baseVisitorMapper.findVisitorByIdNum(icNum);
    }

    @Override
    public BaseAccessRecord findVisitorAccessByICNum(String icNum) {
        return baseVisitorMapper.findVisitorAccessByICNum(icNum);
    }

    @Override
    public BaseMember findUserByIdno(String param) throws Exception {
        return baseMemberMapper.findUserByIdno(param);
    }
    @Value("${vcardnum}")
    public String vcardnum;// 卡号位数 0 八位 1 十位
    @Override
    public List<BaseAccessRecord> checkCardIsActive(String cardNum)throws Exception  {
        // TODO Auto-generated method stub
        String carstr = cardNum;
        if ("1".equals(vcardnum)) {
            carstr = PadLeftUtil.padRight(cardNum, 10, '0');
        } else if ("0".equals(vcardnum)) {
            carstr = PadLeftUtil.padRight(cardNum, 8, '0');
        }
        return baseAccessRecordMapper.selectByCardNum(carstr);
    }

    @Override
    public List<BaseMember> findSubDeptEmployeesById(Integer depId, Integer pageindex, Integer pagesize) {
        //获取部门和子部门的所有ID，在查询人员数量的时候，已经实现
        List<Integer> deptIds = subDepts.get(depId);

        Map map = new HashedMap();
        map.put("deptIds", deptIds);
        map.put("pageindex", pageindex);
        map.put("pagesize", pagesize);

        //获取部门下所有子部门人员列表
        return baseMemberMapper.findEmployeesByDeptIdS(map);
    }

    @Override
    public List<BaseMember> findEmployeesByIdnum(String idnum, Integer pageindex, Integer number) throws Exception {
        return baseMemberMapper.findEmployeesByIdnum(idnum,pageindex,number);
    }

    @Override
    public List<UUserDep> findDepartment(String companyNum) throws Exception {
        return baseDepartmentMapper.findDepartment(companyNum);
    }

    @Override
    public List<UUserDep> findDepartmentByPY(String companyNum, String pinyin) throws Exception {

        return baseDepartmentMapper.findDepartmentByPY(companyNum, pinyin);
    }

    /*
     * 根据部门，获取所有子部门的ID
     * */
    private List<Integer> CreateDeptsContainsSub(List<Integer> depIds) throws Exception {
        //先添加本部门ID
        List<Integer> list = baseDepartmentMapper.findSubdeptidsByPrideptIds(depIds);
        if (list != null && !list.isEmpty()) {
            //下一级有部门，继续查下一级的子部门，负责不再查
            List<Integer> listTemp = CreateDeptsContainsSub(list);
            //把下一级的添加到上一级部门中
            if (listTemp != null && !list.isEmpty()) {
                list.addAll(listTemp);
            }
        }
        //获取子部门的ID
        return list;
    }

    @Override
    public Integer recoverCard(BaseAccessRecord record) throws Exception {
        // TODO Auto-generated method stub
        return baseAccessRecordMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Map<String, Integer> findTodayIntoAndOutNum(String date) throws Exception {
        return  baseAccessRecordMapper.findTodayIntoAndOutNum(date);
    }

    @Override
    public List<BaseVisitor> findBlackListByIdNum(String idNum) throws Exception {
//        return baseVisitorMapper.findBlackListByIdNum(idNum);
        return null;
    }

    @Override
    public List<BaseBlacklistShare> findBlackShare(String idNum) {
//        return baseBlacklistShareMapper.findBlackShare(idNum);
        return null;
    }

    @Override
    public List<BaseVisitor> isAppointment(String idNum) throws Exception {
//        return baseVisitorMapper.isAppointment(idNum);
        return null;
    }

    @Override
    public List<BaseAccessRecord> findAccessRecordByVid(Integer vID) throws Exception {
//        return baseAccessRecordMapper.findAccessRecordByVid(vID);
        return null;
    }

    @Override
    public BaseMember findUserByPhoneNum(String phone) throws Exception {
//        return baseMemberMapper.findUserByPhoneNum(phone);
        return null;
    }


    @Override
    public List<BaseAccessRecord> isAccessRecord(BaseAccessRecord r) throws Exception {
//        return baseAccessRecordMapper.isAccessRecord(r);
        return null;
    }

    @Override
    public Integer updateAccessRecord(BaseAccessRecord record) throws Exception {
        return null;
    }

    @Override
    public List<BaseVisitor> isLogOff(String idNum) throws Exception {
        return baseVisitorMapper.isLogOff(idNum);
    }

    @Override
    public Integer updateVisitorInfo(BaseVisitor visitor) throws Exception {
        return null;
    }

    @Override
    public BaseAccessRecord findLastAccessRecordByIdNum(String idNum) throws Exception {
        return baseAccessRecordMapper.findLastAccessRecordByIdNum(idNum);
    }

    @Override
    public BaseMember findUserInfoById(long id) throws Exception {
        return  baseMemberMapper.selectUserInfoByPrimaryKey(id);
    }

    @Override
    public List<BaseRes> findDraginAndTakeoutRes() throws Exception {
        return baseResMapper.findDraginAndTakeoutRes();
    }
}

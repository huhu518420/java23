package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.entity.DeptExample;
import com.kaishengit.crm.mapper.DeptMapper;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.weixin.WeiXinUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huhu5 on 2017/7/17.
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private WeiXinUtil weiXinUtil;

    @Override
    public List<Dept> findAllDept() {
        return deptMapper.selectByExample(new DeptExample());
    }

    @Override
    @Transactional
    public void saveDept(Dept dept) {
        deptMapper.insert(dept);

        //同步到微信  同步到微信的时候insert方法要使用主键进行插入 useGeneratedKeys="true" keyProperty="id"
        //在deptMapper.xml中使用useGeneratedKeys="true" keyProperty="id"属性在添加的时候会返回一个id,并会自动封装在对象里
        weiXinUtil.createDept(dept.getId(),dept.getpId(),dept.getDeptName());

    }


}

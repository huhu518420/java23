package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;

import java.util.List;

/**
 * Created by huhu5 on 2017/7/17.
 */
public interface DeptService {

    List<Dept> findAllDept();

    void saveDept(Dept dept);


}


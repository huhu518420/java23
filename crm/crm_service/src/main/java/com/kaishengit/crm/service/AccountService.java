package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.exception.ServiceException;

import javax.naming.AuthenticationException;
import java.util.List;

/**
 * Created by huhu5 on 2017/7/17.
 */
public interface AccountService {


    List<Account> findAllAccount();


    void addNewAccount(Account account, Integer[] deptId);

    Long countAll();


    /**
     * 根据AccountID查询
     * @param deptId
     * @return
     */
    List<Account> findByDeptId(Integer deptId);

    /**
     * 根据accountId查询过滤后的数量
     * @param deptId
     * @return
     */
    Long countByDeptId(Integer deptId);

    void delAccountById(Integer id);

    Account login(String mobile, String password) throws AuthenticationException;


    void changePassword(String oldPassword, String password, Account account) throws ServiceException;

}

package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;


import java.util.List;
import java.util.Map;

/**
 * Created by huhu5 on 2017/7/20.
 */
public interface CustomerService {





    Object findAllTrade();

    Object findAllSource();

    void saveNewCustomer(Customer customer, Account account);

    Customer findCustomerById(Integer id);

    void upDataCustomer(Customer customer);

    void delMyCustomerById(Integer id);

    void shareCustomerToPublic(Customer customer,Account account);

    void transferCustomerToAccount(Customer customer, Integer accountId, Account account);


    PageInfo<Customer> findPublicCustomer(Map<String, Object> mapList);

    PageInfo<Customer> findMyCustomer(Map<String, Object> mapList);

    void saveNewPublicCustomer(Customer customer);


    List<Customer> findCustomerByAccountId(Integer id);

    List<Map<String,Object>> findCustomerLevelCount();

}

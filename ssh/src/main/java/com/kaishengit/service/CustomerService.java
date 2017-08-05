package com.kaishengit.service;

import com.kaishengit.dao.CustomerDao;
import com.kaishengit.pojo.Customer;
import com.kaishengit.util.orm.Condition;
import com.kaishengit.util.orm.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huhu5 on 2017/8/3.
 */
@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;


    public Customer findById(int i) {
        return customerDao.findById(i);
    }

    public void saveCustomer(Customer customer) {
        customerDao.save(customer);
    }

    public List<Customer> findAll() {

        return customerDao.findAll();
    }

    public List<Customer> findByProperty(String propertyName,Object value) {
        return customerDao.findByProperty(propertyName,value);
    }

    public List<Customer> findByCondition(Condition... conditions) {
        return customerDao.findByCondition(conditions);
    }

    public Page<Customer> findByPageNum(Integer pageNum) {
        return customerDao.findByPageNum(pageNum,5);
    }

    /**
     * 根据当前页和搜索集合查询customer对象并封装到page中
     * @param pageNum  当前页
     * @param conditions  搜索条件集合
     * @return
     */
    public Page<Customer> findByPageNum(Integer pageNum, Condition... conditions) {


        return customerDao.findByPageNum(pageNum,5,"id","desc",conditions);
    }


}

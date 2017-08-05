package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huhu5 on 2017/7/20.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    //获取配置文件中的trade行业
    @Value("#{'${customer.trade}'.split(',')}")
    private List<String> industryList;
    //获取配置文件中的sources来源
    @Value("#{'${customer.source}'.split(',')}")
    private List<String> sourceList;

    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 获取所有行业数据
     * @return
     */
    @Override
    public List<String> findAllTrade() {
        return industryList;
    }

    /**
     * 获取所有客户来源数据
     * @return
     */
    @Override
    public List<String> findAllSource() {
        return sourceList;
    }

    /**
     * 添加新客户
     * @param customer  客户对象
     * @param account  当前登陆的用户
     */
    @Override
    public void saveNewCustomer(Customer customer, Account account) {

        customer.setAccountId(account.getId());
        customer.setCreateTime(new Date());
        customerMapper.insert(customer);
    }

    @Override
    public Customer findCustomerById(Integer id) {

        return customerMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改客户
     * @param customer
     */
    @Override
    public void upDataCustomer(Customer customer) {

        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void
    delMyCustomerById(Integer id) {
        customerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 将客户放入公海
     * @param customer
     * @param account
     */
    @Override
    public void shareCustomerToPublic(Customer customer,Account account) {
        customer.setId(null);
        //给出备注
        customer.setRemark(account.getUserName()+"已经将"+customer.getCustName()+"放入公海");
        customerMapper.updateByPrimaryKey(customer);
    }

    /**
     * 将客户转交给其他用户
     * @param customer
     * @param accountId
     * @param account
     */
    @Override
    public void transferCustomerToAccount(Customer customer, Integer accountId, Account account) {

        customer.setAccountId(accountId);

        customer.setRemark(account.getUserName()+"已经将"+customer.getCustName()+"转交给"+account.getUserName());

        customerMapper.updateByPrimaryKey(customer);
    }

    /**
     * 公海客户
     * @param mapList
     * @return
     */
    @Override
    public PageInfo<Customer> findPublicCustomer(Map<String, Object> mapList) {

        //将pageNum转为整形
        Integer pageNum = (Integer) mapList.get("pageNum");

        //调用pageHelp进行分页
       PageHelper.startPage(pageNum,5);

       List<Customer> customerList = customerMapper.findPublicCustomer(mapList);
        //返回pageInfo对象
        return new PageInfo<>(customerList);
    }

    /**
     * 查询当前登陆对象的客户
     * @param mapList 条件查寻集合
     * @return
     */
    @Override
    public PageInfo<Customer> findMyCustomer(Map<String, Object> mapList) {

        //获取pageNum并转为整形
        Integer pageNum = (Integer) mapList.get("pageNum");
        //调用PageHelper进行分页
        PageHelper.startPage(pageNum,10);
        //调用customerMapper进行查询
        List<Customer> customerList = customerMapper.findByQueryParam(mapList);
        //返回pageInfo对象
        return new PageInfo<>(customerList);
    }

    /**
     * 新增公海客户
     * @param customer
     */
    @Override
    public void saveNewPublicCustomer(Customer customer) {

        customer.setCreateTime(new Date());

        customerMapper.insert(customer);
    }

    @Override
    public List<Customer> findCustomerByAccountId(Integer id) {

        return customerMapper.findCustomerByAccountId(id);
    }

    /**
     * 查询客户星级数量
     * @return
     */
    @Override
    public List<Map<String, Object>> findCustomerLevelCount() {

        return customerMapper.findCustomerLevelCount();
    }


}

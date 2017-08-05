package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountDeptExample;
import com.kaishengit.crm.entity.AccountDeptKey;
import com.kaishengit.crm.entity.AccountExample;
import com.kaishengit.crm.mapper.AccountDeptMapper;
import com.kaishengit.crm.mapper.AccountMapper;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.exception.AuthenticationException;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by huhu5 on 2017/7/17.
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountDeptMapper accountDeptMapper;

    //注入加密文件,并给节点取名passwordSalt
    @Value("${password.salt}")
    private String passwordSalt;

    @Override
    public List<Account> findAllAccount() {

        //System.out.println("AccountService中。。");
        return accountMapper.selectByExample(new AccountExample());
    }

    @Override
    @Transactional   //涉及多表添加事物
    public void addNewAccount(Account account, Integer[] deptId) {

        //添加员工
        account.setPassword(DigestUtils.md5Hex(passwordSalt + account.getPassword()));

        account.setCreateTime(new Date());
        accountMapper.insert(account);

        //添加员工和部门关系
        for(Integer deptIds : deptId) {

            //创建员工和部门关系表对象
            AccountDeptKey accountDeptKey = new AccountDeptKey();
            //给员工的部门添加deptId
            accountDeptKey.setDeptId(deptIds);
            //添加员工id
            accountDeptKey.setAccountId(account.getId());

            accountDeptMapper.insert(accountDeptKey);

        }
    }

    @Override
    public Long countAll() {

        return accountMapper.countByExample(new AccountExample());

    }

    @Override
    public List<Account> findByDeptId(Integer deptId) {

        if(new Integer(1000).equals(deptId)) {
            deptId = null;
        }
        return accountMapper.findByDeptId(deptId);
    }

    /**
     * 根据accountId查询过滤后的数量
     * @param deptId
     * @return
     */
    @Override
    public Long countByDeptId(Integer deptId) {
        if(new Integer(1000).equals(deptId)) {
            deptId = null;
        }
        return accountMapper.countByDeptId(deptId);
    }

    @Override
    @Transactional
    public void delAccountById(Integer id) {
        //先删除与部门的关联关系
        //根据关联关系表对象
        AccountDeptExample accountDeptExample = new AccountDeptExample();

        accountDeptExample.createCriteria().andAccountIdEqualTo(id);
        //调用关联关系的mapper进行删除
        accountDeptMapper.deleteByExample(accountDeptExample);

        //再根据id删除
        AccountExample accountExample = new AccountExample();
        accountExample.createCriteria().andIdEqualTo(id);
        accountMapper.deleteByExample(accountExample);

    }

    @Override
    public Account login(String mobile, String password) throws AuthenticationException {
        //实现加密和登陆
        //1.根据手机号码查找Account
        Account account = accountMapper.findByMobileLoderDept(mobile);
        //加密并判断account中的密码是否和登陆的一样

        if(DigestUtils.md5Hex(passwordSalt + password).equals(account.getPassword())) {

            return account;
        }

        throw new AuthenticationException("账号或密码错误");
    }

    /**
     * 修改密码
     * @param oldPassword
     * @param password
     * @param account
     */
    @Override
    public void changePassword(String oldPassword, String password, Account account)throws ServiceException {

        //判断旧密码是否正确
        if(DigestUtils.md5Hex(passwordSalt + oldPassword).equals(account.getPassword())) {
            //修改密码
            //根据主键有目的性的修改
            accountMapper.updateByPrimaryKeySelective(account);

        }else {
            throw new ServiceException("旧密码错误");
        }
    }


}

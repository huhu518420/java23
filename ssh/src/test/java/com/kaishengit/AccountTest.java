package com.kaishengit;

import com.kaishengit.pojo.Account;
import com.kaishengit.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by huhu5 on 2017/8/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AccountTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void findById() {

        Account account = accountService.findById(1016);
        System.out.println(account.getUserName());
    }

    @Test
    public void findAll() {
        List<Account> accountList = accountService.findAll();
        for(Account account : accountList) {
            System.out.println(account.getUserName()+ "->" + account.getMobile());
            System.out.println(account.getPassword());
        }
    }



}

package com.kaishengit.service;

import com.kaishengit.dao.AccountDao;
import com.kaishengit.pojo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by huhu5 on 2017/8/3.
 */
@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountDao accountDao;

    public Account findById(int i) {

        return accountDao.findById(i);
    }

    public List<Account> findAll() {
       return accountDao.findAll();
    }
}

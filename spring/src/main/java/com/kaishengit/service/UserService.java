package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

/**
 * Created by huhu5 on 2017/7/11.
 */
@Repository
public class UserService {
    @Autowired
    private UserDao userDao;

    //事物加载Service的方法上
    @Transactional
    public void save(User user) throws SQLException{
        userDao.save(user);
        userDao.save(user);
    }

}

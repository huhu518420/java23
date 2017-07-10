package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.WeixinDao;

/**
 * Created by huhu5 on 2017/7/9.
 */
public class UserService {
    private UserDao userDao;
    private WeixinDao weixinDao;
    //构造方法注入

    public UserService(UserDao userDao, WeixinDao weixinDao) {
        this.userDao = userDao;
        this.weixinDao = weixinDao;

    }





    /*
    依赖注入(不用new创建一个类的对象)
    UserDao依赖UserService
    * */
   /*
    private UserDao userDao;
    //给对象set注入时提供set方法
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
*/
    public void save() {
        userDao.save();


    }
}

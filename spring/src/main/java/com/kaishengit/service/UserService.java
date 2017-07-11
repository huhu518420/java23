package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import com.kaishengit.dao.WeixinDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huhu5 on 2017/7/9.
 */
@Service
public class UserService {
    //自动注入添加在属性上
    @Autowired
    private UserDao userDao;

    @Autowired
    private WeixinDao weixinDao;

   /* //构造方法注入
    public UserService(UserDao userDao, WeixinDao weixinDao) {
        this.userDao = userDao;
        this.weixinDao = weixinDao;

    }
*/
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
        //userDao.save();
        /*if(1==1) {
            throw new RuntimeException("执行异常");
        }*/

    }

   public void sum() {
        userDao.sum(10,20);
   }
}

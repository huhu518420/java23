package com.kaishengit.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huhu5 on 2017/7/9.
 */
public class UserDaoTest {
    @Test
    public void  save() {
        //1.创建spring容器
        //ApplicationContext是一个接口，ClassPathXmlApplicationContext从ClassPath中获取Spring的配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //2.从容器中获取对象
        UserDao userDao = (UserDao) applicationContext.getBean("userDao2");
        userDao.save();
    }
}

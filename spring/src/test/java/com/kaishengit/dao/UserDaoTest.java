package com.kaishengit.dao;

import com.kaishengit.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by huhu5 on 2017/7/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    @Test
    public void  save() {

        User user = new User();
        user.setUser_name("丽丽");
        user.setAddress("河南");
        user.setPassword("123123");
        user.setDept_id(1);
        userDao.save(user);

    }

    @Test
    public void findById() {
        User user = userDao.findAllById(2);
        System.out.println(user.getUser_name()+"->"+user.getAddress());
        //断言user不为null
        Assert.assertNotNull(user);
    }

    @Test
    public void finByName() {
        User user = userDao.findByName("张三");
        System.out.println(user.getId());

    }

    @Test
    public void findByAddress() {
        List<User> users = userDao.findByAddress("河南");
        for(User user : users) {
            System.out.println(user.getUser_name() + "->" + user.getAddress());
        }
    }
    @Test
    public void count() {
        Long count = userDao.count();
        //Assert.assertEquals(13L,count.longValue());
    }
    @Test
    public void findAll() {
        List<User> userList = userDao.findAll();
        for (User users:userList
             ) {
            System.out.println(users.getUser_name()+"->"+users.getAddress());
        }
        Assert.assertEquals(userList.size(),3);
    }


}

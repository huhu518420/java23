package com.kaishengit.dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huhu5 on 2017/7/9.
 */
public class WeiXinDaoTest {
    @Test
    public  void  test() {
        //1.创建容器，从ClassPath中获取spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //从容器（applicationCext.xml）中获取对象，参数为Java类(Bean)里的id属性值
        WeixinDao weixinDao = (WeixinDao) applicationContext.getBean("weixinDao1");

        weixinDao.save();
    }

}

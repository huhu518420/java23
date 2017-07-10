package com.kaishengit.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huhu5 on 2017/7/9.
 */


public class UserServiceTest {
    @Test
    public void  save() {
        //创建容器,获取的是需要加载的ClassPath路径下的spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        //获取之前需要先注入
        //从容器（applicationCext.xml）中获取对象，参数为Java类(Bean)里的id属性值
       UserService userService = (UserService) applicationContext.getBean("userService");

       userService.save();

        //BasicInjectService basicInjectService = (BasicInjectService) applicationContext.getBean("basicInjectService");
        //basicInjectService.show();

    }


}

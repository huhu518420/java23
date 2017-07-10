package com.kaishengit.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huhu5 on 2017/7/9.
 */
public class basicInjectTest {

    @Test
    public void show() {

        //创建容器,从ClassPath路径中获取spring配置对象
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器（applicationCext.xml）中获取对象，参数为Java类(Bean)里的id属性值
        BasicInjectService basicInjectService = (BasicInjectService) applicationContext.getBean("basicInjectService");
        basicInjectService.show();

    }
}

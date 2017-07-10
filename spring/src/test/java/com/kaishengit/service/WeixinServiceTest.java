package com.kaishengit.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by huhu5 on 2017/7/9.
 */
public class WeixinServiceTest {
    @Test
    public void saveWeixin() {
        //创建容器,获取的是需要加载的ClassPath路径下的spring配置文件
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        WinxinService winxinService = (WinxinService) applicationContext.getBean("weixinDao");
        winxinService.save();

    }
}

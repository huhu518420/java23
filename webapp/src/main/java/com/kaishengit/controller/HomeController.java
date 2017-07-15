package com.kaishengit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by huhu5 on 2017/7/14.
 */
@Controller
public class HomeController {

    //客户端发起请求的时候自动扫描器会自动扫描对应的请求方法和路径。。再又试图解析器（解析）跳转到页面
    //@RequestMapping(value="/hello",method = RequestMethod.GET) //以get的方法请求
   @GetMapping("/hello")
    public String helloword() {
        System.out.println("hello,springMVC");
        //return 的是试图的名称
        return "hello";
    }
}

package com.kaishengit.controller;

import com.kaishengit.entity.User;
import org.apache.commons.io.IOUtils;
import org.junit.runner.Request;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

/**
 * Created by huhu5 on 2017/7/14.
 */
@Controller
@RequestMapping("/user")   //添加在类上表示公共请求前缀
public class UserController {

    @GetMapping("/show")
    public String userShow() {
        System.out.println("show ");
        return "user/show";
    }

    @GetMapping("/save")
    public String userSave() {

        System.out.println("userSave。。。 ");
        return "user/save";
        //return "redirect:/user";
    }

    @PostMapping("/save")
    //获取表单的值，参数和表单里的参数对应
    public String save(User user, String zipcode,
                       RedirectAttributes redirectAttributes,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       HttpSession session) {
        System.out.println("userName->"+user.getUserName()+"address->"+user.getAddress()+"zipcode->"+zipcode);
        //通过RedirectAttributes属性的addFlashAttribute方法给页面返回提示信息
        redirectAttributes.addFlashAttribute("message","保存成功");

        //重定向到/user/save方法中
        return  "redirect:/user/save";
    }

    //":"号后面的是正则表达式
    @GetMapping("/{id:\\d+}")
    public String userId(@PathVariable Integer id) {
        //@PathVariable将id的值赋值给路径变量
        System.out.println("usetId->"+id);
        return "user/list";
    }

    //第一种传值方式
    /*@RequestMapping(method = RequestMethod.GET)
   public String list(Model model) {
        model.addAttribute("name","jack");  //将值传到页面
        return "user/list";
    }*/

/*
    //第二种传值方式
    @RequestMapping(method = RequestMethod.GET)
   public ModelAndView list() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user/list");
        modelAndView.addObject("name","jack");
        return modelAndView;

    }*/

    @GetMapping("/api/show")
    @ResponseBody  //返回的是内容而不是试图
    public User show() {
        //当返回值是一个对象时需要转换器才能识别出类型
        User user = new User();
        user.setId(100);
        user.setAddress("北京");
        user.setUserName("丽丽");
        //将对象返回到浏览器，还可以返回一个集合等
        return user;
    }

    @GetMapping
    public ModelAndView list(
            @RequestParam(required = false,defaultValue = "1")Integer page) {

        System.out.println("page->"+page);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("user/list");
        mav.addObject("name","json");
        return mav;
    }

    //文件上传
    @PostMapping("/upload")
    public String upload(MultipartFile name,
                         RedirectAttributes redirectAttributes) {

        //MultipartFile参数的值要和页面name的属性一至
        System.out.println(name.getName());
        System.out.println(name.getOriginalFilename());
        System.out.println(name.getContentType());
        System.out.println(name.getSize());
        redirectAttributes.addFlashAttribute("message","上传成功");

        try {

            InputStream inputStream = name.getInputStream();

            OutputStream outputStream = new FileOutputStream(new File("D:/logs",name.getOriginalFilename()));
            IOUtils.copy(inputStream,outputStream);

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/user/save";
    }

}

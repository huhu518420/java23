package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpSession;

/**
 * 登陆控制器
 * Created by huhu5 on 2017/7/19.
 */
@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;


    /**
     * 跳转到登陆界面
     * @return
     */
    @GetMapping("/")
    public String login() {

        return "login";
    }

    /**
     * 实现登陆
     * @param mobile
     * @param password
     * @param httpSession
     * @return
     * 请求路径什么的不写默认为"/"
     */
    @PostMapping("/")
    @ResponseBody
    public AjaxResult login(String mobile, String password, HttpSession httpSession) {

        Account account = null;
        try {

            account = accountService.login(mobile,password);
            //在登陆的时候给当前登陆的account设置一个session属性
            httpSession.setAttribute("curr_user",account);
            return AjaxResult.success();

        } catch (AuthenticationException e) {

            return AjaxResult.error(e.getMessage());
        }

    }


    /**
     * 安全退出
     * @param session
     * @param redirectAttributes
     * @return
     */
    //安全退出
    @GetMapping("/logout")
    public String loginOut(HttpSession session, RedirectAttributes redirectAttributes) {

        //清空session
        session.invalidate();
        //给出提示
        redirectAttributes.addFlashAttribute("你已经安全退出");
        //重定向到登陆页面
        return "redirect:/";

    }



    /**
     * 个人设置
     */
    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }


    @PostMapping("/profile")
    @ResponseBody
    public AjaxResult proFile(String oldPassword,String password,HttpSession session) {

        Account account = (Account) session.getAttribute("curr_user");

        try {
            accountService.changePassword(oldPassword, password, account);

            //修改成功重新登陆
            session.invalidate();
            return AjaxResult.success();
        }catch (ServiceException ex){
            return AjaxResult.error(ex.getMessage());
        }
    }

}

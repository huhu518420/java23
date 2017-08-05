package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.CustomerService;

import com.kaishengit.crm.service.TaskService;
import com.kaishengit.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huhu5 on 2017/7/20.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TaskService taskService;
    /**
     *客户首页
     * @return
     */
    @GetMapping("/my")
    public String myCustomerHome(
            @RequestParam(required = false,defaultValue = "1",value = "p")Integer pageNum,
            @RequestParam(required = false,defaultValue = "",value = "keyword")String keyword,
            Model model,
            HttpSession session) {

        //页面传来的值有pageNum->p和keyword
        //获取session传来的当前登陆的Account对象

        Account account = (Account) session.getAttribute("curr_user");

        //将页面传来的分页数,搜索关键字，当前账户id量放入Map集合
        Map<String,Object> mapList = new HashMap<>();
        mapList.put("pageNum",pageNum);

        //对keyword进行转码
        keyword = StringsUtil.isoToUtf8(keyword);

        mapList.put("keyword",keyword);
        mapList.put("accountId",account.getId());


        //根据封装的集合查找Customer对象并将Customer对象封装到PageInfor中
        PageInfo<Customer> pageInfo = customerService.findMyCustomer(mapList);
        //传回页面进行获取和现实

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("keyword",keyword);

        return "customer/my_home";
    }


    /**
     * 新增客户
     */
    @GetMapping("/my/new")
    public String newMyCustomer(Model model) {
        model.addAttribute("industryList",customerService.findAllTrade());
        model.addAttribute("sourceList",customerService.findAllSource());
        return "customer/new_mycustomer";
    }

    @PostMapping("/my/new")
    public String saveCustomer(Customer customer, RedirectAttributes redirectAttributes,HttpSession session) {

        //从session中获得当前登陆的用户
        Account account = (Account) session.getAttribute("curr_user");
        customerService.saveNewCustomer(customer,account);
        redirectAttributes.addFlashAttribute("message","添加用户成功");
        return "redirect:/customer/my";
    }

    //显示客户详情
    @GetMapping("/my/{id:\\d+}")
    public String showCustomerInfro(@PathVariable Integer id, Model model, HttpSession session) {
        //需要获取的值有当前客户id，往页面返值对象Model，session

        //1.获取当前登陆用户对象
        Account account = (Account) session.getAttribute("curr_user");

        //2.根据页面传来的客户id查找客户对象
        Customer customer = customerService.findCustomerById(id);
        if(customer==null) {
            throw  new NotFoundException();
        }
        //3.判断客户是不是当前用户的客户
        if(!customer.getAccountId().equals(account.getId())) {
            throw  new ForbiddenException();
        }
        //查出所有的用户返回页面下拉框读取
        List<Account> accountList = accountService.findAllAccount();

        //根据CustomerId去task表里查找对应的待办事项
        List<Task> taskList = taskService.findTaskByCustomerId(id);
        model.addAttribute("taskList",taskList);

        model.addAttribute("accountList",accountList);

        //4.将客户对象返回页面进行显示
        model.addAttribute("customer",customer);

        return "/customer/info";
    }


    /**
     * //编辑客户
     * @param model
     * @return
     */
    @GetMapping("/my/{id:\\d+}/edit")
    public String editMyCustomer(@PathVariable Integer id, Model model,
                                 HttpSession session) {
        //1.获取登陆对象
        Account account = (Account) session.getAttribute("curr_user");
        //2.根据id判断客户是否存在
        Customer customer = customerService.findCustomerById(id);
        if (customer==null) {
            throw new NotFoundException();
        }
        //3.判断客户是否是当前用户的客户
        if (!customer.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }

        //返回配置文件中的下拉框值
        model.addAttribute("industryList",customerService.findAllTrade());
        model.addAttribute("sourceList",customerService.findAllSource());

        model.addAttribute(customer);
        return "customer/edit_mycustomer";
    }

    @PostMapping("/my/{id:\\d+}/edit")
    public String editCustomer(Customer customer,HttpSession session,
                               RedirectAttributes redirectAttributes) {
    //需要获取的值有，保存的customer对象，当前登陆用户，提示对象redirectAttributes
        Account account = (Account) session.getAttribute("curr_user");

        //判断如果客户是当前用户的客户则进行修改
        if(!customer.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }

        customerService.upDataCustomer(customer);

        redirectAttributes.addFlashAttribute("修改成功");
        return "redirect:/customer/my"+customer.getId();
    }


    //删除客户
    @GetMapping("/my/{id:\\d+}/del")
    public String delMyCustomer(@PathVariable Integer id,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        //需要客户id，当前用户对象，redirectAttributes
        Account account = (Account) session.getAttribute("curr_user");
        //判断是否有删除权限(客户是否属于当前用户)
        Customer customer = customerService.findCustomerById(id);

        if(!customer.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }
        //实现删除
        customerService.delMyCustomerById(id);

        redirectAttributes.addFlashAttribute("删除成功");
        return "redirect:/customer/my";

    }

    //放入公海
    @GetMapping("/my/{id:\\d+}/share/public")
    public String sharePublic(@PathVariable Integer id,
                              RedirectAttributes redirectAttributes,
                              HttpSession session) {
        //需要获取的参数有,客户id，sesson,requestAttributes
        Account account = (Account) session.getAttribute("curr_user");
        //根据id查询客户是否存在
        Customer customer = customerService.findCustomerById(id);
        if(customer==null) {
            throw new NotFoundException();
        }

        if(!customer.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }

        //将客户放入公海(将客户到 id 改为null),将account放入是为零放入是指定是谁放入的
        customerService.shareCustomerToPublic(customer,account);
        redirectAttributes.addFlashAttribute("message","成功将客户[ "+customer.getCustName()+" ]放入公海");
        return "redirect:/customer/my";

    }

    /**
     * 转移客户
     * @param custId
     * @param accountId
     * @param session
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/my/{custId:\\d+}/tran/{accountId:\\d+}")
    public String transferCustomerToAccount(@PathVariable Integer custId,
                                            @PathVariable Integer accountId,
                                            HttpSession session,
                                            RedirectAttributes redirectAttributes,
                                            Model model) {
        //需要获取的参数,custId,accountId,session,redirectAttributes
        Account account = (Account) session.getAttribute("curr_user");
        //根据id查询客户是否存在
        Customer customer = customerService.findCustomerById(custId);
        if(customer==null) {
            throw new NotFoundException();
        }

        if(!customer.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }

        customerService.transferCustomerToAccount(customer,accountId,account);

        redirectAttributes.addFlashAttribute(account.getUserName()+"已经成功将"+customer.getCustName()+"转出");

        return "redirect:/customer/my";
    }

    /**
     * 将客户导出为excel
     *//*
    @GetMapping("/my/export")
    public void exportExcel(HttpServletResponse response, HttpSession session) throws Exception {
        Account account = (Account) session.getAttribute("curr_user");

        //告诉浏览器输出内容的MIME
        response.setContentType("application/vnd.ms-excel");
        //设置弹出对话框的文件名称
        response.addHeader("Content-Disposition"," attachment;filename=\"customer.xls\"");
        customerService.exportAccountCustomerToExcel(account,response.getOutputStream());
    }*/




    //新增客户里的代办事项
    @PostMapping("/my/{id:\\d+}/task/new")
    public String newCustomerTask(@PathVariable Integer id,
                                  Task task,
                                  HttpSession session,
                                  RedirectAttributes redirectAttributes) {
        //需要获取的参数，当前客户id,task对象，session,redirectAttributes.
        Account account = (Account) session.getAttribute("curr_user");
        //根据customerId查找Customer对象
        Customer customer = customerService.findCustomerById(id);
        //判断是否存在和是否有权限
        if(customer==null) {
            throw new NotFoundException();
        }
        if(!customer.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }

        //根据Task对象和CustomerId到task表里实现新增
        taskService.saveNewTaskByCustomerId(task,id);

        return "redirect:/customer/my/"+id;
    }




}

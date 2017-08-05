package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.util.StringsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公海客户业务控制器
 * Created by huhu5 on 2017/7/22.
 */
@Controller
@RequestMapping("/customer")
public class PublicCustomerController {
    @Autowired
    private CustomerService customerService;


    /**
     * 公海客户首页
     * @return
     */
    @GetMapping("/public")
    public String pbulicCustomer(@RequestParam(required = false,defaultValue = "1",value = "p")Integer pageNum,
                                 @RequestParam(required = false,defaultValue = "",value = "keyword")String keyword,
                                 Model model,
                                 HttpSession session) {
        //需要获取的值有，pageNum,keyword,Model,session,
        //获取登陆用户对象
        Account account = (Account) session.getAttribute("curr_suer");

        //封装Map集合
        Map<String,Object> mapList = new HashMap<>();

        mapList.put("pageNum",pageNum);
        //对keyword搜索是中文进行转码
        keyword = StringsUtil.isoToUtf8(keyword);
        mapList.put("keyword",keyword);

        Customer customer = new Customer();
        //查询accountId为零的客户(公海客户)
        mapList.put("accountId",0);

        //调用PageInfo进行分页,查找客户
        PageInfo<Customer> pageInfo = customerService.findPublicCustomer(mapList);

        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("keyword",keyword);

        return "customer/public_home";
    }


    /**
     * //新增公海客户
     * @param model
     * @return
     */
    @GetMapping("/public/new")
    public String newPublicCustomer(Model model) {
        //页面发起get请求,将配置文件中下拉框的值返回页面进行设置
        List<String> industryList = (List<String>) customerService.findAllTrade();
        List<String> sourceList = (List<String>) customerService.findAllSource();

        model.addAttribute("industryList",industryList);
        model.addAttribute("sourceList",sourceList);
        return "customer/new_mycustomer";
    }

    @PostMapping("/public/new")
    public String newPublicCustomer(Customer customer,
                                    HttpSession session,
                                    RedirectAttributes redirectAttributes) {
        //需要参数，customer对象,session
        Account account = (Account) session.getAttribute("curr_user");
        //公海客户就是修改custor=mer.accountId=0。
        customer.setAccountId(0);
        //实现添加
        customerService.saveNewPublicCustomer(customer);
        redirectAttributes.addFlashAttribute("message","添加成功");

        return "redirect:/customer/public";
    }


    /**
     * //公海客户详情
     * @param id
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/public/{id:\\d+}")
    public String publicCustomerInfo(@PathVariable Integer id,
                                     HttpSession session,
                                     Model model) {
        //需要获取的值,当前客户id，当前用户对象,Model
        //Account account = (Account) session.getAttribute("curr_user");

        //根据id查询当前客户的信息
        Customer customer = customerService.findCustomerById(id);
        customer.setRemark("");

        model.addAttribute("customer",customer);

        return "customer/public_info";

    }

    //删除公海客户
    @GetMapping("/public/{id:\\d+}/del")
    public String delPublicCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        //获取客户id,闪现
        //根据id进行删除
        customerService.delMyCustomerById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/customer/public";
    }

    /**
     * //编辑客户/customer/public/${customer.id}/edit(请求转发)
     * @param model
     * @return
     */
    @GetMapping("/public/{id:\\d+}/edit")
    public String editPublicCustomer(@PathVariable Integer id, Model model,HttpSession session) {
        //需要的值，Model，session
        //读取配置文件中下拉框中的元素值
        List<String> industryList = (List<String>) customerService.findAllTrade();
        List<String> sourceList = (List<String>) customerService.findAllSource();

        model.addAttribute("industryList",industryList);
        model.addAttribute("sourceList",sourceList);

        //查出当前客户对象返回给页面进行模态框中回显
        Customer customer = customerService.findCustomerById(id);
        customer.setCreateTime(new Date());
        model.addAttribute(customer);
        return "customer/public_edit";
    }

    /**
     * 表单提交的编辑客户信息
     * @return
     */
    @PostMapping("/public/{id:\\d+}/edit")
    public String editPublicCustomer(@PathVariable Integer id,Customer customer) {
        //需要获取的值，客户id，Customer对象，

        //根据i查找客户手否存在
        Customer customers = customerService.findCustomerById(id);

        if(customers==null) {
            throw new NotFoundException();
        }
        //根据customer对象实现修改
        customerService.upDataCustomer(customer);
        return "redirect:/customer/public/"+customer.getId();
    }


























}

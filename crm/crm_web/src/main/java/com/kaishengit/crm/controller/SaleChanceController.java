package com.kaishengit.crm.controller;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Salechance;
import com.kaishengit.crm.entity.Salechancerecord;
import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.crm.service.SaleChanceService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 销售机会主页
 * Created by huhu5 on 2017/7/24.
 */
@Controller
@RequestMapping("/sales")
public class SaleChanceController {

    @Autowired
    private SaleChanceService saleChanceService;
    @Autowired
    private CustomerService customerService;

    /**
     * 我的销售机会主页
     * 用自带pageHelp分页方法进行分页
     * @return
     */
    @GetMapping("/my")
    public String MySaleChance(@RequestParam(required = false,defaultValue = "1",value = "p")Integer pageNum,
                               HttpSession session,
                               Model model) {
        //没有搜索
        //需要获取的值,pageNum,session,Model
        //获取当前登陆用户
        Account account = (Account) session.getAttribute("curr_user");

        //封装Map集合
        Map<String,Object> mapList = new HashMap<>();

        mapList.put("pageNum",pageNum);
        mapList.put("accountId",account.getId());

        //调用pageInfo进行分页查询

        PageInfo<Salechance> pageInfo = saleChanceService.findMySaleByParam(mapList);

        //将pageNum和pageInfo返回页面进行显示
        model.addAttribute("pageNum",pageNum);
        model.addAttribute("pageInfo",pageInfo);

        return "/sales/my";
    }

    /**
     * 新增销售机会
     * @param session
     * @param model
     * @return
     */
    @GetMapping("/my/new")
    public String newSaleChance(HttpSession session,Model model) {
        //先将配置文件中的下拉框的内容获取到并返回显示
        model.addAttribute("progressList",saleChanceService.findAllProgress());

        //去数据库里查询关联客户并返回显示给页面下拉框
        Account account = (Account) session.getAttribute("curr_user");

        //Map<String,Object> mapList = new HashMap<>();
        //List<Customer> customerList = saleChanceService.findAllCustomerByAccountId(account.getId());
        //根据AccountId查找customer
        List<Customer> customerList = customerService.findCustomerByAccountId(account.getId());

        model.addAttribute("customerList",customerList);
        return "/sales/new_chance";
    }

    @PostMapping("/my/new")
    public String newSaleChance(Salechance salechance,
                                RedirectAttributes redirectAttributes,
                                HttpSession session,
                                Model model) {
        //需要获取的参数，Salechance对象，session,Model

        Account account = (Account) session.getAttribute("curr_user");
        saleChanceService.saveSaleChance(salechance);
        redirectAttributes.addFlashAttribute("message","保存成功");

        return "redirect:/sales/my";

    }

    /**
     * //机会详情
     * @return
     */
    @GetMapping("/my/{id:\\d+}")
    public String saleChanceDetil(@PathVariable Integer id,
                                  HttpSession session,
                                  Model model) {
        //需要获取的参数,id,session,Model
        Account account = (Account) session.getAttribute("curr_user");
        //根据id查询销售机会
        Salechance saleChance = saleChanceService.findSaleChanceById(id);
        //判断是否有权限和是否为null
        if(saleChance==null) {
            throw new com.kaishengit.crm.controller.exception.NotFoundException();
        }
        if(!saleChance.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }

        //获取该机会对应的跟进记录列表
        List<Salechancerecord>  saleChanceRecordList = saleChanceService.findChanceRecordByChanceId(saleChance.getId());

        //将查询结果返回页面进行显示
        model.addAttribute("recordList", saleChanceRecordList);
        model.addAttribute("saleChance",saleChance);

        model.addAttribute("progressList",saleChanceService.findAllProgress());

        return "sales/chance";
    }

    /**
     * 删除销售机会
     * @param id
     * @param session
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/my/{id:\\d+}/del")
    public String delSaleChance(@PathVariable Integer id,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        //需要获取的参数,id,session,redirectAttribute
        Account account = (Account) session.getAttribute("curr_user");

        //根据id查询销售机会
        Salechance saleChance = saleChanceService.findSaleChanceById(id);

        //判断是否有权限和是否为null
        if(saleChance==null) {
            throw new com.kaishengit.crm.controller.exception.NotFoundException();
        }
        if(!saleChance.getAccountId().equals(account.getId())) {
            throw new ForbiddenException();
        }

        //执行删除
        saleChanceService.delSaleChanceById(id);

        redirectAttributes.addFlashAttribute("message","删除成功");
        return "sales/my";

    }

    //添加新的销售记录
    @PostMapping("/my/new/record")
    public String saveNewSaleRecord(Salechancerecord record) {
        //实现添加
        saleChanceService.saveNewSaleRecord(record);
        return "redirect:/sales/my/"+record.getSaleId();

    }

    //跟新我的销售进度
    @PostMapping("/my/{id:\\d+}/progress/update")
    public String updateMySaleRecode(@PathVariable Integer id,String progress) {
        //实现更新
        saleChanceService.updateMySaleRecode(id,progress);

        return "redirect:/sales/my/"+id;
    }



}

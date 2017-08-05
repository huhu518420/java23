package com.kaishengit.crm.controller;

import com.kaishengit.crm.service.CustomerService;
import com.kaishengit.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by huhu5 on 2017/7/28.
 */
@Controller
@RequestMapping("/charts")
public class ChartsController {
    @Autowired
    private CustomerService customerService;

    /**
     * 静态数据图表
     * @return
     */
    @GetMapping("/static")
    public String staticDataCharts() {

        return "charts/static";
    }

    /**
     * 客户分布图表
     * @return
     */
    @GetMapping("/customer")
    public String customerDataCharts() {

        return "charts/customer";
    }

    /**
     * 加载Bar数据
     * @return
     */
    @GetMapping("/customer/bar.json")
    @ResponseBody
    public AjaxResult loadBarData() {
        List<Map<String,Object>> levelDataMap =customerService.findCustomerLevelCount();

        return AjaxResult.success(levelDataMap);
    }


}


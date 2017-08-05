package com.kaishengit.crm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.DataTableResult;
import com.kaishengit.dto.ZTreeNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by huhu5 on 2017/7/17.
 */
@Controller
@RequestMapping("/manage/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private DeptService deptService;

    @GetMapping
    public String accountList() {
        //System.out.println("Accountroller。。。");
        return "manage/accounts";
    }

    /**
     * 加载ZTree
     * @return
     */
    @PostMapping("/depts.json")
    @ResponseBody
    public List<ZTreeNode> loadDeptDate() {
        //查找所有的部门
        List<Dept> deptList = deptService.findAllDept();

        //System.out.println(deptList);
        //将Dept对象转为 ZTreeNode
        //用guava实现
        List<ZTreeNode> nodeList = Lists.newArrayList(Collections2.transform(deptList, new Function<Dept,ZTreeNode>() {
            @Nullable
            @Override
            public ZTreeNode apply(@Nullable Dept dept) {
                //@Nullable注意导包类型
                ZTreeNode node = new ZTreeNode();
                node.setId(dept.getId());    //deptId存在
                node.setName(dept.getDeptName());
                node.setpId(dept.getpId());
                return node;
            }
        }));
        return nodeList;


        //用封装对象实现
        /*List<ZTreeNode> zTreeNodeList = new ArrayList<>();
        for (Dept dept : deptList) {
            ZTreeNode zTreeNode = new ZTreeNode();
            zTreeNode.setId(dept.getId());
            zTreeNode.setName(dept.getDeptName());
            zTreeNode.setpId(dept.getpId());

            zTreeNodeList.add(zTreeNode);
        }
        return zTreeNodeList;*/

    }


    //保存部门
    @PostMapping("/dept/new")
    @ResponseBody
    public AjaxResult saveDept(Dept dept) {
        System.out.println(dept.getId());
        deptService.saveDept(dept);
        return AjaxResult.success();
    }

    //添加员工
    @PostMapping("/new")
    @ResponseBody
    //deptId获取客服端与名字一至的值
    public AjaxResult addNewAccount(Account account,Integer[] deptId) {
        accountService.addNewAccount(account,deptId);

        return AjaxResult.success();
    }


    /**
     * DataTables数据加载
     * @return
     */
    @GetMapping("/load.json")
    @ResponseBody
    public DataTableResult<Account> loadAccountData(HttpServletRequest request) {

        //获取页面DataTables的数据
        String draw = request.getParameter("draw");
        //获取复选框id
        String deptId = request.getParameter("deptId");

        //判断如果deptId不是空的话
        Integer id=null;
        if(StringUtils.isNotEmpty(deptId)) {
            id = Integer.valueOf(deptId);
        }

        //获取Account总记录数
        Long total = accountService.countAll();
        //获取Account过滤后的数量
        Long filtedTotal = accountService.countByDeptId(id);
        //当前页的记录
        List<Account> accountList = accountService.findByDeptId(id);

        return new DataTableResult<>(draw,total,filtedTotal,accountList);

    }

    /**
     * //删除
     //@PathVariable Integer id获取页面在url中传来的值
     * @param id
     * @return
     */
    @PostMapping("/del/{id:\\d+}")
    @ResponseBody
    public AjaxResult delAccountById(@PathVariable Integer id) {

        accountService.delAccountById(id);

        return AjaxResult.success();
    }



}

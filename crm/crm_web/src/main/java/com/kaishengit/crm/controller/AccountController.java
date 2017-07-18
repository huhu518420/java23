package com.kaishengit.crm.controller;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.kaishengit.crm.entity.Dept;
import com.kaishengit.crm.service.AccountService;
import com.kaishengit.crm.service.DeptService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.dto.ZTreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nullable;
import java.util.ArrayList;
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
                node.setId(dept.getId());
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

        deptService.saveDept(dept);
        return AjaxResult.success();
    }




}

package com.kaishengit.crm.controller;

import com.kaishengit.crm.controller.exception.ForbiddenException;
import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 代办事项控制器
 * Created by huhu5 on 2017/7/25.
 */
@Controller
@RequestMapping("/task")
public class TaskController {
    //添加模型依赖
    @Autowired
    private TaskService taskService;

    /**
     * 代办事物首页
     * @param show
     * @param session
     * @param model
     * @return
     */
    @GetMapping
    public String taskHome(@RequestParam(required = false,defaultValue = "",value = "show")String show,
                           HttpSession session,
                           Model model) {
        //需要获取的参数，地址传来的show,session,Model
        Account account = (Account) session.getAttribute("curr_user");
        //判断show是否等于all，如果等于赋值为true否则为false
        boolean showAll = "all".equals(show) ? true : false;
        //根据accountId和showAll查询task
        List<Task> taskList = taskService.findTaskByAccountId(account.getId(),showAll);
        model.addAttribute("taskList",taskList);

        return "task/home";

    }

    /**
     * 新增人物get请求
     * @return
     */
    @GetMapping("/new")
    public String newTask() {

        return "task/new";
    }

    /**
     * 新增任务post请求
     * @param task
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/new")
    public String newTask(Task task, RedirectAttributes redirectAttributes) {
        //需要获取的参数，Task对象,redirectAttributes
        taskService.newTask(task);
        redirectAttributes.addFlashAttribute("message","新增成功");
        return "redirect:/task";
    }

    @GetMapping("/{id:\\d+}/state/{state}")
    public String changeTaskState(@PathVariable Integer id,@PathVariable String state,HttpSession session) {
        //需要获取的参数,task的id,状态state,session
        Account account = (Account) session.getAttribute("curr_user");
        //根据id查找task对象
        Task task = taskService.findTaskById(id);
        //判断是否存在和是否有权限修改
        if(task==null) {
            throw new NotFoundException();
        }

        if(!task.getAccountId().equals(account.getId())) {
           throw new ForbiddenException();
        }

        //根据state的值改变Done属性的值
        if("done".equals(state)) {
            task.setDone(true);
        }else{
            task.setDone(false);
        }
        //更新数据库
        taskService.updateState(task);

        return "redirect:/task";

    }

    @GetMapping("/{id:\\d+}/del")
    public String delTask(@PathVariable Integer id,
                          HttpSession session,
                          RedirectAttributes redirectAttributes) {
        //需要获取的值，Task的Id,session,redirectAttributes
        Account account = (Account) session.getAttribute("curr_user");
        //根据id执行删除
        taskService.delTaskById(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/task";
    }


}

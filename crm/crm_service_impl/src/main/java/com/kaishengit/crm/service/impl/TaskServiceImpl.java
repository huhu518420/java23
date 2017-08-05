package com.kaishengit.crm.service.impl;

import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.mapper.TaskMapper;
import com.kaishengit.crm.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huhu5 on 2017/7/25.
 */
@Service
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskMapper taskMapper;


    @Override
    public List<Task> findTaskByAccountId(Integer id, boolean showAll) {

        return taskMapper.findTaskByAccountId(id,showAll);
    }

    @Override
    public void newTask(Task task) {
        //设置创建时间
        task.setCreateTime(new Date());
        //设置出事状态
        task.setDone(false);
        //实现新增
        taskMapper.insert(task);
    }

    @Override
    public Task findTaskById(Integer id) {

        return taskMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateState(Task task) {
        taskMapper.updateByPrimaryKey(task);
    }

    @Override
    public void delTaskById(Integer id) {
        taskMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据CustomerId查询对应的Task
     * @param id
     * @return
     */
    @Override
    public List<Task> findTaskByCustomerId(Integer id) {

        return taskMapper.findTaskByCustomerId(id);
    }

    @Override
    public void saveNewTaskByCustomerId(Task task,Integer id) {
        task.setCreateTime(new Date());

        //task.setCustId(id);
        task.setDone(false);
        taskMapper.insert(task);
    }


}

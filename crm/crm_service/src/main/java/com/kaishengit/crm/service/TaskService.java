package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Task;

import java.util.List;

/**
 * Created by huhu5 on 2017/7/25.
 */
public interface TaskService {


    /**
     * 根据accountId和showAll进行查找符合要求的任务
     * @param id
     * @param showAll
     * @return
     */
    List<Task> findTaskByAccountId(Integer id, boolean showAll);

    void newTask(Task task);

    Task findTaskById(Integer id);

    void updateState(Task task);

    void delTaskById(Integer id);

    List<Task> findTaskByCustomerId(Integer id);

    void saveNewTaskByCustomerId(Task task,Integer id);

}

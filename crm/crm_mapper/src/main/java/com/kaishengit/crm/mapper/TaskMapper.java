package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Task;
import com.kaishengit.crm.entity.TaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    long countByExample(TaskExample example);

    int deleteByExample(TaskExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Task record);

    int insertSelective(Task record);

    List<Task> selectByExample(TaskExample example);

    Task selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByExample(@Param("record") Task record, @Param("example") TaskExample example);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    /**
     * 根据accountId和显示状态查询符合要求的task对象
     * @param id (指定参数对应元素为accountId)
     * @param showAll
     * @return
     */
    List<Task> findTaskByAccountId(@Param("accountId") Integer id,@Param("showAll") boolean showAll);

    List<Task> findTaskByCustomerId(Integer id);

    void saveNewTaskByCustomerId(Task task,@Param("custId") Integer id);

}
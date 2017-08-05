package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    long countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);


    /**
     * 根据AccountID查询
     * @param deptId
     * @return
     */
    List<Account> findByDeptId(@Param("deptId") Integer deptId);

    /**
     * 根据accountId查询过滤后的数量
     * @param deptId
     * @return
     */
    Long countByDeptId(@Param("deptId") Integer deptId);

    /**
     * 根据号码查找Account
     * @param mobile
     * @return
     */
    Account findByMobileLoderDept(@Param("mobile") String mobile);

}
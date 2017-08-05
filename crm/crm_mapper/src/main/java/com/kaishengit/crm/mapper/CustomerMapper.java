package com.kaishengit.crm.mapper;

import com.github.pagehelper.PageHelper;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.CustomerExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    long countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     * 根据分页对象进行查找
     * @param mapList
     * @return
     */
    List<Customer> findByQueryParam(Map<String, Object> mapList);


    List<Customer> findPublicCustomer(Map<String, Object> mapList);

    List<Customer> findCustomerByAccountId(Integer id);

    List<Map<String,Object>> findCustomerLevelCount();

}
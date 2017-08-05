package com.kaishengit.crm.mapper;

import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Salechance;
import com.kaishengit.crm.entity.Salechancerecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SalechanceMapper {


    List<Salechance> findMySaleByParam(Map<String, Object> mapList);

    List<Customer> findAllContentCustomerById(Integer id);

    void insert(Salechance salechance);

    Salechance findSaleChanceById(Integer id);

    List<Salechancerecord> findChanceRecordById(Integer id);

    void delSaleChanceById(Integer id);

    void saveNewSaleRecord(Salechancerecord record);

    void updateMySaleRecode(Salechance salechance);
}

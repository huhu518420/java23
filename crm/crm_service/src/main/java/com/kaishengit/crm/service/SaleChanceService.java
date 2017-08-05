package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Salechance;
import com.kaishengit.crm.entity.Salechancerecord;

import java.util.List;
import java.util.Map;

/**
 * Created by huhu5 on 2017/7/24.
 */
public interface SaleChanceService {

    Object findAllProgress();

    /**
     * 根据分页查询SaleChance
     * @param mapList
     * @return
     */
    PageInfo<Salechance> findMySaleByParam(Map<String, Object> mapList);

    List<Customer> findAllCustomerByAccountId(Integer id);

    void saveSaleChance(Salechance salechance);

    Salechance findSaleChanceById(Integer id);

    List<Salechancerecord> findChanceRecordByChanceId(Integer id);

    void delSaleChanceById(Integer id);

    void saveNewSaleRecord(Salechancerecord record);

    void updateMySaleRecode(Integer id, String progress);

}

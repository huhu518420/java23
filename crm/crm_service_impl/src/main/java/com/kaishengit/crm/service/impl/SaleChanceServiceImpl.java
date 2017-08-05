package com.kaishengit.crm.service.impl;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Salechance;
import com.kaishengit.crm.entity.Salechancerecord;
import com.kaishengit.crm.mapper.SalechanceMapper;
import com.kaishengit.crm.mapper.SalechancerecordMapper;
import com.kaishengit.crm.service.SaleChanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huhu5 on 2017/7/24.
 */
@Service
public class SaleChanceServiceImpl implements SaleChanceService {

    //读取配置文件...配置文件必须在自动注入之前读取
    @Value("#{'${sales.progress}'.split(',')}")
    private List<String> progressList;

    @Autowired
    private SalechanceMapper salechanceMapper;
    @Autowired
    private SalechancerecordMapper salechancerecordMapper;

    @Override
    public List<String> findAllProgress() {
        return progressList;
    }


    /**
     * 根据分页条件查找
     * @param mapList
     * @return
     */
    @Override
    public PageInfo<Salechance> findMySaleByParam(Map<String, Object> mapList) {
        //将pageNum转为整形
        Integer pageNum = (Integer) mapList.get("pageNum");

        Salechance salechance = new Salechance();
        salechance.setCreateTime(new Date());
        salechance.setLastTime(new Date());

        //调用pageHelp进行分页
        List<Salechance> salechanceList =salechanceMapper.findMySaleByParam(mapList);

        //返回pageInfo对象
        return new PageInfo<>(salechanceList);
    }

    /**
     * 根据accountId查询所有关联客户的名称
     * @param id
     * @return
     */
    @Override
    public List<Customer> findAllCustomerByAccountId(Integer id) {

        return salechanceMapper.findAllContentCustomerById(id);
    }

    /**
     * 条件新的销售机会
     * @param salechance
     */
    @Override
    public void saveSaleChance(Salechance salechance) {

        //新增机会的最后跟进时间就是当前时间
        salechance.setLastTime(new Date());
        salechance.setCreateTime(new Date());

        salechanceMapper.insert(salechance);

        //如果内容不为空则设置为一次跟进记录
        /*if(StringUtils.isNotEmpty(salechance.getContent())) {
            Salechancerecord record = new Salechancerecord();
            record.setContent(salechance.getContent());
            record.setSaleId(salechance.getId());
            record.setCreateTime(new Date());
            salechancerecordMapper.insert(record);
        }*/
    }

    /**
     * 根据id查询销售机会
     * @param id
     * @return
     */
    @Override
    public Salechance findSaleChanceById(Integer id) {


        return salechanceMapper.findSaleChanceById(id);
    }

    @Override
    public List<Salechancerecord> findChanceRecordByChanceId(Integer id) {


        return salechanceMapper.findChanceRecordById(id);
    }

    @Override
    public void delSaleChanceById(Integer id) {

        salechanceMapper.delSaleChanceById(id);
    }

    @Override
    public void saveNewSaleRecord(Salechancerecord record) {

        record.setCreateTime(new Date());
        salechanceMapper.saveNewSaleRecord(record);
    }

    /**
     * 更新销售记录
     * @param id
     * @param progress
     */
    @Override
    public void updateMySaleRecode(Integer id, String progress) {
        //先根据id查询到saleChance
        Salechance salechance = salechanceMapper.findSaleChanceById(id);
        //设置progress属性值
        salechance.setProgress(progress);
        salechanceMapper.updateMySaleRecode(salechance);
        //添加跟进记录
        Salechancerecord record = new Salechancerecord();
        record.setCreateTime(new Date());
        record.setSaleId(id);
        record.setContent("将当前进度修改为：[ " + progress + " ]");

        salechancerecordMapper.saveNewSalechancerecord(record);

    }

}

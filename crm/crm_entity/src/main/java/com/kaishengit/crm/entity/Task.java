package com.kaishengit.crm.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Task implements Serializable {
    private Integer id;

    /**
     * 任务内容
     */
    private String title;

    /**
     * 完成时间
     */
    private String finishTime;

    /**
     * 提醒时间
     */
    private String remindTime;

    /**
     * 是否完成 0未完成 1完成
     */
    private Boolean done;

    /**
     * 用户ID
     */
    private Integer accountId;

    /**
     * 关联客户id
     */
    private Integer custId;

    /**
     * 关联销售机会id
     */
    private Integer saleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 客户对象
     */
    private Customer customer;

    /**
     * 销售机会对象
     */
    private Salechance salechance;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Salechance getSalechance() {
        return salechance;
    }

    public void setSalechance(Salechance salechance) {
        this.salechance = salechance;
    }
}
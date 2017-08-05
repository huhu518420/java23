package com.kaishengit.pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
@Entity
@Table
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 客户姓名
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 职位
     */
    private String position;

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 行业
     */
    private String industry;

    /**
     * 客户来源
     */
    private String source;

    /**
     * 客户级别
     */
    private String level;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后联系时间
     */
    @Column(name = "last_contact_time")
    private Date lastContactTime;

    /**
     * 更改时间
     */
    @Column(name = "update_time")
    private Date updateTime;


    /**
     * 备注
     */
    private String remark;

    /**
     * 提示
     */
    private String reminder;
    /**
     * 用户所属账号,如果为空则为公海客户
     * 在多对一的时候一的一端的id（account_id）不需要添加在实体类中
     */
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastContactTime() {
        return lastContactTime;
    }

    public void setLastContactTime(Date lastContactTime) {
        this.lastContactTime = lastContactTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }
}
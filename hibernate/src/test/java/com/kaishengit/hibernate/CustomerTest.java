package com.kaishengit.hibernate;

import com.kaishengit.pojo.Customer;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by huhu5 on 2017/8/2.
 */
public class CustomerTest implements Serializable{
    /*Session session = null;
    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
    }
    @After
    public void after() {
        session.getTransaction().commit();
    }

    @Test
    public void save() {
        Customer customer = new Customer();
        //id的增长策略被设为uuid----也会自动插入唯一的一个uuid值
        customer.setName("乖乖");
        session.save(customer);
        System.out.println(customer.getId());
    }


    @Test
    public void find() {
        //一级缓存(在session中查询同一个数据多次)
        String id = "402881355da1d29d015da1d29ef90000";
        Customer cust1 = (Customer) session.get(Customer.class,id);
        //将第一次查询的session清除
        session.evict(cust1);

        Customer cust2 = (Customer) session.get(Customer.class,id);

        System.out.println(cust2.getName());

    }*/

    @Test
    public void find2() {
        //多个session之间信息要共享则出现二级缓存
        String id = "402881355da1d29d015da1d29ef90000";

        //产生第一个session
        Session s1 = HibernateUtil.getSession();
        s1.getTransaction().begin();
        Customer cust1 = (Customer) s1.get(Customer.class,id);

        s1.getTransaction().commit();

        //cache对象通过工厂来获取
        //通过SessionFactory对象获取Cache对象，通过Cache对象的evictEntityRegion可以将指定类从二级缓存中清除
        Cache cache = HibernateUtil.getSessionFactory().getCache();
        cache.evictEntity(Customer.class,id);

          Session s2 = HibernateUtil.getSession();
          s2.getTransaction().begin();

          Customer cust2 = (Customer) s2.get(Customer.class,id);
          System.out.println(cust2.getName());

          s2.getTransaction().commit();


    }







}

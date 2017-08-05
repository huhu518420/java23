package com.kaishengit.hibernate;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

/**
 * Created by huhu5 on 2017/7/31.
 */
public class HibernateTest {

    @org.junit.Test
    public void first() {
        //读取配置文件(从classpath中读取名称为hibernate.cfg.xml的配置文件)
        Configuration configuration = new Configuration().configure();
        //创建SessionFactory
        //SessionFactory sessionFactory = configuration.buildSessionFactory();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //创建Session...从当前线程中获取
        Session session = sessionFactory.getCurrentSession();
        //开启事务
        Transaction transaction = session.getTransaction();
        transaction.begin();

        Account account = new Account();
        account.setUsername("王思雨");
        account.setAddress("北京");
        account.setAge(23);

        session.save(account);


        //关闭事务（提交 | 回滚）
        transaction.commit();


    }

    @org.junit.Test
    public void findById() {

        //获取session
        Session session = HibernateUtil.getSession();
        //加载session...必须在事物中进行
        session.getTransaction().begin();

        //内容部分
        //根据主键查询
        Account account = (Account) session.get(Account.class,1);
        System.out.println(account.getUsername());


        //提交session
        session.getTransaction().commit();
    }

    @org.junit.Test
    public void upDate() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        //根据id去查询Account对象
        Account account = (Account) session.get(Account.class,1);
        //游离态结束
        //持久态开始
        account.setUsername("思雨");  //持久化对象的改变会被同步到数据库中

        session.getTransaction().commit();
    }

    @org.junit.Test
    public void delete() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        //根据id去查询Account对象
        Account account = (Account) session.get(Account.class,1);
        //游离态结束
        //持久态开始
        session.delete(account);

        session.getTransaction().commit();
    }

    @org.junit.Test
    public void findAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();  //和 session.getTransaction().begin();一样

        String hql = "from Account where username = :name";   //Account是持久化类username是持久化类中的属性

        Query query = session.createQuery(hql);
        query.setParameter("name","思雨");

        List<Account> accountList = query.list();

        for(Account account : accountList) {
            System.out.println(account.getUsername()+"->"+account.getAddress());
        }

        session.getTransaction().commit();
    }



}

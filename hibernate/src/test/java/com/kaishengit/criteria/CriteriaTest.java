package com.kaishengit.criteria;

import com.kaishengit.pojo.Account;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Criteria测试类
 * Created by huhu5 on 2017/8/1.
 */
public class CriteriaTest {
    Session session = null;
    @Before
    public void before() {
        //获取session
       session = HibernateUtil.getSession();
        //通过事物加载session
        session.getTransaction().begin();
    }

    @After
    public void after() {
        //通过事物提交session
        session.getTransaction().commit();
    }

    @Test
    public void findAll() {
        //创建Criteria对象
        Criteria criteria = session.createCriteria(Account.class);

        List<Account> accountList = criteria.list();

        for(Account account : accountList) {
            System.out.println(account);
        }
    }

    //提交where提交的查询
    @Test
    public void findByAccountName() {
        //创建criteria对象
        Criteria criteria = session.createCriteria(Account.class);
        //where条件
        //根据属性查询
        //criteria.add(Restrictions.eq("username","丽丽"));

        //根据最值查询
        //criteria.add(Restrictions.lt("id",3));  //where id < 3;

        //in查询   如果id的值不存在，不会进行显示
        //criteria.add(Restrictions.in("id",new Integer[]{1,4,5}));

        //or
//       criteria.add(Restrictions.or(Restrictions.eq("username","丽丽")
//               ,Restrictions.eq("username","空心大白菜")));

        //使用disjunction连接的查询条件，之间也是使用or连接
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.eq("username","丽丽"));
        disjunction.add(Restrictions.eq("username","jiji"));

        //思想和上一中方法差不多
        criteria.add(disjunction);

        List<Account> accountList = criteria.list();

        for(Account account : accountList) {
            System.out.println(account);
        }

    }


    //分页参数，排序
    @Test
    public void pageAndOrder() {
        //创建criteria对象
        Criteria criteria = session.createCriteria(Account.class);

        criteria.setFirstResult(0);
        criteria.setMaxResults(3);

        List<Account> accountList = criteria.list();

        for (Account account : accountList) {
            System.out.println(account);
        }
    }




}

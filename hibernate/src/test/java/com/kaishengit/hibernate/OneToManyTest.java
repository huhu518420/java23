package com.kaishengit.hibernate;

import com.kaishengit.pojo.Address;
import com.kaishengit.pojo.User;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**一对多
 * Created by huhu5 on 2017/8/1.
 */
public class OneToManyTest {

    Session session =null;
    @Before
    public void before() {
        //获取session对象
        session = HibernateUtil.getSession();
        //session通过事物开始
        session.getTransaction().begin();
    }

    @After
    public void after() {
        session.getTransaction().commit();
    }

    @Test
    public void OneToManyTest() {

        //新建用户 新建地址  保存用户和地址   !!!!
        User user = new User();
        user.setUserName("冬瓜");

        Address address = new Address();
        address.setAddress("贵州");
        address.setCityName("贵阳");
        address.setUser(user);

        //将多的一方封装到一个集合中
        Set<Address> addressSet = new HashSet<Address>();
        addressSet.add(address);
        //设置user中的属性
        user.setAddressSet(addressSet);
        //保存是先存一再存多
        session.save(user);
        session.save(address);

    }

    @Test
    public void deleteUser() {
        //删除用户  级联删除
        //先查找出来再删除
        User user = (User) session.get(User.class,3);
        session.delete(user);
    }

    @Test
    public void addNewAddress() {
        //给id为1的丽丽条件新的收获地址
        User user = (User) session.get(User.class,1);

        Address address = new Address();
        address.setCityName("焦作");
        address.setAddress("河南理工大学");

        address.setUser(user);

        session.save(address);
    }


    @Test
    public void findByAddressId() {

        Address address = (Address) session.get(Address.class,6);

        System.out.println(address.getCityName()+"->"+address.getAddress());
        //延迟加载，如果需要一，则再次发出请求进行查询
        System.out.println(address.getUser().getUserName());
    }

    @Test
    public void findAddressByUserId() {
        //通过user_id外键查询某个用户拥有的地址 !!!!!!
        Criteria criteria = session.createCriteria(Address.class); //OGNL
        //给对应的列添加别名
        criteria.createAlias("user","u");

        //criteria.add(Restrictions.eq("user.id",2));
        criteria.add(Restrictions.eq("u.userName","丽丽"));

        List<Address> addressList = criteria.list();
        for(Address address : addressList) {
            System.out.println(address.getId() + "->" + address.getCityName() + " -> " + address.getAddress());
        }
    }

    @Test
    public void findByUserId() {
        //查询一个用户 加载用户对应的地址
        User user = (User) session.get(User.class,2);
        System.out.println(user.getUserName());

        //延迟加载
        Set<Address> addressSet = user.getAddressSet();
        for(Address address : addressSet) {
            System.out.println(address.getId() + "->" + address.getCityName() + " -> " + address.getAddress());
        }

    }
























}

package com.kaishengit.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


/**将工厂模式设计为单列的
 * Created by huhu5 on 2017/7/31.
 */
public class HibernateUtil {

    //使sessionFactory指向一个私有方法，在私有方法中进行加载工厂
    private static SessionFactory sessionFactory = builderSessionFactory();

    private static SessionFactory builderSessionFactory() {
        //读取配置文件(从classpath中读取名称为hibernate.cfg.xml的配置文件)
        Configuration configuration = new Configuration().configure();
        //创建SessionFactory
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionFactory;
    }

    /**
     * 提供获取SessionFactory的共有方法
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 获取Session的共有方法
     * @return
     */
    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }


}

package com.kaishengit.hibernate;

import com.kaishengit.pojo.Student;
import com.kaishengit.pojo.Teacher;
import com.kaishengit.util.HibernateUtil;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by huhu5 on 2017/8/2.
 */
public class ManyToManyTest {

    private Session session;
    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.getTransaction().begin();
    }

    @After
    public void after() {
        session.getTransaction().commit();   //如果在提交的时候出现空指针异常，说明主配置文件中没有提交子配置
    }

    @Test
    public void save() {
        //新增两个学生s1 s2 新增两个老师t1 t2
        Student s1 = new Student();
        s1.setStudentName("哈哈");

        Student s2 = new Student();
        s2.setStudentName("嘿嘿");

        Teacher t1 = new Teacher();
        t1.setTeacherName("哈哈的老师");
        Teacher t2 = new Teacher();
        t2.setTeacherName("嘿嘿的老师");

        Set<Teacher> teacherSet = new HashSet<Teacher>();
        teacherSet.add(t1);
        teacherSet.add(t2);

        s1.setTeacherSet(teacherSet);
        s2.setTeacherSet(teacherSet);

        session.save(t1);
        session.save(t2);

        session.save(s1);
        session.save(s2);




    }

































}

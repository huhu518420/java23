package com.kaishengit.pojo;

import java.util.Set;

/**
 * Created by huhu5 on 2017/8/2.
 */
public class Student {
    private Integer id;
    private String studentName;

    private Set<Teacher> teacherSet;

    //多对多时两个类之间的要注入Set集合
    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }

    public void setTeacherSet(Set<Teacher> teacherSet) {
        this.teacherSet = teacherSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}

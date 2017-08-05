package com.kaishengit.pojo;

import java.util.Set;

/**
 * Created by huhu5 on 2017/8/2.
 */
public class Teacher {
    private Integer id;
    private String teacherName;
    private Set<Student> studentSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }
}

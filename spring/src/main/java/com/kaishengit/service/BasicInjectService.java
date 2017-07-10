package com.kaishengit.service;

import com.kaishengit.dao.UserDao;
import org.junit.Test;

import java.util.*;

/**
 * Created by huhu5 on 2017/7/9.
 */
public class BasicInjectService {
    private double scoer;
    private String name;
    private List<String> nameList;
    private Set<String> myset;
    private Map<String,UserDao> userMap;
    private Properties likeMap;


    public void show() {
        System.out.println("name->"+ name);
        System.out.println("scoer->"+ scoer);

        for(String n : nameList) {
            System.out.println("nameList->"+ n);
        }

        for(String sets : myset) {
            System.out.println("myset->"+ sets);
        }
        //map输出方法
        for(Map.Entry<String,UserDao> entry : userMap.entrySet()) {
            System.out.println(entry.getKey()+"->"+entry.getValue());
        }

      Enumeration keys = likeMap.propertyNames();
        while(keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = likeMap.getProperty(key);
            System.out.println("key->" + key + "value->" + value);
        }

    }

    public void setScoer(double scoer) {
        this.scoer = scoer;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }

    public void setMyset(Set<String> myset) {
        this.myset = myset;
    }

    public void setUserMap(Map<String, UserDao> userMap) {
        this.userMap = userMap;
    }

    public void setLikeMap(Properties likeMap) {
        this.likeMap = likeMap;
    }
}

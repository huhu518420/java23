package com.kaishengit.pojo;

import java.util.Set;

/**
 * 用户持久层
 * Created by huhu5 on 2017/8/1.
 */
public class User {

    private Integer id;
    private String userName;

    //将多的放在Set集合中
    private Set<Address> addressSet;

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

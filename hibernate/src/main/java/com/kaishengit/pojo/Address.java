package com.kaishengit.pojo;

/**
 * Created by huhu5 on 2017/8/1.
 */
public class Address {
    Integer id;
    String cityName;
    String address;

    //多的一方的关联主键不用写在持久层中

    //一对多的时候在多的将一的对象作为属性
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

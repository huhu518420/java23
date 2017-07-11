package com.kaishengit.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.inject.Named;

/**
 * Created by huhu5 on 2017/7/7.
 */
/*@Service*/
/*@Repository*/
@Named
public class UserDao {
    public void save()  {
        System.out.println("saveed.....");
    }

    public void sum(int a,int b) {
        System.out.println(a+b);
    }
}

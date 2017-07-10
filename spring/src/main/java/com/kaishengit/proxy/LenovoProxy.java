package com.kaishengit.proxy;

/**
 * Created by huhu5 on 2017/7/10.
 */
public class LenovoProxy implements Computer {

    private Computer computer;
    //构造方法实现接口指向实现类
    public LenovoProxy(Computer computer) {
        this.computer = computer;
    }

    @Override
    public void sals() {
        System.out.println("代理在销售前的服务");
        //调用的时被实现类重写过的方法
        computer.sals();
        System.out.println("代理在销售后的服务。。。。");

    }
}

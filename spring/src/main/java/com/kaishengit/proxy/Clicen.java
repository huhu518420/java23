package com.kaishengit.proxy;

/**
 * 客户类
 * Created by huhu5 on 2017/7/10.
 */
public class Clicen {
    public static void main(String args[]) {

        Lenovo lenovo = new Lenovo();
        
        LenovoProxy lenovoProxy = new LenovoProxy(lenovo);
        lenovoProxy.sals();
    }

}

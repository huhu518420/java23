package com.kaishengit.proxy.jdbc;

import com.kaishengit.proxy.Computer;
import com.kaishengit.proxy.Lenovo;

import java.lang.reflect.Proxy;

/**
 * 动态代理测试
 * Created by huhu5 on 2017/7/10.
 */
public class Test {


    public static void main() {
        //目标对象
        Lenovo lenovo = new Lenovo();

        //Hander对象
        ComputerInvocationHandler invocationHandler = new ComputerInvocationHandler(lenovo);
        //接口指向(动态)代理对象
        //动态代理的核心：自动创建代理对象
        Computer computer = (Computer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(),lenovo.getClass().getInterfaces(),invocationHandler);
        //调用代理对象的方法
        computer.sals();
    }
}

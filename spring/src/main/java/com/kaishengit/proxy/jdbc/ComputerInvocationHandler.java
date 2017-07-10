package com.kaishengit.proxy.jdbc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理模式
 * Created by huhu5 on 2017/7/10.
 */
public class ComputerInvocationHandler implements InvocationHandler {

    //指定目标对象（在调用该类的时候传入目标对象）
    private Object target;
    public ComputerInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     *指定代理对象的行为
     * @param proxy 代理对象
     * @param method 目标对象的当前执行方法
     * @param args 调用方法带的参数
     * @return 调用方法的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("动态代理之前，，，");
        Object result = method.invoke(target, args);
        System.out.println("动态代理之后，，，");

        return result;
    }
}

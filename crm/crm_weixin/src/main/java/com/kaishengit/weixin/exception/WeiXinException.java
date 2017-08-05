package com.kaishengit.weixin.exception;

/**
 * 自定义微信异常
 * Created by huhu5 on 2017/7/28.
 */
public class WeiXinException extends RuntimeException {

    //1.无参构造方法
    public WeiXinException(){};

    //2.一个参数构造方法
    public WeiXinException(String message){
        super(message);
    }

    public WeiXinException(Throwable throwable) {
        super(throwable);
    }

    //3.两个参数的构造方法
    public WeiXinException(String message,Throwable throwable) {
        super(message,throwable);

    }

}

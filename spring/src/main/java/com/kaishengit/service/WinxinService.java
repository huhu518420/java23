package com.kaishengit.service;

import com.kaishengit.dao.WeixinDao;

/**
 * Created by huhu5 on 2017/7/9.
 */
public class WinxinService {

    private WeixinDao weixinDao;

    public void setWeixinDao(WeixinDao weixinDao) {
        this.weixinDao = weixinDao;
    }

    public void save() {
        weixinDao.save();
    }
}

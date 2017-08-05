package com.kaishengit.util.orm;

import java.util.List;

/**
 * hibernate分页工具类
 * Created by huhu5 on 2017/8/4.
 */
public class Page<T> {
    //其实行数
    private Integer start;
    //每页数量
    private Integer pageSize;
    //数据总数
    private Integer total;
    //总页数
    private Integer totalPageSize;
    //当前页
    private Integer pageNum;
    //存放数据的集合
    private List<T> items;

    //提供一个无惨的构造方法
    public Page() {}

    public Page(int totalSize,Integer pageSize,Integer pageNum) {

        total = totalSize;
        //计算总页数
        totalPageSize = total/pageSize;
        if(totalSize % pageSize > 0) {
            totalPageSize++;
        }

        if(pageNum < 1) {
            pageNum = 1;
        }
        if(pageNum > totalPageSize) {
            pageNum = totalPageSize;
        }
        if(totalPageSize == 0) {
            pageNum = 1;
        }
        //计算起始行数
        //1 0 10
        //2 10 10
        start = (pageNum - 1) * pageSize;

    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPageSize() {
        return totalPageSize;
    }

    public void setTotalPageSize(Integer totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }


}

package com.kaishengit.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;
import com.kaishengit.mapper.MovieMapper;
import com.kaishengit.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现类
 * Created by huhu5 on 2017/7/15.
 * 实现类应该交给spring管理
 */
@Service
public class MovieServiceImpl implements MovieService{

    //将接口对象注入到spring
    @Autowired
   private MovieMapper movieMapper;

    @Override
    public List<Movie> findAll() {

        return movieMapper.findAll();
    }

    /**
     * 保存movie方法
     * @param movie
     */
    @Override
    public void save(Movie movie) {
        movieMapper.save(movie);
    }

    @Override
    public void del(Integer id) {
        movieMapper.del(id);
    }

    @Override
    public Movie findById(Integer id) {

        return movieMapper.findById(id);
    }

    @Override
    public void editMovie(Movie movie) {
        movieMapper.editMovie(movie);
    }

    @Override
    public PageInfo<Movie> findByPageNo(Integer pageNo) {
        //调用PageHelper对象根据页数和大小进行分页
        PageHelper.startPage(pageNo,20);

        List<Movie> movieList = movieMapper.findAll();

        return new PageInfo<>(movieList);
    }

    @Override
    public PageInfo<Movie> findByParam(String title, String daoyan, Float min, Float max, Integer pageNo) {

        //第二中分页方式
        Map<String,Object> searchParam = new HashMap<>();
        searchParam.put("title",title);
        searchParam.put("daoyan",daoyan);
        searchParam.put("min",min);
        searchParam.put("max",max);

        //自动分页
        searchParam.put("pageNum",pageNo);
        searchParam.put("pageSize",20);

        List<Movie> movieList = movieMapper.findByParam(searchParam);
        return new PageInfo<>(movieList);

    }


}

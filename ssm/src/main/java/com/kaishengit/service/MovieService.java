package com.kaishengit.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Movie;

import java.util.List;

/**
 * Created by huhu5 on 2017/7/15.
 */
public interface MovieService {
    List<Movie> findAll();


    void save(Movie movie);

    void del(Integer id);

    Movie findById(Integer id);

    void editMovie(Movie movie);

    PageInfo<Movie> findByPageNo(Integer pageNo);

    PageInfo<Movie> findByParam(String title, String daoyan, Float min, Float max, Integer pageNo);

}

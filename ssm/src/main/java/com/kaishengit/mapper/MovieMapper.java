package com.kaishengit.mapper;

import com.kaishengit.entity.Movie;

import java.util.List;
import java.util.Map;

/**
 * Created by huhu5 on 2017/7/15.
 */
public interface MovieMapper {

    List<Movie> findAll();

    /**
     * @param movie
     */
    //保存
    void save(Movie movie);

    void del(Integer id);


    Movie findById(Integer id);

    void editMovie(Movie movie);

    List<Movie> findByParam(Map<String, Object> searchParam);

}

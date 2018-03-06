package com.jun.service;

import com.jun.entity.User3;

import java.util.List;

/**
 * Created by Administrator on 2017-09-26.
 */
public interface User3Service {
    User3 findById(Integer id);
    User3 save(String name);
    List<User3> findAll();
    List<User3>  findAllToCopy();
    void save(List<User3> list);
}
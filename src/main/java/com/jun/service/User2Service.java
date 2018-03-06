package com.jun.service;

import com.jun.entity.User2;

import java.util.List;

public interface User2Service {
    User2 findById(Integer id);
    User2 save(String name);
    List<User2> findAll();
    void delete(Integer id) ;
    User2 recover(String id);


}

package com.jun.service.impl;


import com.jun.dao.User3Dao;
import com.jun.daoH2.H2User3Dao;
import com.jun.entity.User3;
import com.jun.service.User3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User3ServiceImpl implements User3Service {
    @Autowired
    private H2User3Dao userDao;
    @Autowired
    private User3Dao mysqlUserDao;
    public User3 findById(Integer id) {
        return userDao.findById(id);
    }
    public User3 save(String name) {
        return userDao.save(new User3(name));
    }
    public List<User3> findAll() {
        return userDao.findAll();
    }
    public User3 getUser(Integer id, String name) {
        return null;
    }
    public List<User3> findAllToCopy() {
        return mysqlUserDao.findAll();
    }
    public void save(List<User3> list) {
        userDao.save(list);
    }

}

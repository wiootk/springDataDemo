package com.jun.service.impl;

import com.jun.dao.User2Dao;
import com.jun.entity.User2;
import com.jun.service.User2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class User2ServiceImpl implements User2Service {
    @Autowired
    private User2Dao userDao;
    public User2 findById(Integer id) {
        return userDao.findById(id);
    }
    public User2 save(String name) {
        return userDao.save(new User2(name));
    }
    public List<User2> findAll() {
        return userDao.findAll();
    }
    public User2 getUser(Integer id, String name) {
        return null;
    }

    public void delete(Integer id) {
         userDao.logicDelete(id);
    }
    public User2 recover(String id) {
        try {
            return userDao.recover(id);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
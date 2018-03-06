package com.jun.service.impl;

import com.jun.dao.UserDao;
import com.jun.entity.User;
import com.jun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    public User getUser(Integer id, String name) {
        return userDao.getUser(id, name);
    }
}

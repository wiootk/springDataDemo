package com.jun.dao.impl;

import com.jun.dao.UserDao;
import com.jun.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    public User getUser(Integer id, String name) {
        return new User(id, name);
    }
}
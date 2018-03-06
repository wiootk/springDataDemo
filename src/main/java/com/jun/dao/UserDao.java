package com.jun.dao;

import com.jun.entity.User;

public interface UserDao {
    User getUser(Integer id, String name);
}

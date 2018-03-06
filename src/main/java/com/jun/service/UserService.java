package com.jun.service;

import com.jun.entity.User;

public interface UserService {
    User getUser(Integer id, String name);
}
package com.jun.dao;

import com.jun.entity.User2;
import com.jun.util.DataSource;
import com.jun.util.MyRepo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface User2Dao extends MyRepo<User2, Serializable> {
//    @DataSource("mysql2")
    User2 findById(Integer id);
}

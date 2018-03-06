package com.jun.dao;

import com.jun.entity.User3;
import com.jun.util.MyRepo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface User3Dao extends MyRepo<User3, Serializable> {
    User3 findById(Integer id);
    List<User3> findAll();
}

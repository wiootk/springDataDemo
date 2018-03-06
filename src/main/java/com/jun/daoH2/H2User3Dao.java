package com.jun.daoH2;

import com.jun.entity.User3;
import com.jun.util.DataSource;
import com.jun.util.MyRepo;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
@DataSource("h2")
public interface H2User3Dao extends MyRepo<User3, Serializable> {
    User3 findById(Integer id);
    List<User3> findAll();
}

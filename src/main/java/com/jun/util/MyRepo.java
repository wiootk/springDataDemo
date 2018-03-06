package com.jun.util;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-09-22.
 */
@NoRepositoryBean
public interface MyRepo<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
    void logicDelete(ID id);
    void logicDelete(T t);
    T recover(ID id) throws ClassNotFoundException;
}
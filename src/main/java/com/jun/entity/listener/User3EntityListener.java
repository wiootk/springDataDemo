package com.jun.entity.listener;

import com.alibaba.fastjson.JSON;

import javax.persistence.*;

/**
 * Created by Administrator on 2017-09-27.
 */
public class User3EntityListener {
    @PrePersist
    public void beforePersist(Object entity) {
        System.out.println("PrePersist :  "+ JSON.toJSONString(entity));
    }

    @PreUpdate
    public void beforeUpdate(Object entity) {
        System.out.println("PreUpdate :  "+ JSON.toJSONString(entity));
    }

    @PreRemove
    public void beforeRemove(Object entity) {
        System.out.println("PreRemove :  "+ JSON.toJSONString(entity));
    }

    @PostPersist
    public void afterPersist(Object entity) {
        System.out.println("PostPersist :  "+ JSON.toJSONString(entity));
    }

    @PostUpdate
    public void afterUpdate(Object entity) {
        System.out.println("PostUpdate :  "+ JSON.toJSONString(entity));
    }

    @PostRemove
    public void afterRemove(Object entity) {
        System.out.println("PostRemove :  "+ JSON.toJSONString(entity));
    }

    @PostLoad
    public void afterLoad(Object entity) {
        System.out.println("PostLoad :  "+ JSON.toJSONString(entity));
    }

}

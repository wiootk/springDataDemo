package com.jun.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="sys_role")
@Data
public class Role implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String remark,name;
}


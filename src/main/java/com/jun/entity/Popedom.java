package com.jun.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name ="sys_popedom")
@Data
public class Popedom implements Serializable {
    @Id
    private String popedomModule,popedomPrivilege;
    private int sort;
    private String  title,popedomName,remark;
}

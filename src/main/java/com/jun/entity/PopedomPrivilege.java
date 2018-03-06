package com.jun.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name ="sys_popedom_privilege")
@Data
public class PopedomPrivilege implements Serializable {
    @Id
    private String roleId,popedomModule,popedomPrivilege;
}

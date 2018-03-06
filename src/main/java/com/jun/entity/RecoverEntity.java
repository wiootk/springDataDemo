package com.jun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "recover")
public class RecoverEntity implements Serializable {
    private static final long serialVersionUID = 7077267778320168471L;
    @Id
    private  String id= UUID.randomUUID().toString();
    private  String entityName;
    private String entity;
    private  long deleteTime;
    private String deleteOperorId;
    private String deleteOperorName;
    @Column(name = "recoverTime",nullable =true )
    private  long recoverTime=0;
    private String recoverOperorId;
    private String recoberOperorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteOperorId() {
        return deleteOperorId;
    }

    public void setDeleteOperorId(String deleteOperorId) {
        this.deleteOperorId = deleteOperorId;
    }

    public String getDeleteOperorName() {
        return deleteOperorName;
    }

    public void setDeleteOperorName(String deleteOperorName) {
        this.deleteOperorName = deleteOperorName;
    }

    public long getRecoverTime() {
        return recoverTime;
    }

    public void setRecoverTime(long recoverTime) {
        this.recoverTime = recoverTime;
    }

    public String getRecoverOperorId() {
        return recoverOperorId;
    }

    public void setRecoverOperorId(String recoverOperorId) {
        this.recoverOperorId = recoverOperorId;
    }

    public String getRecoberOperorName() {
        return recoberOperorName;
    }

    public void setRecoberOperorName(String recoberOperorName) {
        this.recoberOperorName = recoberOperorName;
    }
}

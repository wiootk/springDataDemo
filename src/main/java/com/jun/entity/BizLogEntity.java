package com.jun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Administrator on 2017-09-25.
 */
@Entity
@Table(name = "bizLog")
public class BizLogEntity implements Serializable {
    private static final long serialVersionUID = 7077267778320168471L;
    @Id
    private  String id= UUID.randomUUID().toString();
    private  long bizTime;
    private  String bizType;
    @Column(length = 4000)
    private  String remark;
    private String objType;
    private String  objId;
    private String operatorId;
    private String operatorName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getBizTime() {
        return bizTime;
    }

    public void setBizTime(long bizTime) {
        this.bizTime = bizTime;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
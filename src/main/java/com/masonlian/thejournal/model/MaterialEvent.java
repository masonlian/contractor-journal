package com.masonlian.thejournal.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class MaterialEvent {

    private Integer materialEventId ;
    private Integer projectId ;
    private BigDecimal totalAmount;
    private Timestamp createdDate;


    public Integer getMaterialEventId() {
        return materialEventId;
    }

    public void setMaterialEventId(Integer materialEventId) {
        this.materialEventId = materialEventId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }
}

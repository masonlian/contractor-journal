package com.masonlian.thejournal.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AccountPayable {
    private Integer payableId;
    private String supplier;
    private Integer month;
    private Integer materialEventId;
    private BigDecimal payableAmount;
    private Timestamp lastModifiedDate;
    private Boolean alreadyPaid;

    public Integer getPayableId() {
        return payableId;
    }

    public void setPayableId(Integer payableId) {
        this.payableId = payableId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getMaterialEventId() {
        return materialEventId;
    }

    public void setMaterialEventId(Integer materialEventId) {
        this.materialEventId = materialEventId;
    }

    public BigDecimal getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(BigDecimal payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Boolean getAlreadyPaid() {
        return alreadyPaid;
    }

    public void setAlreadyPaid(Boolean alreadyPaid) {
        this.alreadyPaid = alreadyPaid;
    }
}

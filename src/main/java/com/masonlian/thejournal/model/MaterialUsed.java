package com.masonlian.thejournal.model;

import java.math.BigDecimal;

public class MaterialUsed {

    private Integer materialUsedId ;
    private Integer materialEventId ;
    private BigDecimal unit;
    private BigDecimal amount;
    private String materialName;


    public Integer getMaterialUsedId() {
        return materialUsedId;
    }

    public void setMaterialUsedId(Integer materialUsedId) {
        this.materialUsedId = materialUsedId;
    }

    public Integer getMaterialEventId() {
        return materialEventId;
    }

    public void setMaterialEventId(Integer materialEventId) {
        this.materialEventId = materialEventId;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
}

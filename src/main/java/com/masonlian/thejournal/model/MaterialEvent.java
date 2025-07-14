package com.masonlian.thejournal.model;

import java.math.BigDecimal;

public class MaterialEvent {
    private String materialName;
    private Integer unit;
    private BigDecimal amount;
    private Integer eventId;
    private Integer materialEventId;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getMaterialEventId() {
        return materialEventId;
    }

    public void setMaterialEventId(Integer materialEventId) {
        this.materialEventId = materialEventId;
    }
}

package com.masonlian.thejournal.dto.request;

import java.math.BigDecimal;

public class MaterialItem {

    private String materialName;
    private BigDecimal unit;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public BigDecimal getUnit() {
        return unit;
    }

    public void setUnit(BigDecimal unit) {
        this.unit = unit;
    }
}

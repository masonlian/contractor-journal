package com.masonlian.thejournal.dto.request;

public class MaterialUsed {

    private String materialName;

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    private Integer unit;

}

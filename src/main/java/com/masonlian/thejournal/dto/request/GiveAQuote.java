package com.masonlian.thejournal.dto.request;

import java.math.BigDecimal;

public class GiveAQuote {



    private String materialName;
    private BigDecimal materialUnit;

    private String constructionItem;
    private BigDecimal constructionUnit;


    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public BigDecimal getMaterialUnit() {
        return materialUnit;
    }

    public void setMaterialUnit(BigDecimal materialUnit) {
        this.materialUnit = materialUnit;
    }

    public String getConstructionItem() {
        return constructionItem;
    }

    public void setConstructionItem(String constructionItem) {
        this.constructionItem = constructionItem;
    }

    public BigDecimal getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(BigDecimal constructionUnit) {
        this.constructionUnit = constructionUnit;
    }
}

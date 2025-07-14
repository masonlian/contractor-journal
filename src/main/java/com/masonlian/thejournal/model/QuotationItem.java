package com.masonlian.thejournal.model;

import java.math.BigDecimal;

public class QuotationItem {

    private Integer quotationItemId;
    private Integer quotationId;

    private String materialName;
    private BigDecimal materialUnit;
    private String materialSpec;

    private BigDecimal materialAmount;

    private BigDecimal constructionUnit;
    private String constructionItem;
    private String constructionSpec;

    private BigDecimal constructionAmount;


    public Integer getQuotationItemId() {
        return quotationItemId;
    }

    public void setQuotationItemId(Integer quotationItemId) {
        this.quotationItemId = quotationItemId;
    }

    public Integer getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

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

    public String getMaterialSpec() {
        return materialSpec;
    }

    public void setMaterialSpec(String materialSpec) {
        this.materialSpec = materialSpec;
    }

    public BigDecimal getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(BigDecimal materialAmount) {
        this.materialAmount = materialAmount;
    }

    public BigDecimal getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(BigDecimal constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getConstructionItem() {
        return constructionItem;
    }

    public void setConstructionItem(String constructionItem) {
        this.constructionItem = constructionItem;
    }

    public String getConstructionSpec() {
        return constructionSpec;
    }

    public void setConstructionSpec(String constructionSpec) {
        this.constructionSpec = constructionSpec;
    }

    public BigDecimal getConstructionAmount() {
        return constructionAmount;
    }

    public void setConstructionAmount(BigDecimal constructionAmount) {
        this.constructionAmount = constructionAmount;
    }
}

package com.masonlian.thejournal.model;

import java.math.BigDecimal;

public class QuotationItem {

    private Integer quotationItemId;
    private Integer quotationId;

    private BigDecimal constructionUnit;
    private String constructionItem;
    private String constructionSpec;
    private BigDecimal construction_estimate;


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

    public Integer getQuotationId() {
        return quotationId;
    }

    public void setQuotationId(Integer quotationId) {
        this.quotationId = quotationId;
    }

    public Integer getQuotationItemId() {
        return quotationItemId;
    }

    public void setQuotationItemId(Integer quotationItemId) {
        this.quotationItemId = quotationItemId;
    }

    public String getConstructionSpec() {
        return constructionSpec;
    }

    public void setConstructionSpec(String constructionSpec) {
        this.constructionSpec = constructionSpec;
    }

    public BigDecimal getConstruction_estimate() {
        return construction_estimate;
    }

    public void setConstruction_estimate(BigDecimal construction_estimate) {
        this.construction_estimate = construction_estimate;
    }
}

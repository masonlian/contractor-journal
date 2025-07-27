package com.masonlian.thejournal.model;

import java.math.BigDecimal;

public class Construction {
    private Integer constructionId;

    public String getConstructionItem() {
        return constructionItem;
    }

    public void setConstructionItem(String constructionItem) {
        this.constructionItem = constructionItem;
    }

    public Integer getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(Integer constructionId) {
        this.constructionId = constructionId;
    }

    public String getConstructionSpec() {
        return constructionSpec;
    }

    public void setConstructionSpec(String constructionSpec) {
        this.constructionSpec = constructionSpec;
    }

    public BigDecimal getConstructionEstimate() {
        return constructionEstimate;
    }

    public void setConstructionEstimate(BigDecimal constructionEstimate) {
        this.constructionEstimate = constructionEstimate;
    }


    private String constructionItem;
    private String constructionSpec;
    private BigDecimal constructionEstimate;

    public String getConstructionCategory() {
        return constructionCategory;
    }

    public void setConstructionCategory(String constructionCategory) {
        this.constructionCategory = constructionCategory;
    }

    private String  constructionCategory;

}

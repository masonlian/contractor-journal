package com.masonlian.thejournal.dto.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ConstructionRequest {

    @NotNull
    private String constructionItem;
    private String constructionSpec;
    private BigDecimal constructionEstimate;
    private String  constructionCategory;

    public String getConstructionCategory() {
        return constructionCategory;
    }

    public void setConstructionCategory(String constructionCategory) {
        this.constructionCategory = constructionCategory;
    }



    public String getConstructionSpec() {
        return constructionSpec;
    }

    public void setConstructionSpec(String constructionSpec) {
        this.constructionSpec = constructionSpec;
    }

    public String getConstructionItem() {
        return constructionItem;
    }

    public void setConstructionItem(String constructionItem) {
        this.constructionItem = constructionItem;
    }

    public BigDecimal getConstructionEstimate() {
        return constructionEstimate;
    }

    public void setConstructionEstimate(BigDecimal constructionEstimate) {
        this.constructionEstimate = constructionEstimate;
    }


}

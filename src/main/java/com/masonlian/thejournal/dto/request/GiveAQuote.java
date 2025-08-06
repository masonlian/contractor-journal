package com.masonlian.thejournal.dto.request;

import java.math.BigDecimal;

public class GiveAQuote {

    private String constructionItem;
    private BigDecimal constructionUnit;
    private BigDecimal constructionEstimate;
    private String constructionSpec;


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

    public BigDecimal getConstructionEstimate() {
        return constructionEstimate;
    }

    public void setConstructionEstimate(BigDecimal constructionEstimate) {
        this.constructionEstimate = constructionEstimate;
    }

    public String getConstructionSpec() {
        return constructionSpec;
    }

    public void setConstructionSpec(String constructionSpec) {
        this.constructionSpec = constructionSpec;
    }
}

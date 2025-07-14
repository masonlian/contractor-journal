package com.masonlian.thejournal.dto.request;

import java.math.BigDecimal;

public class ConstructionRequest {
    private String constructionItem;

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

    public String getMediaRepositoryCategory() {
        return mediaRepositoryCategory;
    }

    public void setMediaRepositoryCategory(String mediaRepositoryCategory) {
        this.mediaRepositoryCategory = mediaRepositoryCategory;
    }

    private String constructionSpec;
    private BigDecimal constructionEstimate;
    private String mediaRepositoryCategory;
}

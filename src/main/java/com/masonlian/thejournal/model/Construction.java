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

    public String getMediaRepositoryCategory() {
        return mediaRepositoryCategory;
    }

    public void setMediaRepositoryCategory(String mediaRepositoryCategory) {
        this.mediaRepositoryCategory = mediaRepositoryCategory;
    }

    private String constructionItem;
    private String constructionSpec;
    private BigDecimal constructionEstimate;
    private String mediaRepositoryCategory;

}
